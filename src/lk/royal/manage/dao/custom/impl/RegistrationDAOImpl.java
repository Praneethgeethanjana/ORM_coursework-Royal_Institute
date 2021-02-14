package lk.royal.manage.dao.custom.impl;

import lk.royal.manage.FactoryConfiguration;
import lk.royal.manage.dao.custom.RegistrationDAO;
import lk.royal.manage.entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration registration) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(registration);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Registration registration) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(registration);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Registration registration = session.get(Registration.class, integer);
        session.delete(registration);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Registration get(Integer integer) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Registration registration = session.get(Registration.class, integer);
        transaction.commit();
        session.close();
        if(registration!=null){
            return registration;
        }
        return null;
    }





    @Override
    public List<Registration> getAll() throws Exception {
        return null;
    }


    @Override
    public int getRegNO() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query=session.createSQLQuery("SELECT regNo FROM registration ORDER BY regNo DESC LIMIT 1");
        int rst= (int) query.uniqueResult();

        transaction.commit();
        return rst;
    }
}
