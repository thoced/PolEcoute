package dao;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T>{

    public abstract void insert(T model) throws SQLException;
    public abstract void delete(long id) throws SQLException;
    public abstract void update(T model) throws SQLException;
    public abstract T find(long id) throws SQLException;
    public abstract List<T> selectAll() throws SQLException;
    public abstract List<T> selectFromForeignKey(long id) throws SQLException;

}
