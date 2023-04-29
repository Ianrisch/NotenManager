package Models;

import java.util.List;

public class SchoolSubject {
    public String name;
    public Person teacher;
    public List<Grade> grades;

    public SchoolSubject(Person teacher, String name, List<Grade> grades){
        this.teacher=teacher;
        this.name=name;
        this.grades=grades;

    }

}
