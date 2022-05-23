package kurilovich.bsu.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> {
    public abstract void insert(T entity);
    public abstract T getById(int id);
    public abstract void update(T entity);
    public abstract void delete(T entity);
    public abstract ArrayList<T> getAll();
}
