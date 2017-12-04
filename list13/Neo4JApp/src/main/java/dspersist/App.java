package dspersist;

import org.neo4j.driver.v1.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.neo4j.driver.v1.Values.parameters;

public class App {
    private static Driver driver = GraphDatabase.driver( "bolt://localhost:7687",
            AuthTokens.basic( "neo4j", "admin" ) );

    public static void main(String[] args) {

        //Create the complete graph
        //App.createGraphast();
        //System.out.println("Graph created!");

        // Execute Shortest Path
        System.out.println(App.shortestPath(1, 4));
        System.out.println("Shortest Path Executed!");

        //All Paths Between 2 nodes
        //App.allPaths(1, 4).forEach(v -> System.out.println(v));
        //System.out.println("All Paths");

        App.driver.close();
    }


    public static long addNode(final int id)
    {
        try ( Session session = driver.session() )
        {
            session.writeTransaction(tx -> createGraphastNode(tx, id));
            return session.readTransaction(tx -> matchGraphastNode(tx, id));
        }
    }


    private static Void createGraphastNode(Transaction tx, int node)
    {
        tx.run( "CREATE (a:Graph {node: $node})", parameters( "node", node) );
        return null;
    }

    private static long matchGraphastNode(Transaction tx, int node)
    {
        StatementResult result = tx.run( "MATCH (a:Graph {node: $node}) RETURN id(a)",
                parameters( "node", node ) );
        return result.single().get(0).asLong();
    }

    public static void createGraphast() {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("CREATE " +
                        "(node0 :Graph {node: 0}), " +
                        "(node1 :Graph {node: 1}), " +
                        "(node2 :Graph {node: 2}), " +
                        "(node3 :Graph {node: 3}), " +
                        "(node4 :Graph {node: 4}), " +
                        "(node5 :Graph {node: 5}), " +
                        "(node1) - [:CONNECTED_TO{cost: 1.1}] -> (node5), " +
                        "(node5) - [:CONNECTED_TO{cost: 1.1}] -> (node1), " +
                        "(node5) - [:CONNECTED_TO{cost: 7.0}] -> (node4), " +
                        "(node5) - [:CONNECTED_TO{cost: 2.0}] -> (node0), " +
                        "(node0) - [:CONNECTED_TO{cost: 3.0}] -> (node3), " +
                        "(node3) - [:CONNECTED_TO{cost: 3.0}] -> (node0), " +
                        "(node3) - [:CONNECTED_TO{cost: 4.0}] -> (node2), " +
                        "(node2) - [:CONNECTED_TO{cost: 5.0}] -> (node3), " +
                        "(node2) - [:CONNECTED_TO{cost: 6.0}] -> (node4), " +
                        "(node4) - [:CONNECTED_TO{cost: 6.0}] -> (node2)"
                );
                return null;
            });
        }
    }

    //default 1 e 4
    public static String shortestPath(int nodeInitial, int nodeFinal) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                        String res = tx.run("match (nodeInitial:Graph {node: $nodeInitial}), " +
                                                "(nodeFinal:Graph {node: $nodeFinal}), " +
                                                "p = shortestPath((nodeInitial) - [*] - (nodeFinal)) return p",
                                        parameters("nodeInitial", nodeInitial, "nodeFinal", nodeFinal))
                                        .single().values().toString();
                        return res;
                    }
            );
        }
    }

    public static List allPaths(int nodeInitial, int nodeFinal) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx ->
                    tx.run("match (nodeInitial:Graph {node: $nodeInitial}), " +
                                    "(nodeFinal:Graph {node: $nodeFinal}), " +
                                    "p = ((nodeInitial) - [*] -> (nodeFinal)) return p",
                            parameters("nodeInitial", nodeInitial, "nodeFinal", nodeFinal))
                            .list().stream().map(l -> l.values()).collect(Collectors.toList())
            );
        }
    }
}
