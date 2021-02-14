package lk.royal.manage.bo.custom.impl;

import lk.royal.manage.bo.custom.RegistrationBO;
import lk.royal.manage.dao.DAOFactory;
import lk.royal.manage.dao.DAOType;
import lk.royal.manage.dao.custom.impl.RegistrationDAOImpl;
import lk.royal.manage.dto.CourseDTO;
import lk.royal.manage.dto.RegistrationDTO;
import lk.royal.manage.dto.StudentDTO;
import lk.royal.manage.entity.Course;
import lk.royal.manage.entity.Registration;
import lk.royal.manage.entity.Student;

import java.util.ArrayList;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAOImpl regDAO= DAOFactory.getInstance().getDAO(DAOType.REGISTRATION);


    @Override
    public boolean saveRegistration(RegistrationDTO registrationDTO) throws Exception {
        StudentDTO studentDTO = registrationDTO.getStudentDTO();
        Student student = new Student(studentDTO.getId(), studentDTO.getStudent_name(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob(), studentDTO.getGender());
        CourseDTO courseDTO = registrationDTO.getCourseDTO();
        Course course = new Course(courseDTO.getCode(), courseDTO.getCourse_name(), courseDTO.getCourse_type(), courseDTO.getDuration());
        return regDAO.save(new Registration(registrationDTO.getRegNo(),registrationDTO.getRegDate(),registrationDTO.getRegFee(),student,course));


    }

    @Override
    public boolean deleteRegistration(int id) throws Exception {
        return regDAO.delete(id);
    }


    @Override
    public boolean updateRegistration(RegistrationDTO registrationDTO) throws Exception {
//        return regDAO.update(new Registration(registrationDTO.getRegNo(),registrationDTO.getRegDate(),registrationDTO.getRegFee(),registrationDTO.getStudentDTO(),registrationDTO.getCourseDTO()));
   return false;
    }

    @Override
    public RegistrationDTO getRegistration(int id) throws Exception {
        Registration registration=regDAO.get(id);
        Student student=registration.getStudent();
        Course course=registration.getCourse();

        StudentDTO studentDTO=new StudentDTO(student.getId(),student.getStudent_name(),student.getAddress(),student.getContact(),student.getDob(),student.getGender());
        CourseDTO courseDTO=new CourseDTO(course.getCode(),course.getCourse_name(),course.getCourse_type(),course.getDuration());

        return new RegistrationDTO(registration.getRegNo(),registration.getRegDate(),registration.getRegFee(),studentDTO,courseDTO);
    }


    @Override
    public ArrayList<RegistrationDTO> getAllRegistrations() throws Exception {
        return null;
    }

    @Override
    public int getRegNO() throws Exception {
        int lastID = regDAO.getRegNO();
        if (lastID == 0) {
            return 001;
        }

        int newID = (lastID + 1);

        return newID;
    }


}
