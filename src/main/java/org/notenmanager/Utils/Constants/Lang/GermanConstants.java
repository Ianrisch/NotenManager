package org.notenmanager.Utils.Constants.Lang;

import org.notenmanager.UI.Compenents.LabledField.LabeledTextField;

public class GermanConstants extends LanguageConstants  {

    public GermanConstants(){
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
    }

    @Override
    public String UsernameAlreadyExists(String username) {
        return  "Der Nutzername \"" + username + "\" existiert bereits!";
    }
    @Override
    public String EmailArleadyExists(LabeledTextField eMail) {
        return "Die EMail \"" + eMail.getText() + "\" existiert bereits!";
    }
}
