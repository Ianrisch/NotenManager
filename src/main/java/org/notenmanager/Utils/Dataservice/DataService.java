package org.notenmanager.Utils.Dataservice;

import org.notenmanager.Models.*;

import java.util.List;

public interface DataService {

    public void CreateUser(User user);
    public boolean UserExist(String username);
    public boolean AuthenticateUser(String username, String password);
    public User GetUser(String username);
    public void DeleteUser(String username);



    public List<SchoolSubject> GetSchoolSubjectsFromUser(User user);
    public void CreateSchoolSubjectForUser(User user, String schoolSubjectName, Person teacher, List<Grade> grades);
    public void UpdateSchoolSubjectForUser(User user,String nameOfSubjectToUpdate,SchoolSubject schoolSubject);
    public void DeleteSchoolSubjectForUser(User user,String nameOfSubject);






}
