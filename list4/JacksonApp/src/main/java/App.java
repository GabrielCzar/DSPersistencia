import controller.Controller;
import model.Deputado;

import java.io.IOException;
import java.util.List;

public class App {

    private static final String URL = "https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome",
                                FILENAME = "deputados.json";

    public static void main(String[] args) {
        Controller controller = new Controller();

        try {
            List<Deputado> deputadosURL = controller.deserializeObjectsFromUrl(URL);
            controller.serializeObjects(deputadosURL);
            List<Deputado> deputadosFILE = controller.deserializeObjectsFromFile(FILENAME);

            System.out.println(deputadosFILE);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
