package persistencia.query;

import persistencia.model.Dependente;
import persistencia.model.Funcionario;
import persistencia.repository.DependenteRepository;
import persistencia.repository.FuncionarioRepository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPQLNativeCriteriaNamed {
    private FuncionarioRepository funcionarioRepository;
    private DependenteRepository dependenteRepository;

    public JPQLNativeCriteriaNamed() {
        this.dependenteRepository = new DependenteRepository();
        this.funcionarioRepository = new FuncionarioRepository();
    }

    //Mostrar os nomes e todos os dependentes
    // com nomes iniciador por uma determinada letra,
    // juntamente com o nome do funcionário ao qual ele depende.
    // A letra inicial deve ser um parâmetro nomeado da consulta.

    public void allNomesDependentesCriteria (String initialLetter) {
        CriteriaBuilder cb = dependenteRepository.getEM().getCriteriaBuilder();
        CriteriaQuery<Dependente> cq = cb.createQuery(Dependente.class);
        Root<Dependente> from = cq.from(Dependente.class);
        TypedQuery<Dependente> typedQuery = dependenteRepository.getEM().createQuery(
                cq.select(from).where(cb.like(from.get("nome"),initialLetter + '%')));

        List<Dependente> ds = typedQuery.getResultList();
        for (Dependente d : ds) {
            System.out.println(d.showDependenteFuncionario());
        }
    }

    public void allNomesDependentesJQPL (String initialLetter) {
        List<Dependente> dependentes =  dependenteRepository.getEM()
                .createQuery("from Dependente where nome like :pattern", Dependente.class)
                .setParameter("pattern", initialLetter + '%')
                .getResultList();

        for (Dependente d : dependentes) {
            System.out.println(d.showDependenteFuncionario());
        }
    }

    public void allNomesDependentesNamed (String initialLetter) {
        List<Dependente> ds = dependenteRepository.getEM()
                .createNamedQuery("Dependente.findAll", Dependente.class)
                .setParameter("pattern", initialLetter + '%')
                .getResultList();
        for (Dependente d : ds) {
            System.out.println(d.showDependenteFuncionario());
        }
    }

    public void allNomesDependentesNative(String initialLetter) {
        List<Dependente> ds = dependenteRepository.getEM()
                .createNativeQuery("SELECT * FROM Dependentes WHERE nome LIKE :pattern", Dependente.class)
                .setParameter("pattern", initialLetter + '%')
                .getResultList();

        for (Dependente d : ds) {
            System.out.println(d.showDependenteFuncionario());
        }
    }

    //Mostrar todos os dados de todos os funcionários funcionários.
    public void allFuncionariosCriteria() {
        CriteriaBuilder cb = funcionarioRepository.getEM().getCriteriaBuilder();
        CriteriaQuery<Funcionario> cq = cb.createQuery(Funcionario.class);
        cq.from(Funcionario.class);
        List<Funcionario> funcionarios = funcionarioRepository.getEM().createQuery(cq).getResultList();

        System.out.println(funcionarios);
    }

    public void allFuncinariosJQPL() {
        List<Funcionario> funcionarios = funcionarioRepository.getEM()
                .createQuery("from Funcionario")
                .getResultList();
        System.out.println(funcionarios);
    }

    public void allFuncionariosNamed() {
        List<Funcionario> fs = funcionarioRepository.getEM()
                .createNamedQuery("Funcionario.findAll", Funcionario.class)
                .getResultList();
        System.out.println(fs);
    }

    public void allFuncionariosNative() {
        List<Dependente> fs = dependenteRepository.getEM()
                .createNativeQuery("SELECT * FROM Dependentes", Funcionario.class)
                .getResultList();
        System.out.println(fs);
    }
}
