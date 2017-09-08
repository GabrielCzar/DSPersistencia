package controller;

import model.Fornecedor;
import model.Link;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

public class Controller {


    public static void update(Fornecedor fornecedor, String name, String value) {
        try {
            Method method = Fornecedor.class.getDeclaredMethod("set" + capitalize(name), String.class);
            method.invoke(fornecedor, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void saveInCsv(Fornecedor fornecedor, String pathCSV) {
        try {
            PrintStream pt = new PrintStream(new FileOutputStream(pathCSV, true));
            pt.println(fornecedor.toString());
            pt.close();
        } catch (IOException e) {
            File file = new File(pathCSV.toString());
            printStackTrace("Error to find file");
        }
    }

    private static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
