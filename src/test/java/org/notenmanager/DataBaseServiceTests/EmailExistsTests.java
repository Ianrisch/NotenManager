package org.notenmanager.DataBaseServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailExistsTests extends DatabaseServiceTests {
    @Test
    public void Should_return_true() {
        SetupData();
        Assertions.assertTrue(sut.EmailExist(existentUser.mail));
    }

    @Test
    public void Should_return_false() {
        SetupData();
        Assertions.assertFalse(sut.EmailExist("SomeEmailWichDoesntExist"));
    }
}
