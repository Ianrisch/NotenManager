package Models;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class SchoolSubject {

    public String name;

    public Person teacher;

    public List<Grade> grades;
    @JsonIgnore private boolean isInvalid;
    public SchoolSubject(Person teacher, String name, List<Grade> grades){
        this.teacher=teacher;
        this.name=name;
        this.grades=grades;
        this.isInvalid = false;
    }
    @JsonCreator
    private static SchoolSubject createSchoolSubject(@JsonProperty("teacher") Person teacher,@JsonProperty("name") String name,@JsonProperty("grades") List<Grade> grades){
        return new SchoolSubject(teacher,name,grades);
    }

    private SchoolSubject(boolean isInvalid){
        this.isInvalid = isInvalid;
    }

    /**
     * Checks if SchoolSubject Is Invalid
     * @return boolean
     */
    public boolean isInvalid(){
        return isInvalid;
    }
    /**
     * Creates an Invalid SchoolSubject
     * @return Invalid SchoolSubject
     */
    public static SchoolSubject CreateInvalid(){
        return new SchoolSubject(true);
    }

}
