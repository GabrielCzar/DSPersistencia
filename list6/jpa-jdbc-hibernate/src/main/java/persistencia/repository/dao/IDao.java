package persistencia.repository.dao;

import java.util.List;

public interface IDao<T> {
    void remove(T entity);
    void update(T entity);
    T save(T entity);
    T get(Object id);
    List<T> all();

    public void beginTransaction();
    public void rollback();
    public void commit();
    public void close();
}
