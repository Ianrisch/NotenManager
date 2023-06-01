package org.notenmanager.DataBaseServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticateUser extends DatabaseServiceTests{

    @Test
    public void Should_Authenticate(){
        SetupData();
        Assertions.assertTrue(sut.AuthenticateUser(existentUser.username, existentUser.password));
    }
    @Test
    public void Should_not_Authenticate(){
        SetupData();
        Assertions.assertFalse(sut.AuthenticateUser("hanse", existentUser.password));
    }
}
