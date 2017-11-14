package dspersist;

import com.datastax.driver.core.*;

public class SimpleClient {
    private Cluster cluster;
    private Session session;

    public void conect(String node) {
        cluster = Cluster.builder().addContactPoint(node).build();
        session = cluster.connect();
        Metadata metadata = cluster.getMetadata();
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datacenter: %s; Host: %s; Rack: %s; State: %s; Broadcast: %s\n", host.getDatacenter(), host.getAddress(),
                    host.getRack(), host.getState(), host.getBroadcastAddress());
        }
    }

    public void close() {
        cluster.close();
    }

    public void loadData() {
        String query = "select nome, idade from contatos.pessoas";
        ResultSet rs = session.execute(query);
        for (Row row : rs) {
            String nome = row.getString("nome");
            int idade = row.getInt("idade");
            System.out.println("Nome: " + nome + ", Idade: " + idade);
        }
    }

    public void insertData(String nome, int idade) {
        String stmt = "insert into contatos.pessoas (id, nome, idade) values (now(), ?, ?)";
        PreparedStatement ps = session.prepare(stmt);
        BoundStatement bs = new BoundStatement(ps);
        bs.bind(nome, idade);
        session.execute(bs);
    }
}
