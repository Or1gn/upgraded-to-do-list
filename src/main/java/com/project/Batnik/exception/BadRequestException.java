package com.project.Batnik.exception;

import com.project.Batnik.util.Constants;

public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super(Constants.NOT_VALID_TEXT_MESSAGE);
    }
}
