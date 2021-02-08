package lk.royal.manage.dto;

import java.util.Date;

public class StudentDTO {
    private String id;
    private String student_name;
    private String address;
    private String contact;
    private String dob;
    private String gender;

    public StudentDTO() {
    }

    public StudentDTO(String id, String student_name, String address, String contact, String dob, String gender) {
        this.id = id;
        this.student_name = student_name;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
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

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id='" + id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
