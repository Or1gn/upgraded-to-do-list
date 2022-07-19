package com.project.Batnik.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Batnik.exception.EmailAlreadyTakenException;
import com.project.Batnik.exception.EmailNotValidException;
import com.project.Batnik.exception.RefreshTokenIsMissingException;
import com.project.Batnik.exception.UserNotFoundException;
import com.project.Batnik.model.RQ.RegistrationRQ;
import com.project.Batnik.model.entity.User;
import com.project.Batnik.model.enums.Roles;
import com.project.Batnik.repository.UserRepository;
import com.project.Batnik.util.Constants;
import com.project.Batnik.validator.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailValidator emailValidator;
    private final MailSender mailSender;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expired.milliseconds}")
    private Long expiredMilliseconds;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND, email)));
        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    public String addUser(RegistrationRQ registrationRQ){
        if (userRepository.findUserByEmail(registrationRQ.getEmail()).isPresent()){
            throw new EmailAlreadyTakenException();
        }

        User user = new User();

        if (!emailValidator.test(registrationRQ.getEmail())){
            throw new EmailNotValidException();
        }
        user.setEmail(registrationRQ.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registrationRQ.getPassword()));
        user.setUsername(registrationRQ.getUsername());
        user.setRole(Roles.USER);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setActivatedStatus(false);
        userRepository.save(user);

        String message = String.format(Constants.EMAIL_SEND_TEXT, user.getUsername(), user.getActivationCode());
        mailSender.send(registrationRQ.getEmail(), Constants.EMAIL_SEND_SUBJECT, message);

        return Constants.USER_REGISTERED;
    }

    public String activateUser(String code){
        User user = userRepository.findUserByActivationCode(code)
                .orElseThrow(UserNotFoundException::new);
        user.setActivatedStatus(true);
        userRepository.save(user);
        return Constants.USER_SUCCESSFULLY_ACTIVATED;
    }

    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader.startsWith("Bearer ")){
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String email = decodedJWT.getSubject();
                User user = userRepository.findUserByEmail(email)
                        .orElseThrow(UserNotFoundException::new);
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + expiredMilliseconds))
                        .withIssuer("Batnik")
                        .withClaim("role",
                                List.of(user.getRole().name()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }
            catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else {
            throw new RefreshTokenIsMissingException();
        }
    }
}
