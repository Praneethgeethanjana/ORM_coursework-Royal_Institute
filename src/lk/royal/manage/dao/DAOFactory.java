package lk.royal.manage.dao;

import lk.royal.manage.dao.custom.impl.CourseDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return(null==daoFactory) ? daoFactory=new DAOFactory() : daoFactory; }

        public <T extends SuperDAO> T getDAO(DAOType daoType){
         switch (daoType){
             case COURSE:
                 return (T) new CourseDAOImpl();
             default:
                 return null;

         }
        }

}


