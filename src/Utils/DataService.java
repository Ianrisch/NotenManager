package Utils;

import Models.Grade;
import Models.Person;
import Models.SchoolSubject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataService {
    private static JSONObject ParseSchoolSubjectToJson(SchoolSubject schoolSubject) {

        JSONObject tempObj = new JSONObject();
        tempObj.put("name", schoolSubject.name);
        tempObj.put("teacher", ParsePersonToJson(schoolSubject.teacher));
        tempObj.put("grades", ParseGradeListToJson(schoolSubject.grades));

        return tempObj;
    }

    private static List<SchoolSubject> ParseSchoolSubjectsFromJson(String jsonString) {
        JSONArray objectArray = new JSONArray(jsonString);
        List<SchoolSubject> result = new ArrayList();

        for (int i = 0; i < objectArray.length(); i++) {
            JSONObject object = objectArray.getJSONObject(i);
            result.add(JsonToSchoolSubject(object));
        }

        return result;
    }

    private static SchoolSubject JsonToSchoolSubject(JSONObject object) {
        Person teacher = JsonToPerson(object.getJSONObject("teacher"));
        String name = object.getString("name");
        List<Grade> grades = JsonToGradeList(object.getJSONArray("grades"));
        SchoolSubject subject = new SchoolSubject(
                teacher,
                name,
                grades
        );

        return subject;
    }

    private static List<Grade> JsonToGradeList(JSONArray grades) {
        List<Grade> gradesResult = new ArrayList();
        for (int i = 0; i < grades.length(); i++) {
            JSONObject grade = grades.getJSONObject(i);
            gradesResult.add(JsonToGrade(grade));
        }
        return gradesResult;
    }

    private static Grade JsonToGrade(JSONObject grade) {
        float value = grade.getFloat("value");
        double gravity = grade.getDouble("gravity");
        Grade result = new Grade(value, gravity);
        return result;
    }

    private static Person JsonToPerson(JSONObject teacher) {
        String firstName = teacher.getString("firstName");
        String lastName = teacher.getString("lastName");
        Person person = new Person(firstName, lastName);
        return person;
    }

    private static JSONObject ParseGradeToJson(Grade grade) {
        JSONObject tempObj = new JSONObject();
        tempObj.put("gravity", grade.Gravity);
        tempObj.put("value", grade.Value);

        return tempObj;
    }

    private static JSONObject ParsePersonToJson(Person person) {
        JSONObject tempObj = new JSONObject();
        tempObj.put("firstName", person.firstName);
        tempObj.put("lastName", person.lastName);

        return tempObj;
    }

    private static JSONArray ParseGradeListToJson(List<Grade> grades) {
        JSONArray jsonArray = new JSONArray();
        for (Grade grade : grades) {
            jsonArray.put(ParseGradeToJson(grade));
        }
        return jsonArray;
    }

    public static void WriteToFile(String path, SchoolSubject schoolSubject) {
        try (FileWriter out = new FileWriter(path)) {
            JSONObject jsonobject = ParseSchoolSubjectToJson(schoolSubject);
            out.write(jsonobject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONArray ParseMultipleSchoolSubjectsToJson(List<SchoolSubject> schoolSubjects) {
        JSONArray newObj = new JSONArray();
        for (int i = 0; i < schoolSubjects.size(); i++) {
            JSONObject object = ParseSchoolSubjectToJson(schoolSubjects.get(i));
            newObj.put(object);
        }
        return newObj;
    }


    public static void WriteToFile(String path, List<SchoolSubject> schoolSubjects) {
        ObjectMapper mapper = new ObjectMapper();

        // Java object to JSON file
        try {
            mapper.writeValue(new File("c:\\test\\staff.json"), schoolSubjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}