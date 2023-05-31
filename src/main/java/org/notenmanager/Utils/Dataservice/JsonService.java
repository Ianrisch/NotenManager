package org.notenmanager.Utils.Dataservice;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.notenmanager.Models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonService implements DataService {
    private List<User> users=null;
    private List<SchoolSubject> subjects=null;

    private static void WriteToFile(List<SchoolSubject> schoolSubjects, String username) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(getSchoolSubjectListPath(username)), schoolSubjects);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<SchoolSubject> GetFromFile(String username) {
        List<SchoolSubject> listSchool = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            listSchool = mapper.readValue(new File(getSchoolSubjectListPath(username)), new TypeReference<List<SchoolSubject>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File is not created creating a file!");
            try {
                FileWriter writer = new FileWriter(getSchoolSubjectListPath(username));
                writer.write("[]");
                writer.close();
            } catch (IOException e2) {
                e.printStackTrace();
            }
        }
        return listSchool;
    }

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

    private static String getSchoolSubjectListPath(String username) {
        return System.getProperty("user.dir") + "/data-" + username + ".json";
    }

    private static String getUserListPath() {
        return System.getProperty("user.dir") + "/users.json";
    }

}
