package org.notenmanager.Exceptions;

public class AlreadyExistsException extends IllegalArgumentException {
    public AlreadyExistsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public AlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
