package persistencia.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "funcionarios",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cpf", "matricula"}))
public class Funcionario {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nome, email, telefone, cpf, matricula;

    @OneToMany(mappedBy = "funcionario", cascade = ALL)
    private List<Dependente> dependentes;

    public Funcionario() { }

    public Funcionario(String nome, String email, String telefone, String cpf, String matricula) {
        this.cpf = cpf;
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes (List<Dependente> dependente) {
        this.dependentes = dependente;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s", id, nome, email, cpf, matricula);
    }
}
