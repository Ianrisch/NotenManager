package org.notenmanager.Utils.Constants.Lang;

public class GermanConstants extends LanguageConstants {

    public GermanConstants() {
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
        Empty = "Diese Liste ist Leer füge ein neues Item hinzu!";
        AddGrade = "Note hinzufügen";
        RemoveGrade = "Note/n entfernen";
        AddSubject = "Klasse hinzufügen";
        RemoveSubject = "Klasse entfernen";
        Grade = "Note: ";
        Gravity = "Gewichtung: ";
        Back = "Zurück";
        Average = "Durchschnitt: ";
        AverageWithGravity = "Durchschnitt mit Notengewichtung: ";
        PickALanguage = "Wähle eine Sprache: ";
        Persistence = "Persistenz: ";
        Local = "Lokal";
        Online = "Online";

    }

    @Override
    public String NotenManagerTitle(String username) {
        return "NotenManager von Nutzer: " + username;
    }

    @Override
    public String UsernameAlreadyExists(String username) {
        return "Der Nutzername \"" + username + "\" existiert bereits!";
    }

    @Override
    public String EmailAlreadyExists(String eMail) {
        return "Die EMail \"" + eMail + "\" existiert bereits!";
    }
}
