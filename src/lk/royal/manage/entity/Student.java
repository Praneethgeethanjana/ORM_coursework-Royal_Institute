package lk.royal.manage.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Student implements SuperEntity {
    @Id
      private String id;
      private String student_name;
      private String address;
      private String contact;
      private String  dob;
      private String gender;
      @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
      private List<Registration> registrations;


    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String student_name, String address, String contact, String dob, String gender) {
        this.id = id;
        this.student_name = student_name;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
    }

    public Student(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", registrations=" + registrations +
                '}';
    }
}
