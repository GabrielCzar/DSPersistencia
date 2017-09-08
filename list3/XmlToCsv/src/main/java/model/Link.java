package model;

public class Link {
    private String title;
    private String url;
    private String rel;

    public Link(String url, String rel, String title) {
        this.title = title;
        this.url = url;
        this.rel = rel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    @Override
    public String toString() {
        if (title == null || url == null || rel == null)
            return "";
        return String.format("'%s','%s','%s'", title, url, rel);
    }
}
