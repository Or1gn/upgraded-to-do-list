package com.project.Batnik.exception;

import com.project.Batnik.util.Constants;

public class IncorrectProjectIdException extends IllegalStateException{
    public IncorrectProjectIdException(){super(Constants.INCORRECT_PROJECT_ID);}
}
