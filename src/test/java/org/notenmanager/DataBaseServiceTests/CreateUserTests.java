package org.notenmanager.DataBaseServiceTests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.notenmanager.Exceptions.AlreadyExistsException;
import org.notenmanager.Exceptions.UserAlreadyExistsException;
import org.notenmanager.Models.SchoolClass;
import org.notenmanager.Models.User;

public class CreateUserTests extends DatabaseServiceTests{
    @Test
    public void Should_Create_User(){
        User tempUser = new User( "Wlad","test","df",new SchoolClass("someclass"));
        sut.CreateUser(tempUser);

        User result = sut.GetUser(tempUser.username);

        Assertions.assertNotNull(result);

        Assertions.assertEquals(tempUser.username,result.username);
        Assertions.assertEquals(tempUser.schoolClass.name,result.schoolClass.name);
        Assertions.assertEquals(tempUser.mail,result.mail);
        Assertions.assertEquals(tempUser.password,result.password);
    }
    @Test
    public void Should_Throw_when_User_exists_already(){
        SetupData();
        User tempUser = existentUser;
        String expectedMessage = "User with username:  ["+tempUser.username+"] already exists!";
        String actualMessage = "";
        boolean IsUserAlreadyExistsException = false;
        try {
            sut.CreateUser(tempUser);
        }catch (Exception e){
            if (e instanceof UserAlreadyExistsException){
                IsUserAlreadyExistsException = true;
            }
            actualMessage = e.getMessage();
        }


        Assertions.assertTrue(IsUserAlreadyExistsException);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
