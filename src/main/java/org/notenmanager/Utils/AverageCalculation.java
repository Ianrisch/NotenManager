package org.notenmanager.Utils;


import org.notenmanager.Models.Grade;
import org.notenmanager.Models.SchoolSubject;

public class AverageCalculation {
    public static double average(SchoolSubject subject) {
        double allGradesAdded = 0;
        for(Grade grade : subject.grades) {
            allGradesAdded += grade.value;
        }
        long gradeCount = subject.grades.size();
        double average = allGradesAdded/gradeCount;
        return average;
    }
}
