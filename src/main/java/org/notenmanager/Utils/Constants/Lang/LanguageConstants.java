package org.notenmanager.Utils.Constants.Lang;

public class LanguageConstants {

    public String Login;
    public String Registration;
    public String EMail;
    public String Username;
    public String Password;
    public String RepeatedPassword;

    public String SchoolClass;
    public String Submit;
    public String PasswordMismatch;
    public String InnateWarning;
    public String Teacher;
    public String Subject;

    public LanguageConstants() {
        Login = "Login";
        Registration = "Registration";
        EMail = "Email";
        Username = "Username";
        Password = "Password";
        RepeatedPassword = "Repeat Password";
        SchoolClass = "Class";
        Submit = "Submit";
        PasswordMismatch = "Passwords are not matching!";
        InnateWarning = "Innate Warning";
        Teacher = "Teacher";
        Subject = "Subject";
    }

    public String UsernameAlreadyExists(String username) {
       return  "The Username \"" + username + "\" already Exists!";
    }

    public String EmailAlreadyExists(String eMail) {
        return "The EMail \"" + eMail + "\" already Exists!";
    }
}
