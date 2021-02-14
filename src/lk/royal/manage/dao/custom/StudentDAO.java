package lk.royal.manage.dao.custom;

import lk.royal.manage.dao.CrudDAO;
import lk.royal.manage.entity.Student;

public interface StudentDAO extends CrudDAO<Student,String> {
    public String getSID()throws Exception;
}
