import java.io.*;

public class Cat {

    public static void main(String[] args) {
        try {
            String filename = args[0];
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename)));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception exception) {
            System.out.println("Falta o caminho do arquivo!");
        }
    }
}
