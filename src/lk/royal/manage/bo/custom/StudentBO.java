package lk.royal.manage.bo.custom;

import lk.royal.manage.bo.SuperBO;
import lk.royal.manage.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    public boolean saveStudent(StudentDTO studentDTO)throws Exception;
    public boolean deleteStudent(String id)throws Exception;
    public boolean updateStudent(StudentDTO studentDTO)throws Exception;
    public StudentDTO getStudent(String id)throws Exception;
    public ArrayList<StudentDTO> getAllStudents()throws Exception;
    public String getStudentID()throws Exception;
}
