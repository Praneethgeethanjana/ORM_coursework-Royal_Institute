package lk.royal.manage.bo.custom;

import lk.royal.manage.bo.SuperBO;
import lk.royal.manage.dto.CourseDTO;

import java.util.ArrayList;

public interface CourseBO extends SuperBO {
    public boolean saveCourse(CourseDTO courseDTO) throws Exception;
    public boolean deleteCourse(String id)throws Exception;
    public boolean updateCourse(CourseDTO courseDTO)throws Exception;
    public CourseDTO getCourse(String id) throws Exception;
    public ArrayList<CourseDTO> getAllCourses() throws Exception;
    public String getCourseID()throws Exception;

}
