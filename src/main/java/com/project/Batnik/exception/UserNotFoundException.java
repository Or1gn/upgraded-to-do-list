package com.project.Batnik.exception;

import com.project.Batnik.util.Constants;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {
    public UserNotFoundException() {
        super(Constants.USER_NOT_FOUND);
    }
}
