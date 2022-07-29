package com.project.Batnik.exception;

import com.project.Batnik.util.Constants;

public class PriorityNotFoundException extends IllegalStateException{
    public PriorityNotFoundException(){super(Constants.PRIORITY_NOT_FOUND);}
}
