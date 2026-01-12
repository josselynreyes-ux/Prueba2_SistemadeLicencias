import dao.Connectiondb;
import ui.*;
import java.sql.Connection;

public class Main{
    public static void main(String [] args){

        new Login();

        //try que se uso para comprobar la conexión

//        try (Connection cn = Connectiondb.getConnection()) {
//            System.out.println("Conexión OK");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

