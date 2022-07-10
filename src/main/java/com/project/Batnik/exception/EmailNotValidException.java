package com.project.Batnik.exception;

import com.project.Batnik.util.Constants;

public class EmailNotValidException extends IllegalStateException{
    public EmailNotValidException(){
        super(Constants.EMAIL_NOT_VALID);
    }
}
