package dspersist;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dspersist.model.Editora;
import dspersist.model.Livro;
import dspersist.util.Operations;
import org.bson.Document;

import java.util.Scanner;

public class App {
    private static MongoClient mongoClient = new MongoClient();
    private static MongoDatabase db = mongoClient.getDatabase("persistencia");
    private static MongoCollection<Document> collectionLibrary = db.getCollection("library");
    private static MongoCollection<Document> collectionEditoras = db.getCollection("Editoras");
    private static MongoCollection<Document> collectionLivros = db.getCollection("Livros");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        boolean condition;
        do {
            MENU();

            int opt = scanner.nextInt();
            executeQuery(opt);

            System.out.println("Deseja continuar?");
            condition = scanner.nextBoolean();
        } while (condition);
    }

    private static void executeQuery(int opt) {
        switch (opt) {
            case 1:
                Operations.getEditoraQtdTotalEValorTotalApartir2010(collectionLivros).forEach(v -> System.out.println(v.toJson()));
                break;
            case 2:
                Operations.getEditoraQuantidadeEValorTotal(collectionLibrary).forEach(v -> System.out.println(v.toJson()));
                break;
            case 3:
                Operations.list(collectionEditoras).forEach(v -> System.out.println(v.toJson()));
                break;
            case 4:
                Operations.list(collectionLivros).forEach(v -> System.out.println(v.toJson()));
                break;
            case 5:
                Editora e = readEdt();
                Operations.insertEditora(collectionEditoras, e);
                break;
            case 6:
                Livro l = readLvr();
                Operations.insertLivro(collectionLivros, l);
                break;
                default:
                    System.out.println("Opção inválida");
        }
    }

    private static Editora readEdt()
    {
        Editora e = new Editora();
        System.out.println("Digite o id da editora");
        e.setId(scanner.nextInt());
        System.out.println("Digite o nome da editora");
        e.setNome(scanner.nextLine());

        return e;
    }

    private static Livro readLvr()
    {
        Livro l = new Livro();
        System.out.println("Digite o isbn do livro");
        l.setIsbn(scanner.nextLong());
        System.out.println("Digite o titulo do livro");
        l.setTitulo(scanner.nextLine());
        System.out.println("Digite o ano de publicacao do livro");
        l.setAno_publicacao(scanner.nextInt());
        System.out.println("Digite a quantidade em estoque do livro");
        l.setQtd_estoque(scanner.nextInt());
        System.out.println("Digite o valor do livro");
        l.setValor(scanner.nextDouble());
        System.out.println("Digite o id da editora do livro");
        l.setId_editora(scanner.nextInt());

        return l;
    }

    private static void MENU () {
        System.out.println("Digite uma das opções: ");
        System.out.println("1 - Visualizar a Quantidade e Valor total por Editora apartir de 2010");
        System.out.println("2 - Vsiualizar a Quantidade e Valor total por Editora (Coleção Única)");
        System.out.println("3 - Visualizar todas as Editoras");
        System.out.println("4 - Visualizar todos os Livros");
        System.out.println("5 - Inserir Editora");
        System.out.println("6 - Inserir Livro");
    }
}
