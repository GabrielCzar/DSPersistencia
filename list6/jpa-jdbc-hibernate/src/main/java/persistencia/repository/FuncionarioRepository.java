package persistencia.repository;

import persistencia.dao.JPADao;
import persistencia.model.Funcionario;

public class FuncionarioRepository extends JPADao<Funcionario> {

    public FuncionarioRepository() {
        this.persistenceClass = Funcionario.class;
    }

}
