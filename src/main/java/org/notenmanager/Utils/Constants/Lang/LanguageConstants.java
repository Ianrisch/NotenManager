package org.notenmanager.Utils.Constants.Lang;

import org.notenmanager.Models.Grade;

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
    public String Empty;
    public String AddGrade;
    public String RemoveGrade;
    public String AddSubject;
    public String RemoveSubject;
    public String Grade;
    public String Gravity;
    public String SubjectName;

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
        Empty = "List is Empty Add new Item!";
        AddGrade = "Add Grade";
        RemoveGrade = "Remove Grade/s";
        AddSubject = "Add Subject";
        RemoveSubject = "Remove Subject";
        Grade = "Grade: ";
        Gravity = "Gravity: ";
        SubjectName = "Subject Name: ";

    }

    public String UsernameAlreadyExists(String username) {
        return "The Username \"" + username + "\" already Exists!";
    }

    public String EmailAlreadyExists(String eMail) {
        return "The EMail \"" + eMail + "\" already Exists!";
    }
}
