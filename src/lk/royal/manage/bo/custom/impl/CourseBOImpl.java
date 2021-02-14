package lk.royal.manage.bo.custom.impl;

import lk.royal.manage.bo.custom.CourseBO;
import lk.royal.manage.dao.DAOFactory;
import lk.royal.manage.dao.DAOType;
import lk.royal.manage.dao.custom.impl.CourseDAOImpl;
import lk.royal.manage.dto.CourseDTO;
import lk.royal.manage.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {
    CourseDAOImpl courseDAO= DAOFactory.getInstance().getDAO(DAOType.COURSE);

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws Exception {
        return courseDAO.save(new Course(courseDTO.getCode(),courseDTO.getCourse_name(),courseDTO.getCourse_type(),courseDTO.getDuration()));

    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        return courseDAO.delete(id);
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws Exception {
        return courseDAO.update(new Course(courseDTO.getCode(),courseDTO.getCourse_name(),courseDTO.getCourse_type(),courseDTO.getDuration()));
    }

    @Override
    public CourseDTO getCourse(String id) throws Exception {
        Course course=courseDAO.get(id);
        return new CourseDTO(course.getCode(),course.getCourse_name(),course.getCourse_type(),course.getDuration());

    }

    @Override
    public ArrayList<CourseDTO> getAllCourses() throws Exception {
        List<Course> courseList=courseDAO.getAll();
        ArrayList<CourseDTO> dtoList=new ArrayList();
        for (Course course :courseList){
            dtoList.add(new CourseDTO(
                    course.getCode(),
                    course.getCourse_name(),
                    course.getCourse_type(),
                    course.getDuration()

            ));

        }
        return dtoList;
    }

    @Override
    public String getCourseID() throws Exception {
        String lastID = courseDAO.getCID();
        if (lastID == null) {
            return "C001";
        }
        int newID = Integer.parseInt(lastID.substring(1, 4)) + 1;

        if (newID < 10) {
            return "C00" + newID;
        } else if (newID < 100) {
            return "C0" + newID;
        } else {
            return "C" + newID;
        }
    }
}
