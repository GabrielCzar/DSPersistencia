package persistencia.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final String DEV_H2 = "dev-h2", DEV_POSTGRES = "dev-postgres";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(DEV_H2);
    private static ThreadLocal<EntityManager> ems = new ThreadLocal<EntityManager>();

    private JpaUtil () {}

    public static EntityManager getEntityManager () {
        EntityManager em = ems.get();
        if (em == null) {
            em = emf.createEntityManager();
            ems.set(em);
        }
        return em;
    }

    public static void closeEntity()
    {
        EntityManager em = ems.get();
        if (em != null) {
            EntityTransaction tx = em.getTransaction();
            if (tx.isActive())
                tx.commit();
            em.close();
            ems.set(null);
        }
    }

    public static void beginTransaction() {
        if (!getEntityManager().getTransaction().isActive())
            getEntityManager().getTransaction().begin();
    }

    public static void commit() {
        if (getEntityManager().getTransaction().isActive())
            getEntityManager().getTransaction().commit();
    }

    public static void rollback(){
        if (getEntityManager().getTransaction().isActive())
            getEntityManager().getTransaction().rollback();
    }
}
