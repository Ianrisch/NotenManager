package org.notenmanager.Utils.Dataservice;

import org.notenmanager.Models.SchoolSubject;
import org.notenmanager.Models.User;

import java.util.List;

public interface DataService {

    public void CreateUser(User user);

    public boolean UserExist(String username);

    public boolean EmailExist(String email);

    public boolean AuthenticateUser(String username, String password);

    public User GetUser(String username);

    public void DeleteUser(String username);


    public List<SchoolSubject> GetSchoolSubjectsFromUser(User user);

    public void CreateSchoolSubjectForUser(User user, SchoolSubject schoolSubject);

    public void UpdateSchoolSubjectForUser(User user, String nameOfSubjectToUpdate, SchoolSubject schoolSubject);

    public void DeleteSchoolSubjectForUser(User user, String nameOfSubject);


}
