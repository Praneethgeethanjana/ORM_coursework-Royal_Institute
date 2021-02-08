package lk.royal.manage.dao.custom.impl;

import lk.royal.manage.FactoryConfiguration;
import lk.royal.manage.dao.custom.StudentDAO;
import lk.royal.manage.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student student) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, s);

        session.delete(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student get(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student=session.get(Student.class,s);
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public List<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> list=session.createCriteria(Student.class).list();
        transaction.commit();
        session.close();
        return list;
    }
}
