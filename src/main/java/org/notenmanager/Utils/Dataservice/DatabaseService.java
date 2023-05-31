package org.notenmanager.Utils.Dataservice;


import org.notenmanager.Models.*;

import java.util.List;

public class DatabaseService implements DataService{
    @Override
    public void CreateUser(User user) {

    }

    @Override
    public void UserExist(String username) {

    }

    @Override
    public boolean AuthenticateUser(String username, String password) {
        return false;
    }

    @Override
    public User GetUser(String username) {
        return null;
    }

    @Override
    public void DeleteUser(String username) {

    }

    @Override
    public List<SchoolSubject> GetSchoolSubjectsFromUser(User user) {
        return null;
    }

    @Override
    public void CreateSchoolSubjectForUser(User user, String schoolSubjectName, Person teacher, List<Grade> grades) {

    }

    @Override
    public void UpdateSchoolSubjectForUser(User user, String nameOfSubjectToUpdate, SchoolSubject schoolSubject) {

    }

    @Override
    public void DeleteSchoolSubjectForUser(User user, String nameOfSubject) {

    }
}
