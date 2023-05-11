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
    public static JSONObject ParseSchoolSubjectToJson(SchoolSubject schoolSubject) {

        JSONObject tempObj = new JSONObject();
        tempObj.put("name", schoolSubject.name);
        tempObj.put("teacher", ParsePersonToJson(schoolSubject.teacher));
        tempObj.put("grades", ParseGradeListToJson(schoolSubject.grades));

        return tempObj;
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
        for (Grade grade :
                grades) {
            jsonArray.put(ParseGradeToJson(grade));
        }
        return jsonArray;
    }

    public static void WriteToFile(String path, JSONObject tempObj) {
        try (FileWriter out =new FileWriter(path)) {
            out.write(tempObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void WriteToFile(String path, ArrayList<SchoolSubject> schoolSubjects) {
        ObjectMapper mapper = new ObjectMapper();

        // Java object to JSON file
        try {
            mapper.writeValue(new File("c:\\test\\staff.json"), schoolSubjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}