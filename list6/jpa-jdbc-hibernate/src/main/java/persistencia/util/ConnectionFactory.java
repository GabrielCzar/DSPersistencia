package persistencia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String [] DEV_H2 = {"jdbc:h2:~/jpa-jdbc-db", "sa", ""};
    private static final String [] DEV_POSTGRES = {"jdbc:postgresql://localhost/jpa-jdbc-db", "postgres", "postgres"};

    private ConnectionFactory () {}

    public static Connection getConnection () {
        try {
            return DriverManager.getConnection(DEV_H2[0], DEV_H2[1], DEV_H2[2]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
