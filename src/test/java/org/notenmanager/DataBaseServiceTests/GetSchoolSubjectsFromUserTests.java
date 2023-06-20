package org.notenmanager.DataBaseServiceTests;

import org.junit.jupiter.api.Test;
import org.notenmanager.Models.SchoolClass;
import org.notenmanager.Models.SchoolSubject;
import org.notenmanager.Models.User;
import org.notenmanager.Utils.Dataservice.DatabaseService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetSchoolSubjectsFromUserTests extends DatabaseServiceTests{
    @Test
    public void Should_get_SchoolSubject_list_from_User() {

        SetupData();

        DatabaseService service = new DatabaseService(sessionFactory);
        List<SchoolSubject> result = service.GetSchoolSubjectsFromUser(existentUser);
        SchoolSubject schoolSubject = result.get(0);
        assertEquals(subject.name, schoolSubject.name);
        assertEquals(subject.grades.size(), schoolSubject.grades.size());
        assertEquals(subject.teacher.fullName(), schoolSubject.teacher.fullName());


    }

    @Test
    public void Should_return_null_when_User_doesnt_exist() {

        SetupData();

        User CustomUser = new User("notexistent", "", "", new SchoolClass());
        List<SchoolSubject> result = sut.GetSchoolSubjectsFromUser(CustomUser);
        assertNull(result);
    }
}