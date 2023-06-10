package org.notenmanager.Utils.Dataservice;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.notenmanager.Exceptions.UserAlreadyExistsException;
import org.notenmanager.Models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonService implements DataService {
    private List<User> users = null;
    private List<SchoolSubject> subjects = null;


    public JsonService() {
        users = GetUserFromFile(getUserListPath());

    }

    private static <T> void WriteToFile(List<T> list, String fileLocation) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileLocation), list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<User> GetUserFromFile(String fileLocation) {

        List<User> list = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            list = mapper.readValue(new File(fileLocation), new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File is not created creating a file!");
            try {
                FileWriter writer = new FileWriter(fileLocation);
                writer.write("[]");
                writer.close();
            } catch (IOException e2) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private static List<SchoolSubject> GetSchoolSubjectFromFile(String fileLocation) {

        List<SchoolSubject> list = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            list = mapper.readValue(new File(fileLocation), new TypeReference<List<SchoolSubject>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File is not created creating a file!");
            try {
                FileWriter writer = new FileWriter(fileLocation);
                writer.write("[]");
                writer.close();
            } catch (IOException e2) {
                e.printStackTrace();
            }
        }
        return list;
    }


    @Override
    public void CreateUser(User user) {
        if (!UserExist(user.username)) {
            users.add(user);
            WriteToFile(users, getUserListPath());
        } else {
            throw new UserAlreadyExistsException(user);
        }
    }

    @Override
    public boolean UserExist(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean EmailExist(String email) {
        for (User user : users) {
            if (user.mail.equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean AuthenticateUser(String username, String password) {
        if (UserExist(username)) {
            User user = GetUser(username);
            return password.equals(user.password);
        }
        return false;
    }

    @Override
    public User GetUser(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void DeleteUser(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                users.remove(user);
                WriteToFile(users, getUserListPath());
            }
        }
    }

    @Override
    public List<SchoolSubject> GetSchoolSubjectsFromUser(User user) {
        subjects = GetSchoolSubjectFromFile(getSchoolSubjectListPath(user.username));
        return subjects;

    }

    @Override
    public void CreateSchoolSubjectForUser(User user, String schoolSubjectName, Person teacher, List<Grade> grades) {
        subjects.add(new SchoolSubject(teacher, schoolSubjectName, grades));
        WriteToFile(subjects, getSchoolSubjectListPath(user.username));
    }

    @Override
    public void UpdateSchoolSubjectForUser(User user, String nameOfSubjectToUpdate, SchoolSubject schoolSubject) {
        for (SchoolSubject subject : subjects) {

            if (subject.name.equals(nameOfSubjectToUpdate)) {
                subjects.remove(subject);
                subjects.add(schoolSubject);
                break;
            }
        }
        WriteToFile(subjects, getSchoolSubjectListPath(user.username));
    }

    @Override
    public void DeleteSchoolSubjectForUser(User user, String nameOfSubject) {
        for (SchoolSubject subject : subjects) {
            if (subject.name.equals(nameOfSubject)) {
                subjects.remove(subject);
                break;
            }
        }
        WriteToFile(subjects, getSchoolSubjectListPath(user.username));

    }

    private static String getSchoolSubjectListPath(String username) {
        return System.getProperty("user.dir") + "/data-" + username + ".json";
    }

    private static String getUserListPath() {
        return System.getProperty("user.dir") + "/users.json";
    }

}
