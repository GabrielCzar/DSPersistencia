package dspersist.model;

import org.bson.types.ObjectId;

public class Livro {
    private ObjectId mongoID;
    private Long isbn;
    private String titulo;
    private Integer ano_publicacao;
    private Integer qtd_estoque;
    private Double valor;
    private Integer id_editora;

    public ObjectId getMongoID() {
        return mongoID;
    }

    public void setMongoID(ObjectId mongoID) {
        this.mongoID = mongoID;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(Integer ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public Integer getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(Integer qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getId_editora() {
        return id_editora;
    }

    public void setId_editora(Integer id_editora) {
        this.id_editora = id_editora;
    }
}
