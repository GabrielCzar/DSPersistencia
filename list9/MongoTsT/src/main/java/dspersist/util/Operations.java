package dspersist.util;

import com.mongodb.client.MongoCollection;
import dspersist.model.Editora;
import dspersist.model.Livro;
import org.bson.Document;

import java.util.Arrays;

public class Operations {

    public static void insertLivro(MongoCollection<Document> collection, Livro livro) {
        collection.insertOne(
                new Document("isbn", livro.getIsbn())
                        .append("ano_publicacao", livro.getAno_publicacao())
                        .append("titulo", livro.getTitulo())
                        .append("valor", livro.getValor())
                        .append("id_editora", livro.getId_editora()));
    }

    public static void insertEditora(MongoCollection<Document> collection, Editora editora) {
        collection.insertOne(new Document("nome", editora.getId()).append("id", editora.getId()));
    }

    // Obter o nome da editora, a quantidade total de livros por editora e o valor total
    public static Iterable<Document> getEditoraQuantidadeEValorTotal (MongoCollection<Document> collection) {
        return collection.aggregate(Arrays.asList(
                new Document("$match",
                        new Document("ano_publicacao",
                                new Document("$gte", 2010))),
                new Document("$group",
                        new Document("_id", "$editora")
                                .append("totalPrice",
                                        new Document("$sum",
                                                new Document("$multiply", Arrays.asList(
                                                        "$qtd_estoque", "$valor")))))));
    }

    // Obter o nome da editora,
    // a quantidade total de livros por editora
    // e o valor total (qtd_estoque * valor) dos livros para cada editora.
    // Somente considerar os livros publicados a partir de 2010.
    public static Iterable<Document> getEditoraQtdTotalEValorTotalApartir2010 (MongoCollection<Document> collection) {
        return collection.aggregate(Arrays.asList(
                new Document("$match",
                        new Document("ano_publicacao",
                                new Document("$gte", 2010))),
                new Document("$lookup",
                        new Document("from", "Editoras")
                                .append("localField", "id_editora")
                                .append("foreignField", "id")
                                .append("as", "editora")),
                new Document("$group",
                        new Document("_id", "$editora.nome")
                                .append("totalLivros",
                                        new Document("$sum", "$qtd_estoque"))
                                .append("totalValor",
                                        new Document("$sum",
                                                new Document("$multiply", Arrays.asList("$qtd_estoque", "$valor"))))),
                new Document("$project",
                        new Document("_id", false)
                            .append("editora", "$_id")
                            .append("totalLivros", true)
                            .append("totalValor", true))
        ));
    }

    public static Iterable<Document> list (MongoCollection<Document> collection) {
        return collection.find();
    }

}
