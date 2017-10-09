package persistencia.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String [] DEV_H2 = {"org.h2.Driver", "jdbc:h2:~/jpa-jdbc-db", "sa", ""};
    private static final String [] DEV_POSTGRES = {"org.postgresql.Driver", "jdbc:postgresql://localhost/jpa-jdbc-db", "postgres", "postgres"};

    private ConnectionPool() {}

    public static Connection getConnection () {
        try {
            ComboPooledDataSource pool = new ComboPooledDataSource();
            pool.setDriverClass(DEV_H2[0]);
            pool.setJdbcUrl(DEV_H2[1]);
            pool.setUser(DEV_H2[2]);
            pool.setPassword(DEV_H2[3]);

            pool.setMinPoolSize(3);
            pool.setAcquireIncrement(5);
            pool.setMaxPoolSize(10);
            pool.setCheckoutTimeout(300);
            pool.setMaxStatements(50);
            return pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
