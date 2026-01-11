import dao.Connectiondb;
import ui.*;
import java.sql.Connection;

public class Main{
    public static void main(String [] args){
        //new ReportesyEstadisticas(); //LISTO

        //new GestionTramites(); //LISTO

        //new GestionUsuarios(); //LISTO

        //new RegistrarExamenes(); //LISTO

        //new GenerarLicencia(); //LISTO

        //new MenuAdministrador(); //LISTO

        //new MenuAnalista(); //LISTO

        //new VerificarRequisitos(); //LISTO

        //new RegistroSolicitante();//LISTO

        //new Login(); //LISTO


        //try que se uso para comprobar la conexión

        try (Connection cn = Connectiondb.getConnection()) {
            System.out.println("Conexión OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

