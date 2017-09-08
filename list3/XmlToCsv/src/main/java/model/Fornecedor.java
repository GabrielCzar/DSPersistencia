package model;

import java.util.ArrayList;
import java.util.List;

public class Fornecedor {
    private List<Link> _links;
    private String id;
    private String cnpj;
    private String cpf;
    private String nome;
    private String ativo;
    private String recadastrado;
    private String id_municipio;
    private String uf;
    private String id_natureza_juridica;
    private String id_porte_empresa;
    private String id_ramo_negocio;
    private String id_unidade_cadastradora;
    private String id_cnae;
    private String habilitado_licitar;

    public Fornecedor() {
        _links = new ArrayList<>();
    }

    public List<Link> get_links() {
        return _links;
    }

    public void set_links(List<Link> _links) {
        this._links = _links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String isAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String isRecadastrado() {
        return recadastrado;
    }

    public void setRecadastrado(String recadastrado) {
        this.recadastrado = recadastrado;
    }

    public String getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(String id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getId_natureza_juridica() {
        return id_natureza_juridica;
    }

    public void setId_natureza_juridica(String id_natureza_juridica) {
        this.id_natureza_juridica = id_natureza_juridica;
    }

    public String getId_porte_empresa() {
        return id_porte_empresa;
    }

    public void setId_porte_empresa(String id_porte_empresa) {
        this.id_porte_empresa = id_porte_empresa;
    }

    public String getId_ramo_negocio() {
        return id_ramo_negocio;
    }

    public void setId_ramo_negocio(String id_ramo_negocio) {
        this.id_ramo_negocio = id_ramo_negocio;
    }

    public String getId_unidade_cadastradora() {
        return id_unidade_cadastradora;
    }

    public void setId_unidade_cadastradora(String id_unidade_cadastradora) {
        this.id_unidade_cadastradora = id_unidade_cadastradora;
    }

    public String getId_cnae() {
        return id_cnae;
    }

    public void setId_cnae(String id_cnae) {
        this.id_cnae = id_cnae;
    }

    public String isHabilitado_licitar() {
        return habilitado_licitar;
    }

    public void setHabilitado_licitar(String habilitado_licitar) {
        this.habilitado_licitar = habilitado_licitar;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAtivo() {
        return ativo;
    }

    public String getRecadastrado() {
        return recadastrado;
    }

    public String getHabilitado_licitar() {
        return habilitado_licitar;
    }

    public void addLink(Link link) {
        _links.add(link);
    }

    @Override
    public String toString() {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                toStringLinks(), id, (cpf == null ? cnpj : cpf), nome, ativo, recadastrado, id_municipio, uf, id_natureza_juridica,
                id_porte_empresa, id_ramo_negocio, id_unidade_cadastradora, id_cnae, habilitado_licitar);

    }

    private String toStringLinks() {
        String x = "[";
        for (Link link: _links)
            x += link.toString() + ",";
        if (x.length() > 1)
            x.substring(0, x.lastIndexOf(","));
        x += "]";
        return x;
    }

}
