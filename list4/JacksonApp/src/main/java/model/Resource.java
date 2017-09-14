package model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

public class Resource {
    private List<Deputado> dados;
    @JsonIgnore
    private List<Link> links;

    public Resource() {}

    public Resource(List<Deputado> dados, List<Link> links) {
        this.dados = dados;
        this.links = links;
    }

    public List<Deputado> getDados() {
        return dados;
    }

    public void setDados(List<Deputado> dados) {
        this.dados = dados;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    class Link {
        private String rel;
        private String href;

        public Link() {}

        public Link(String rel, String href) {
            this.rel = rel;
            this.href = href;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
