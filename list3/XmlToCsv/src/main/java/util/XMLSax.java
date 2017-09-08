package util;

import controller.Controller;
import model.Fornecedor;
import model.Link;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class XMLSax extends DefaultHandler {
    private String tempValue;
    private Fornecedor fornecedor;
    private String pathCSV;

    public XMLSax() {
        super();
    }

    public void makeParse(String path, String pathCSV) {
        this.pathCSV = pathCSV;

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;

        try {
            saxParser = factory.newSAXParser();
            saxParser.parse(path, this);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void startDocument() { System.out.println("\nIniciando o Parsing...\n"); }

    public void endDocument() { System.out.println("\nFim do Parsing..."); }

    public void startElement(String uri, String localName, String qName, Attributes atts) {
        tempValue = "";

        if (qName.equalsIgnoreCase("resource") && (atts.getValue(0) == null)){
            fornecedor = new Fornecedor();
        } else if (qName.equalsIgnoreCase("link")) {
            String url = atts.getValue("href"),
                    rel = atts.getValue("rel"),
                    title = atts.getValue("title");

            if (fornecedor != null)
                fornecedor.addLink(new Link(url, rel, title));
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        tempValue = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("resource")) {
            System.out.println(fornecedor); // LOG
            Controller.saveInCsv(fornecedor, pathCSV);
        } else {

            if (qName.equalsIgnoreCase("_links")
                    || qName.equalsIgnoreCase("link")
                    ||(qName.equals("_embedded")))
                return;
            Controller.update(fornecedor, qName, tempValue);

        }
    }

}
