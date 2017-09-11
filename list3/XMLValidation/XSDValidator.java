import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;


public class XSDValidator {

  public static void main(String [] args) {
    if (args.length <= 0 || args.length > 2){
      System.out.println("Usage 1: XSDValidator <file-name.xml>");
      System.out.println("Usage 2: XSDValidator <file-name.xsd> <file-name.xml>");
      return;
    }

    boolean isValid = args.length == 2 ?
                      validateXMLSchema(args[0], args[1]) :
                      validateXML(args[0]);

    if(isValid)
      System.out.println("Is valid!");
    else
      System.out.println("Is not valid!");

  }

  public static boolean validateXMLSchema(String xsdPath, String xmlPath){
    try {
      SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = factory.newSchema(new File(xsdPath));
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(new File(xmlPath)));
      return true;
    } catch (IOException | SAXException e){
      System.out.println("Exception: " + e.getMessage());
      return false;
    }
  }

  public static boolean validateXML(String xmlPath){
    try {
      SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      String s = new XMLSax().makeParse(xmlPath);
      Schema schema = factory.newSchema(new File(s));
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(new File(xmlPath)));
      return true;
    } catch (IOException | SAXException e){
      System.out.println("Exception: " + e.getMessage());
      return false;
    }
  }
}

class XMLSax extends DefaultHandler {
	private String pathXSD;
	private final String SCHEMA_LOCATION = "xsi:schemaLocation";

	XMLSax() {
		super();
		pathXSD = "";
	}

  public class MySaxTerminatorException extends SAXException {}

	public String makeParse(String path) {
		 SAXParserFactory factory = SAXParserFactory.newInstance();
		 SAXParser saxParser;

		try {
			saxParser = factory.newSAXParser();
			saxParser.parse(path, this);
    } catch (MySaxTerminatorException e) {
      // Log para dizer que encontrou o arquivo para validacao
    } catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
		return pathXSD;
	}

	public void startDocument() {}

	public void endDocument() {}

	public void startElement(String uri, String localName, String qName, Attributes atts) throws MySaxTerminatorException{
    if (atts.getValue(SCHEMA_LOCATION) != null) {
      this.pathXSD = atts.getValue(SCHEMA_LOCATION);
      throw new MySaxTerminatorException();
    }
	}

	public void characters(char[] ch, int start, int length) throws SAXException {}

	public void endElement(String uri, String localName, String qName) throws SAXException {}

}
