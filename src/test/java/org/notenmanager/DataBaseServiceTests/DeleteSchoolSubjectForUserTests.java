package org.notenmanager.DataBaseServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.notenmanager.Models.SchoolSubject;

public class DeleteSchoolSubjectForUserTests extends DatabaseServiceTests {

    @Test
    public void Should_Delete_Subject() {
        SetupData();

        sut.DeleteSchoolSubjectForUser(existentUser, subject.name);

        for (SchoolSubject schoolSubject : sut.GetSchoolSubjectsFromUser(existentUser)) {
            assertSubjectHasDifferentName(subject.name, schoolSubject);
        }
    }

    private void assertSubjectHasDifferentName(String notExpected, SchoolSubject schoolSubjectToTest) {
        Assertions.assertNotEquals(notExpected, schoolSubjectToTest.name);
    }
}
