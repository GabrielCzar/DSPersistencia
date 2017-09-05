import java.io.*;

public class Wc {

    public static void main(String[] args) {
        long qtdLinhas = 0,
                qtdPalavras = 0,
                qtdCaracteres = 0;

        try {
            String filename = args[0];

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename)));
            String line;
            while ((line = br.readLine()) != null) {
                qtdLinhas ++;
                qtdPalavras += countWords(line);
                qtdCaracteres += line.length() + 1; // com a quebra de linha
            }
            System.out.println(qtdLinhas + " " + qtdPalavras + " " + qtdCaracteres + " " + filename);
        } catch (Exception exception) {
            System.out.println("Falta o caminho do arquivo!");
        }
    }

    private static int countWords(String line) {
        String [] s = line.trim().split("\\s+");
        long count = 0;
        for (String a : s) if (a.length() == 0) count++;
        return s.length - count;
    }
}
