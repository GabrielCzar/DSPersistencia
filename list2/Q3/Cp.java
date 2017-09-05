import java.io.*;
import java.nio.file.*;

public class Cp {

    public static void main(String[] args) {
        try {
            String origin = args[0];
            String destiny = args[1];
            long time = System.currentTimeMillis();

            Cp cp = new Cp();
            byte[] bytes = cp.readBinaryFile(origin);

            cp.writeBinaryFile(bytes, destiny);

            long timeFinal = System.currentTimeMillis();

            System.out.println((timeFinal - time) + " milisegundos para a transferência.");
        } catch (Exception exception) {
            System.out.println("ERROR! Falta especificar o caminho do arquivo!");
        } finally {

        }
    }

    byte[] readBinaryFile(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        return Files.readAllBytes(path);
    }

    void writeBinaryFile(byte[] aBytes, String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        Files.write(path, aBytes);
    }

}