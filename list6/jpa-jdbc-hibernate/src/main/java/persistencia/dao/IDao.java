package persistencia.dao;

import java.util.List;

public interface IDao<T> {
    void remove(T entity);
    void update(T entity);
    void save(T entity);
    T get(Object id);
    List<T> all();

}
