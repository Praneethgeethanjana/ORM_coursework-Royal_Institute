package lk.royal.manage;

import lk.royal.manage.entity.Course;
import lk.royal.manage.entity.Registration;
import lk.royal.manage.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Properties properties=new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("royalinstitute.properties"));

        sessionFactory = new Configuration().mergeProperties(properties)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Registration.class)
                    .buildSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }

        }

    public static FactoryConfiguration getInstance(){
        return (null==factoryConfiguration)? factoryConfiguration=new FactoryConfiguration(): factoryConfiguration;

    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
