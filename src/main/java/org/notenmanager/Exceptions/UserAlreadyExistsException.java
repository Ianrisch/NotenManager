package org.notenmanager.Exceptions;

import org.notenmanager.Models.User;

public class UserAlreadyExistsException extends AlreadyExistsException {
    public UserAlreadyExistsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

    public UserAlreadyExistsException(User user) {
        super("User with username:  [" + user.username + "] already exists!");
    }


}
