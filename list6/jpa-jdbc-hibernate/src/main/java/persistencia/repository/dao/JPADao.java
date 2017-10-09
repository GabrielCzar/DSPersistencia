package persistencia.repository.dao;

import persistencia.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JPADao<T> implements IDao<T>{

    protected Class<T> persistenceClass;

    public T save(T entity) {
        return getEM().merge(entity);
    }

    public T get(Object id) {
        return getEM().find(persistenceClass, id);
    }

    public void remove(T entity) {
        getEM().remove(JpaUtil.getEntityManager().merge(entity));
    }

    public void update(T entity) {
        getEM().merge(entity);
    }

    public List<T> all() {
        CriteriaQuery<T> cq = getEM().getCriteriaBuilder().createQuery(persistenceClass);
        cq.from(persistenceClass);
        return JpaUtil.getEntityManager().createQuery(cq).getResultList();
    }

    public EntityManager getEM() {
        return JpaUtil.getEntityManager();
    }

    public void beginTransaction() {
        JpaUtil.beginTransaction();
    }

    public void commit() {
        JpaUtil.commit();
    }

    public void rollback() {
        JpaUtil.rollback();
    }

    public void close() {
        JpaUtil.closeEntity();
    }
}
