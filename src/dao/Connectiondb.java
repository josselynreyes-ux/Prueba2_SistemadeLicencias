package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connectiondb {

    private static final String url = "jdbc:mysql://hopper.proxy.rlwy.net:42735/railway?useSSL=false&serverTimezone=UTC";
// estructura: jdbc:mysql://USUARIO:PASSWORD@HOST:PUERTO/BASE_DE_DATOS

    private static final String user = "root";
    private static final String pass = "FiXMPrBObCMcsSIbHmPyGjPZzZbwAMNI";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

}

