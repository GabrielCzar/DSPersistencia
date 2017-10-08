package persistencia.repository;

import persistencia.dao.JPADao;
import persistencia.model.Dependente;

public class DependenteRepository extends JPADao<Dependente> {

    public DependenteRepository() {
        this.persistenceClass = Dependente.class;
    }
}
