package org.notenmanager.DataBaseServiceTests;

import jakarta.persistence.Column;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.notenmanager.Models.User;

public class GetUserTests extends DatabaseServiceTests {

    @Test
    public void Should_get_User() {
        SetupData();
        User result = sut.GetUser(existentUser.username);
        Assertions.assertEquals(existentUser.username, result.username);
        Assertions.assertEquals(existentUser.password, result.password);
        Assertions.assertEquals(existentUser.mail, result.mail);
        Assertions.assertEquals(existentUser.schoolClass.name, result.schoolClass.name);
    }

    @Test
    public void Should_return_null_when_User_doesnt_exist() {
        SetupData();
        User result = sut.GetUser("NotExistentUser");
        Assertions.assertNull(result);
    }
}
