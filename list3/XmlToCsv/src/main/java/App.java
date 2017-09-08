import util.XMLSax;

import java.io.IOException;
import java.util.Properties;

public class App {
    private String CONFIG = "config.properties", LOCAL_XML = "local.xml", LOCAL_CSV = "local.csv";
    private String localCSV, filenameXML;
    private Properties properties;

    App() throws IOException {
        properties = new Properties();
        properties.load(App.class.getClassLoader().getResourceAsStream(CONFIG));
        localCSV = properties.getProperty(LOCAL_CSV);
        filenameXML = properties.getProperty(LOCAL_XML);
    }

    public static void main(String args []) {
        try {
            App app = new App();
            XMLSax xmlSax = new XMLSax();
            xmlSax.makeParse(app.getFilenameXML(), app.getFilenameCSV());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilenameXML() {
        return filenameXML;
    }
    public String getFilenameCSV() {
        return localCSV;
    }
}
