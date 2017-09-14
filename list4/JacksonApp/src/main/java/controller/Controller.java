package controller;

import model.Deputado;
import model.Resource;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import java.io.*;
import java.net.URL;
import java.util.List;

public class Controller {
    private static final String FILENAME = "deputados.ser",
                                FILENAME_PRETTY = "deputados_pretty.json";
    private ObjectMapper mapper;

    public Controller() {
        mapper = new ObjectMapper();
    }

    /**
     * Crie uma classe Java para baixar através do Java/Jackson o JSON acima descrito a partir da URL original
     * e não do arquivo salvo na questão anterior.
     * Depois disso, serialize o JSON para objetos Java.
     *
     * @param url
     * @return List<Deputado>
     * @throws IOException
     */
    public List<Deputado> deserializeObjectsFromUrl(String url) throws IOException {
        Resource resource = mapper.readValue(new URL(url), Resource.class);
        return resource.getDados();
    }

    /**
     *
     Crie uma classe Java para serializar os objetos criados na questão 2 contendo os dados do JSON.
     Serialize e salve em um arquivo usando a serialização padrão do Java.
     Serialize e salve em um arquivo usando a serialização formatada JSON do Jackson. Ou seja: o arquivo deve ser salvo formatado.
     Dica: Use o mapper.defaultPrettyPrintingWriter().writeValueAsString(json) do Jackson.

     * @throws IOException
     * @param deputados
     */

    public void serializeObjects(List<Deputado> deputados) throws IOException {
        // Serialization default
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
        oos.writeObject(deputados);
        oos.close();
        // JSON pretty
        mapper.enable(Feature.INDENT_OUTPUT);
        mapper.writeValue(new File(FILENAME_PRETTY), deputados);
    }

    /**
     *  Crie uma classe Java para deserializar e exibir os dados do primeiro arquivo criado na questão 3.
     *  @param path of the filename
     */
    //TypeReference<?> typeReference = new TypeReference<List<Deputado>>(){};
    //List<Deputado> deputados = mapper.readValue(new File(path), typeReference);
    public List<Deputado> deserializeObjectsFromFile(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
        List<Deputado> deputados = (List<Deputado>) ois.readObject();
        return deputados;
    }
}
