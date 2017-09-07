package model;

public class Product {
    private String title;
    private String subtitle;
    private String price;

    public Product(String title, String subtitle, String price) {
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Subtitle: %s, Price: %s", getTitle(), getSubtitle(), getPrice());
    }

    public String toCsv() {
        return String.format("\"%s\",\"%s\",\"%s\"", getTitle(), getSubtitle(), getPrice());
    }
}
