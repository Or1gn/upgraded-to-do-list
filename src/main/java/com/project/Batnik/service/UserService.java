package com.project.Batnik.service;

import com.project.Batnik.exception.EmailAlreadyTakenException;
import com.project.Batnik.exception.EmailNotValidException;
import com.project.Batnik.exception.UserNotFoundException;
import com.project.Batnik.model.RQ.RegistrationRQ;
import com.project.Batnik.model.entity.User;
import com.project.Batnik.model.enums.Roles;
import com.project.Batnik.repository.UserRepository;
import com.project.Batnik.util.Constants;
import com.project.Batnik.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailValidator emailValidator;
    private final MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND, email)));
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
}
