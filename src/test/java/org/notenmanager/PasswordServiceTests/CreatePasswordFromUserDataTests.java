package org.notenmanager.PasswordServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.notenmanager.Utils.PasswordService;

import java.security.NoSuchAlgorithmException;

public class CreatePasswordFromUserDataTests {
    @ParameterizedTest
    @CsvSource({
            "test,password123,1589e0c672130de7185d6b5e4df810ce",
            "tes,password12,6f313e15188db30a12ea5b1b1af11c46",
            "mamma,mia,1306fb91fe361e70246bd78fa6601e1a",
            "mamma,mias,ed5580d83456503063c7df635a7fee29",
    })
    public void Should_Return_Right_Hash(String username, String plainTextPassword, String expected) {
        String result =  PasswordService.CreatePasswordFromUserData(username, plainTextPassword);
        Assertions.assertEquals(expected.toLowerCase(), result.toLowerCase());
    }
}
