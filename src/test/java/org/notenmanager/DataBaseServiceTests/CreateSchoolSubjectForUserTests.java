package org.notenmanager.DataBaseServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.notenmanager.Models.SchoolSubject;

import java.util.ArrayList;
import java.util.List;

public class CreateSchoolSubjectForUserTests extends DatabaseServiceTests {

    @Test
    public void Should_Create_SchoolSubject() {
        SetupData();

        SchoolSubject newSubject = new SchoolSubject(person, "Neues Fach", new ArrayList());

        sut.CreateSchoolSubjectForUser(existentUser, newSubject);

        List<SchoolSubject> results = sut.GetSchoolSubjectsFromUser(existentUser);

        AssertSubjectCreation(newSubject, results);
    }

    @Test
    public void Should_Override_SchoolSubject_if_it_Exists() {
        SetupData();

        SchoolSubject newSubject = new SchoolSubject(person, subject.name, new ArrayList());

        sut.CreateSchoolSubjectForUser(existentUser, newSubject);

        List<SchoolSubject> results = sut.GetSchoolSubjectsFromUser(existentUser);

        AssertSubjectCreation(newSubject, results);
    }

    private void AssertSubjectCreation(SchoolSubject expected, List<SchoolSubject> actual) {
        Assertions.assertTrue(AssertSubjectExists(expected, actual), "Subject wasn't Created!");
    }
}
