package dspersist;

public class App {
    public static void main(String[] args) {
        SimpleClient client = new SimpleClient();
        client.conect("127.0.0.1");
        client.insertData("Czar", 99);
        client.loadData();
        client.close();
    }
}
