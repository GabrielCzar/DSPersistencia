package persistencia.repository;

import persistencia.model.Dependente;
import persistencia.repository.dao.IDao;
import persistencia.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCDependenteDao implements IDao<Dependente> {
    private Connection conn;
    private PreparedStatement stmt;
    private String query;

    public JDBCDependenteDao() {
        this.conn = ConnectionFactory.getConnection();
    }

    @Override
    public void remove(Dependente entity) {
        query = "delete from dependentes where id = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Dependente entity) {
        query = "update dependentes set nome = ?, cpf = ? where id = ?";
        saveOrUpdate(entity, query);
    }

    @Override
    public void save(Dependente entity) {
        query = "insert into dependentes (nome, cpf) values (?, ?)";
        saveOrUpdate(entity, query);
    }

    private void saveOrUpdate(Dependente f, String query) {
        try {
            stmt = conn.prepareStatement(query);
            if (f.getId() != null)
                stmt.setLong(3, f.getId());

            stmt.setString(1, f.getNome());
            stmt.setString(4, f.getCpf());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dependente get(Object id) {
        query = "select * from dependentes where id = ?";
        Dependente Dependente = new Dependente();
        try {
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, (Long) id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                Dependente = mappingDependente(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Dependente;
    }

    @Override
    public List<Dependente> all() {
        query = "select * from dependentes";
        List<Dependente> result = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Dependente f = mappingDependente(rs);
                result.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void beginTransaction() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dependente mappingDependente(ResultSet rs) {
        Dependente d = new Dependente();
        try {
            d.setId(rs.getLong("id"));
            d.setNome(rs.getString("nome"));
            d.setCpf(rs.getString("cpf"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
}
