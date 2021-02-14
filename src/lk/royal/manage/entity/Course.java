package lk.royal.manage.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course implements SuperEntity {
    @Id
    private String code;
    private String course_name;
    private String course_type;
    private String duration;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Registration>registrations;


    public Course() {
    }

    public Course(String code) {
        this.code = code;
    }

    public Course(String code, String course_name, String course_type, String duration) {
        this.code = code;
        this.course_name = course_name;
        this.course_type = course_type;
        this.duration = duration;
    }

    public Course(List<Registration> registrations) {
        this.registrations = registrations;
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

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_type='" + course_type + '\'' +
                ", duration='" + duration + '\'' +
                ", registrations=" + registrations +
                '}';
    }
}
