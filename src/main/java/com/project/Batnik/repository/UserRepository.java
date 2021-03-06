package com.project.Batnik.repository;

import com.project.Batnik.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    User deleteUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByActivationCode(String code);
    User findUserByUsername(Object object);
}
