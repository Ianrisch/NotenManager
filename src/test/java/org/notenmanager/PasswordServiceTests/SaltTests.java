package org.notenmanager.PasswordServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.notenmanager.Utils.PasswordService;

public class SaltTests {

    @ParameterizedTest
    @CsvSource({
            "test,password123,test-passw-test-ord123-test",
            "tes,password12,tes-passw-tes-ord12-tes",
            "mamma,mia,mamma-m-mamma-ia-mamma",
            "mamma,mias,mamma-mi-mamma-as-mamma",
    })

    public void Should_Return_Correct_Salt(String username, String plainTextPassword, String expected) {
        String result = PasswordService.Salt(username, plainTextPassword);

        Assertions.assertEquals(expected, result);
    }
}
