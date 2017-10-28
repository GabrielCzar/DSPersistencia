package dspersist;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dspersist.util.Operations;
import org.bson.Document;

public class App {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("persistencia");
        MongoCollection<Document> collectionLibrary = db.getCollection("library");
        MongoCollection<Document> collectionEditoras = db.getCollection("Editoras");
        MongoCollection<Document> collectionLivros = db.getCollection("Livros");

        
        Iterable<Document> docs = Operations.getEditoraQtdTotalEValorTotalApartir2010(collectionLivros);
        docs.forEach(v -> System.out.println(v.toJson()));

    }
}
