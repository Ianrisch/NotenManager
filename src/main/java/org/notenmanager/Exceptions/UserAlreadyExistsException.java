package org.notenmanager.Exceptions;

public class UserAlreadyExistsException extends AlreadyExistsException{
    public UserAlreadyExistsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
