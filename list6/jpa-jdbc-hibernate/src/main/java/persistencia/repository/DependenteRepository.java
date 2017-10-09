package persistencia.repository;

import persistencia.model.Dependente;
import persistencia.repository.dao.JPADao;

public class DependenteRepository extends JPADao<Dependente> {

    public DependenteRepository() {
        this.persistenceClass = Dependente.class;
    }
}
