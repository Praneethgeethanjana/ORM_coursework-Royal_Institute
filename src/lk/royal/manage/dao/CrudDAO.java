package lk.royal.manage.dao;

import lk.royal.manage.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO <T extends SuperEntity,ID extends Serializable> extends SuperDAO{
    public boolean save(T t)throws Exception;

    public boolean update(T t)throws Exception;

    public boolean delete(ID id)throws Exception;

    public T get(ID id)throws Exception;

    public List<T> getAll()throws Exception;


}
