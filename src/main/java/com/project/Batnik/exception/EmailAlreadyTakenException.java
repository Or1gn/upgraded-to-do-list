package com.project.Batnik.exception;

import com.project.Batnik.util.Constants;

public class EmailAlreadyTakenException extends IllegalStateException{
    public EmailAlreadyTakenException(){
        super(Constants.EMAIL_ALREADY_TAKEN);
    }
}
