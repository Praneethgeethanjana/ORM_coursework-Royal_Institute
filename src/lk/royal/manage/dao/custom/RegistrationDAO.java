package lk.royal.manage.dao.custom;

import lk.royal.manage.dao.CrudDAO;
import lk.royal.manage.entity.Registration;

public interface RegistrationDAO extends CrudDAO<Registration,Integer> {
    public int getRegNO()throws Exception;
}
