import Models.Grade;
import Models.Person;
import Models.SchoolSubject;
import Utils.AverageCalculation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<Grade> meineNoten = new ArrayList<Grade>();
        Grade note1 = new Grade(6,1);
        Grade note2 = new Grade(1,1);
        meineNoten.add(note1);
        meineNoten.add(note2);
        Person meinTeacher = new Person("Tom", "Müller");
        SchoolSubject meinSubject = new SchoolSubject(meinTeacher, "LF5", meineNoten);
        System.out.println(AverageCalculation.average(meinSubject));
    }
}