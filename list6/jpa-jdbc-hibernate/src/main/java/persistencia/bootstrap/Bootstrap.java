package persistencia.bootstrap;

import persistencia.model.Dependente;
import persistencia.model.Funcionario;
import persistencia.repository.DependenteRepository;
import persistencia.repository.FuncionarioRepository;

public class Bootstrap {
    private final Integer MAX = 10;
    private FuncionarioRepository funcionarioRepository;
    private DependenteRepository dependenteRepository;

    public Bootstrap() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.dependenteRepository = new DependenteRepository();
    }

    // Criar funcionarios com dependentes
    public void inserirFuncionariosComDependentes () {
        Funcionario gabriel = new Funcionario("Gabriel", "gabriel@serv.com", "8899.0999", "999.999.999-99", "123");
        Dependente cesar = new Dependente("Cesar", "111.111.111-11");

        try {
            funcionarioRepository.beginTransaction();
            funcionarioRepository.save(gabriel); // sem dependente
            funcionarioRepository.commit();

            dependenteRepository.beginTransaction();
            dependenteRepository.save(cesar); // sem funcionario
            dependenteRepository.commit();

            funcionarioRepository.beginTransaction();
            dependenteRepository.beginTransaction();

            for (int i = 0; i < MAX; i++) {
                String pos = String.valueOf(i);
                Funcionario f = new Funcionario(gabriel.getNome() + pos,
                        gabriel.getEmail() + pos,
                        gabriel.getTelefone() + pos,
                        gabriel.getCpf() + pos,
                        gabriel.getMatricula() + pos);
                Dependente d = new Dependente(cesar.getNome() + pos, cesar.getCpf() + pos);
                Dependente d1 = new Dependente(cesar.getNome() + pos + pos, cesar.getCpf() + pos + pos);

                f.getDependentes().add(d);
                f.getDependentes().add(d1);
                d.setFuncionario(f);
                d1.setFuncionario(f);

                funcionarioRepository.save(f);
                dependenteRepository.save(d);
                dependenteRepository.save(d1);
            }
            funcionarioRepository.commit();
            dependenteRepository.commit();

        } catch (Exception e) {
            funcionarioRepository.rollback();
            dependenteRepository.rollback();
            e.printStackTrace();
        } finally {
            funcionarioRepository.close();
            dependenteRepository.close();
        }
    }

    // Executar um rollback
    public void executarRoolbackNaInsercao () {
        Funcionario alves = new Funcionario("Alves", "alves@serv.com", "8899.0999", "888.888.888-88", "4321");
        Funcionario alli = new Funcionario("Alli", "alli@serv.com", "8899.0999", "777.777.777-77", "5555");
        Dependente lima = new Dependente("Lima", "222.222.222-22");

        try {
            funcionarioRepository.beginTransaction();
            dependenteRepository.beginTransaction();

            alves.getDependentes().add(lima);
            lima.setFuncionario(alves);

            funcionarioRepository.save(alves); // Insere no database
            dependenteRepository.save(lima);
            funcionarioRepository.save(alli);

            funcionarioRepository.commit();
            dependenteRepository.commit();

            funcionarioRepository.save(alli); // Da erro porque ja existe cpf e matricula
            funcionarioRepository.commit();
        } catch (Exception e ) {
            funcionarioRepository.rollback();
            dependenteRepository.rollback();
            e.printStackTrace();
        } finally {
            funcionarioRepository.close();
            dependenteRepository.close();
        }
    }

}
