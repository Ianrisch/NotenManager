package Utils;

import Models.Grade;
import Models.SchoolSubject;

public class AverageCalculation {
    public static double average(SchoolSubject subject) {
        double allGradesAdded = 0;
        for(Grade grade : subject.grades) {
            allGradesAdded += grade.Value;
        }
        long gradeCount = subject.grades.size();
        double average = allGradesAdded/gradeCount;
        return average;
    }
}
