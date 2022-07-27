package com.project.Batnik.exception;

import com.project.Batnik.util.Constants;

public class IncorrectPriorityNameException extends IllegalStateException{
    public IncorrectPriorityNameException(){ super(Constants.INCORRECT_PRIORITY_NAME); }
}
