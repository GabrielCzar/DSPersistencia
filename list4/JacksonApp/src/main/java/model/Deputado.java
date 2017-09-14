package model;

import java.io.Serializable;

public class Deputado implements Serializable{
    private Long id;
    private String uri;
    private String nome;
    private String siglaPartido;
    private String uriPartido;
    private String siglaUf;
    private int idLegislatura;
    private String urlFoto;

    public Deputado() {}

    public Deputado(Long id, String uri, String nome, String siglaPartido, String uriPartido, String siglaUf, int idLegislatura, String urlFoto) {
        this.id = id;
        this.uri = uri;
        this.nome = nome;
        this.siglaPartido = siglaPartido;
        this.uriPartido = uriPartido;
        this.siglaUf = siglaUf;
        this.idLegislatura = idLegislatura;
        this.urlFoto = urlFoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public String getUriPartido() {
        return uriPartido;
    }

    public void setUriPartido(String uriPartido) {
        this.uriPartido = uriPartido;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public int getIdLegislatura() {
        return idLegislatura;
    }

    public void setIdLegislatura(int idLegislatura) {
        this.idLegislatura = idLegislatura;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s,%d,%s\n",
                id,
                uri,
                nome,
                siglaPartido,
                uriPartido,
                siglaUf,
                idLegislatura,
                urlFoto);
    }
}



