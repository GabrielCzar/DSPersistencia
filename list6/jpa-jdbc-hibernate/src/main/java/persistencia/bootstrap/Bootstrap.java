package persistencia.bootstrap;

import persistencia.model.Dependente;
import persistencia.model.Funcionario;
import persistencia.repository.DependenteRepository;
import persistencia.repository.FuncionarioRepository;
import persistencia.util.JpaUtil;

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
            System.out.println("INS FUNC");
        } catch (Exception e) {
            funcionarioRepository.rollback();
            System.err.println("ROLLBACK FUNC");
        }

        try {
            dependenteRepository.beginTransaction();
            dependenteRepository.save(cesar); // sem funcionario
            dependenteRepository.commit();
            System.out.println("INS DEP");
        } catch (Exception e) {
            dependenteRepository.rollback();
            System.err.println("ROLLBACK DEP");
        }

        try {
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

                f = funcionarioRepository.save(f);
                d = dependenteRepository.save(d);

                d.setFuncionario(f);

                d = dependenteRepository.save(d);

                f.getDependentes().add(d);

                funcionarioRepository.update(f);
            }
            funcionarioRepository.commit();
            dependenteRepository.commit();

        } catch (Exception e) {
            funcionarioRepository.rollback();
            dependenteRepository.rollback();
            System.err.println("ROLLBACK JOIN");
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

    public void showFuncionarios() {
        System.out.println(
                funcionarioRepository.all()
        );
        funcionarioRepository.close();
    }

    public void showDependentes() {
        System.out.println(
                dependenteRepository.all()
        );
        dependenteRepository.close();
    }

}
