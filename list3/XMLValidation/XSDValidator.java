import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

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
      Schema schema = factory.newSchema();
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(new File(xmlPath)));
      return true;
    } catch (IOException | SAXException e){
      System.out.println("Exception: " + e.getMessage());
      return false;
    }
  }
}
