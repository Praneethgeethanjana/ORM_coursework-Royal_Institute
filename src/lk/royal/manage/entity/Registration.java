package lk.royal.manage.entity;

import javax.persistence.*;

@Entity
public class Registration implements SuperEntity{
    @Id
    private int regNo;
    private String regDate;
    private double regFee;
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    public Registration() {
    }

    public Registration(int regNo, String regDate, double regFee, Student student, Course course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.student = student;
        this.course = course;
    }

//    public Registration(int regNo, String regDate, double regFee, String id, String code) {
//
//    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regNo=" + regNo +
                ", regDate='" + regDate + '\'' +
                ", regFee=" + regFee +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
