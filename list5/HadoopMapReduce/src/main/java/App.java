import org.apache.hadoop.util.ToolRunner;
import service.WordCount;

import java.util.Properties;

public class App {
    private static final String CONFIG = "config.properties";

    public static void main(String[] args) {
        Properties properties;

        try {
            properties = new Properties();
            properties.load(App.class.getClassLoader().getResourceAsStream(CONFIG));
            String pathInput = properties.getProperty("pathInput");
            String pathOutput = properties.getProperty("pathOutput");

            int exitCode = ToolRunner.run(new WordCount(), new String[]{pathInput, pathOutput});
            System.exit(exitCode);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro, verifique suas configurações\n");
            e.printStackTrace();
        }
    }
}
