package persistencia.repository;

import persistencia.model.Funcionario;
import persistencia.repository.dao.IDao;
import persistencia.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCFuncionarioDao implements IDao<Funcionario> {
    private Connection conn;
    private PreparedStatement stmt;
    private String query;

    public JDBCFuncionarioDao () {
        this.conn = ConnectionPool.getConnection();
    }

    @Override
    public void remove(Funcionario entity) {
        query = "delete from funcionarios where id = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Funcionario entity) {
        query = "update funcionarios set nome = ?, email = ?, telefone = ?, cpf = ?, matricula = ? where id = ?";
        saveOrUpdate(entity, query);
    }

    @Override
    public Funcionario save(Funcionario entity) {
        query = "insert into funcionarios (nome, email, telefone, cpf, matricula) values (?, ?, ?, ?, ?)";
        saveOrUpdate(entity, query);
        return entity;
    }

    private void saveOrUpdate(Funcionario f, String query) {
        try {
            stmt = conn.prepareStatement(query);
            if (f.getId() != null)
                stmt.setLong(6, f.getId());

            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getEmail());
            stmt.setString(3, f.getTelefone());
            stmt.setString(4, f.getCpf());
            stmt.setString(5, f.getMatricula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Funcionario get(Object id) {
        query = "select * from funcionarios where id = ?";
        Funcionario funcionario = new Funcionario();
        try {
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, (Long) id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                funcionario = mappingFuncionario(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> all() {
        query = "select * from funcionarios";
        List<Funcionario> result = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = mappingFuncionario(rs);
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

    private Funcionario mappingFuncionario(ResultSet rs) {
        Funcionario f = new Funcionario();
        try {
            f.setId(rs.getLong("id"));
            f.setNome(rs.getString("nome"));
            f.setEmail(rs.getString("email"));
            f.setTelefone(rs.getString("telefone"));
            f.setCpf(rs.getString("cpf"));
            f.setMatricula(rs.getString("matricula"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }
}
