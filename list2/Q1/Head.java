import java.io.*;

public class Head {

    public static void main(String[] args) {
        try {
            String filename = args[0];
            int num = 10;
            if (args.length > 1 & args[0].equalsIgnoreCase("-n")) {
                filename = args[2];
                num = Integer.parseInt(args[1]);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename)));
            String line;
            while ((line = br.readLine()) != null && num > 0) {
                System.out.println(line);
                num--;
            }
        } catch (Exception exception) {
            System.out.println("Falta o caminho do arquivo!");
        }
    }
}
