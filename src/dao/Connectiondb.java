package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// para la conexion  usamos variables de entorno esto
// es para mantener la seguridad de nuestra base de datos evitando que se filtre
// la contraseña cuando se suba a Git

public class Connectiondb {

    private static String env(String key) {
        String conexion = System.getenv(key);
        if (conexion == null || conexion.isBlank()) {
            throw new RuntimeException("Falta variable de entorno: " + key);
        }
        return conexion;
    }

    public static Connection getConnection() throws SQLException {
        String host = env("DB_HOST");
        String puerto = env("DB_PORT");
        String db   = env("DB_NAME");
        String user = env("DB_USER");
        String pass = env("DB_PASS");

        String url = "jdbc:mysql://" + host + ":" + puerto + "/" + db + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        return DriverManager.getConnection(url, user, pass);
    }

}
