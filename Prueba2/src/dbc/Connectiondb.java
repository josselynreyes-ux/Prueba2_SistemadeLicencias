package dbc;
import java.sql.*;

public class Connectiondb {
    public static Connection getConnection() throws SQLException{
        String urlConnection="jdbc:mysql:/";
        String usuarioConnection = " ";
        String claveConnection=" ";

        return DriverManager.getConnection(urlConnection,usuarioConnection,claveConnection);
    }
}
