package lk.royal.manage.dao.custom.impl;

import lk.royal.manage.FactoryConfiguration;
import lk.royal.manage.dao.custom.CourseDAO;
import lk.royal.manage.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean save(Course course) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(course);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course course) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(course);

        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, s);

        session.delete(course);
        transaction.commit();
        session.close();
        return true;

    }


    @Override
    public Course get(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, s);
        transaction.commit();
        session.close();
        return course;

    }


    @Override
    public List<Course> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Course> list = session.createCriteria(Course.class).list();
        transaction.commit();
        session.close();
        return list;
    }
//        Query query=session.createQuery("FROM Course");
//        List list=query.list();
//        transaction.commit();
//        session.close();





    @Override
    public String getCID() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("SELECT code FROM course ORDER BY code DESC LIMIT 1");
        String rst = (String) query.uniqueResult();
        transaction.commit();
        return rst;
    }


}
