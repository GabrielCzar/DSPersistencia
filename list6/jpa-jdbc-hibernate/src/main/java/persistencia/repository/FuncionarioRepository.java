package persistencia.repository;

import persistencia.model.Funcionario;
import persistencia.repository.dao.JPADao;

public class FuncionarioRepository extends JPADao<Funcionario> {

    public FuncionarioRepository() {
        this.persistenceClass = Funcionario.class;
    }

}
