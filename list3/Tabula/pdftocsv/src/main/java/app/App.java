package app;

import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.Table;
import technology.tabula.extractors.BasicExtractionAlgorithm;
import technology.tabula.writers.CSVWriter;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class App {
    private static final String PDF = "/home/gabriel/TabeladeRemuneracao2017.pdf";
    private static final String CSV = "/home/gabriel/TabeladeRemuneracao2017.csv";
    private static Float [] positions = new Float[] {240f, 600f, 645f, 300f};

    public static void main(String[] args) {
        ObjectExtractor oe = null;
        try {
            PDDocument pdfDocument = PDDocument.load(new File(PDF));
            oe = new ObjectExtractor(pdfDocument);

            Page page = oe.extract(5);
            Page pageArea = page.getArea(positions[0], positions[1], positions[2], positions[3]);

            BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();

            Table table = bea.extract(pageArea).get(0);

            CSVWriter csvWriter = new CSVWriter();
            csvWriter.write(new PrintStream(CSV), table);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oe != null) {
                try {
                    oe.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


