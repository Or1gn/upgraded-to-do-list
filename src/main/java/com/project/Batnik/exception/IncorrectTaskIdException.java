package com.project.Batnik.exception;

import com.project.Batnik.util.Constants;

public class IncorrectTaskIdException extends IllegalStateException{
    public IncorrectTaskIdException(){super(Constants.INCORRECT_TASK_ID);}
}
