package org.notenmanager.DataBaseServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteUserTests extends DatabaseServiceTests {
    @Test
    public void Should_delete_User() {
        SetupData();
        sut.DeleteUser(existentUser.username);
        Assertions.assertFalse(sut.UserExist(existentUser.username));
    }
}
