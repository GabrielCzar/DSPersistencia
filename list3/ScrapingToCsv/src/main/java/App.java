import model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class App {
    private static final String URL = "https://www.kabum.com.br/",
            ITEM = ".H-box",
            TITLE = ".H-titulo",
            SUBTITLE = ".H-subtitulo",
            PRICE = ".H-preco",
            CONFIG = "config.properties",
            LOCAL_STORAGE = "local.storage",
            FILENAME = "filename";
    private String localStorage, filename;

    private Properties properties;

    App () throws IOException {
        properties = new Properties();
        properties.load(App.class.getClassLoader().getResourceAsStream(CONFIG));
        localStorage = properties.getProperty(LOCAL_STORAGE);
        filename = properties.getProperty(FILENAME);
    }

    public static void main (String args [])  {
        try {
            App app = new App();
            List<Product> list = app.getAllProducts();
            app.saveCsv(list);
            System.out.println("Todos os dados foram gravados no CSV");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() throws IOException {
        ArrayList<Product> list = new ArrayList<Product>();
        Document document = Jsoup.connect(URL).get();
        Elements products = document.select(ITEM);

        for (Element productNode : products) {
            String name = productNode.select(TITLE).first().text();
            String subtitle = productNode.select(SUBTITLE).first().text();
            String price = productNode.select(PRICE).first().text();

            list.add(new Product(name, subtitle, price));
        }

        return list;
    }

    public void saveCsv(List<Product> products) {
        try {
            PrintStream pt = new PrintStream(new FileOutputStream(this.path(), true));
            for (Product product: products)
                pt.println(product.toCsv());
            pt.close();
        } catch (FileNotFoundException e) {
            File file = new File(this.path());
            e.printStackTrace();
        }

    }

    public String path() {
        return localStorage + filename;
    }
}
