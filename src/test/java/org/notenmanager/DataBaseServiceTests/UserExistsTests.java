package org.notenmanager.DataBaseServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserExistsTests extends DatabaseServiceTests {

    @Test
    public void Should_be_true() {
        SetupData();
        Assertions.assertTrue(sut.UserExist(existentUser.username));
    }

    @Test
    public void Should_be_false() {
        SetupData();
        Assertions.assertFalse(sut.UserExist("SomeUserWichDoesntExist"));
    }

}
