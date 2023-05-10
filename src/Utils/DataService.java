package Utils;
import Models.Person;
import Models.Grade;
import Models.SchoolSubject;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
public class DataService {
    public static JSONObject ParseSchoolSubjectToJson(SchoolSubject schoolSubject) {

        String path = "C:/Users/DataService.json";

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

    private static void PrintJasonDatei(String path,JSONObject tempObj) {
    try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
        out.write(tempObj.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}