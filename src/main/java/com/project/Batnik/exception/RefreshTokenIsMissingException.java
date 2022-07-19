package com.project.Batnik.exception;

import com.project.Batnik.util.Constants;

public class RefreshTokenIsMissingException extends RuntimeException{
    public RefreshTokenIsMissingException(){
        super(Constants.REFRESH_TOKEN_IS_MISSING);
    }
}
