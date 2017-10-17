# Lista 7

#### Consulta JPA

- Mostrar os nomes e todos os dependentes com nomes iniciador por uma determinada letra, juntamente com o nome do funcionário ao qual ele depende. A letra inicial deve ser um parâmetro nomeado da consulta.

_Consulta_
```java
public void allNomesDependentes (String initialLetter) {
        List<Dependente> ds = dependenteRepository.getEM()
                .createNamedQuery("Dependente.findAll", Dependente.class)
                .setParameter("pattern", initialLetter + '%')
                .getResultList();
        for (Dependente d : ds) {
            System.out.println(d.showDependenteFuncionario());
        }
    }
```

_Classe Dependente Padrão_
```java
@NamedQueries({
        @NamedQuery(name = "Dependente.findAll", query = "from Dependente where d.nome like :pattern")
})
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome, cpf;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    // getters and setters

    public String showDependenteFuncionario() {
        return String.format("%s, %s", nome, funcionario != null ? funcionario.getNome() : "");
    }
}
```

_Class Dependente Com Named Query Otimizada_
```java
@NamedQueries({
        @NamedQuery(name = "Dependente.findAll", query = "from Dependente d join fetch d.funcionario where d.nome like :pattern")
})
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome, cpf;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    // getters and setters

    public String showDependenteFuncionario() {
        return String.format("%s, %s", nome, funcionario != null ? funcionario.getNome() : "");
    }
}
```