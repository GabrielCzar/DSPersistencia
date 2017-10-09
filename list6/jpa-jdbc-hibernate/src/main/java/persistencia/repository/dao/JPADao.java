package persistencia.repository.dao;

import persistencia.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JPADao<T> implements IDao<T>{

    protected Class<T> persistenceClass;

    public void save(T entity) {
        getEM().merge(entity);
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
        getEM().getTransaction().begin();
    }

    public void commit() {
        if (getEM().getTransaction().isActive())
            getEM().getTransaction().commit();
    }

    public void rollback() {
        if (getEM().getTransaction().isActive())
            getEM().getTransaction().rollback();
    }

    public void close() {
        JpaUtil.closeEntity();
    }
}
