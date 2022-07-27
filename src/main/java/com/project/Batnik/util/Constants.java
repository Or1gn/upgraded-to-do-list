package com.project.Batnik.util;

public class Constants {

    //exceptions
    public static final String NOT_VALID_TEXT_MESSAGE = "not valid text message";
    public static final String USER_NOT_FOUND = "user with %s email not found";
    public static final String EMAIL_NOT_VALID = "email is not valid";
    public static final String EMAIL_ALREADY_TAKEN = "email is already taken";
    public static final String FAILED_TO_SEND_EMAIL = "failed to send email";
    public static final String REFRESH_TOKEN_IS_MISSING = "refresh token is missing";
    public static final String INCORRECT_PRIORITY_NAME = "incorrect priority name";
    public static final String INCORRECT_PROJECT_ID = "incorrect project id";

    //common messages
    public static final String EDIT_DATA = "data was successfully edit";
    public static final String EMAIL_SEND_SUBJECT = "Activation code for Batnik";
    public static final String EMAIL_SEND_TEXT = "Hello %s! \n" +
            "Welcome to Batnik. Your activation link: http://localhost:8086/api/v1/acivation/%s";

    //user
    public static final String USER_SUCCESSFULLY_DELETED = "user was successfully deleted";
    public static final String USER_SUCCESSFULLY_ACTIVATED = "user was successfully activated";
    public static final String USER_REGISTERED = "user was successfully registered";

    //project
    public static final String PROJECT_SUCCESSFULLY_DELETED = "project was successfully deleted";
    public static final String GRANTED_ACCESS = "access was granted";
    public static final String CREATE_PROJECT = "the project was created";

    //task
    public static final String CREATE_TASK = "the task was created";
    public static final String ADD_PRIORITY_TO_TASK = "the role was added";


}
