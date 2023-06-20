package org.notenmanager.DataBaseServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.notenmanager.Models.SchoolSubject;

import java.util.ArrayList;
import java.util.List;

public class UpdateSchoolSubjectForUserTests extends DatabaseServiceTests {

    @Test
    public void Should_Save_Changes_To_Grades() {
        SetupData();

        subject.grades = new ArrayList();

        sut.UpdateSchoolSubjectForUser(existentUser, subjectName, subject);

        List<SchoolSubject> results = sut.GetSchoolSubjectsFromUser(existentUser);

        AssertUpdate(subject, results);
    }

    @Test
    public void Should_Save_Changes_To_Self() {
        SetupData();

        subject.name = "Test";

        sut.UpdateSchoolSubjectForUser(existentUser, subjectName, subject);

        List<SchoolSubject> results = sut.GetSchoolSubjectsFromUser(existentUser);

        AssertUpdate(subject, results);
    }

    private void AssertUpdate(SchoolSubject expected, List<SchoolSubject> actual) {
        Assertions.assertTrue(AssertSubjectExists(expected, actual), "Method didn't update Correctly");
    }
}
