package org.notenmanager.Utils;


import org.notenmanager.Models.Grade;
import org.notenmanager.Models.SchoolSubject;

public class AverageCalculation {
    public static double average(SchoolSubject subject) {
        double allGradesAdded = 0;
        for (Grade grade : subject.grades) {
            allGradesAdded += grade.value;
        }
        long gradeCount = subject.grades.size();
        double average = allGradesAdded / gradeCount;
        return average;
    }

    public static double averageWithGravity(SchoolSubject subject) {
        double allGradesAdded = 0;
        long gradeCount = subject.grades.size();
        for (Grade grade : subject.grades) {
            allGradesAdded += grade.value * grade.gravity;
            gradeCount += grade.gravity-1;
        }

        double average = allGradesAdded / gradeCount;
        return average;
    }
}
