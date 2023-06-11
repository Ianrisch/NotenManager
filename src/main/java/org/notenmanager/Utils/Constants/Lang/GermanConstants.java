package org.notenmanager.Utils.Constants.Lang;

public class GermanConstants extends LanguageConstants  {

    public GermanConstants(){
        super();
        Login = "Anmelden";
        Registration = "Registrieren";
        EMail = "Email";
        Username = "Nutzername";
        Password = "Passwort";
        RepeatedPassword = "Passwort wiederholen";
        SchoolClass = "Klasse";
        Submit = "Bestätigen";
        PasswordMismatch = "Die Passwörter stimmen nicht Überein!";
        InnateWarning = "Warnung";
        Teacher = "Lehrer";
        Subject = "Fach";
    }

    @Override
    public String UsernameAlreadyExists(String username) {
        return  "Der Nutzername \"" + username + "\" existiert bereits!";
    }
    @Override
    public String EmailAlreadyExists(String eMail) {
        return "Die EMail \"" + eMail + "\" existiert bereits!";
    }
}
