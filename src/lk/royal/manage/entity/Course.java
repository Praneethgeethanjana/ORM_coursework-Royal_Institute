package lk.royal.manage.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course implements SuperEntity {
    @Id
    private String code;
    private String course_name;
    private String course_type;
    private String duration;

    public Course() {
    }

    public Course(String code, String course_name, String course_type, String duration) {
        this.code = code;
        this.course_name = course_name;
        this.course_type = course_type;
        this.duration = duration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_type='" + course_type + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
