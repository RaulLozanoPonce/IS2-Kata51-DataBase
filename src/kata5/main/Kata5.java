package kata5.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import kata5.view.MailListReader;

public class Kata5 {
    
    public static void main(String[] args) throws IOException{
        //Obtenemos la lista de strings del fichero
        String directory = "email.txt";
        List<String> lista = new MailListReader().read(directory);
        
        //Creamos una tabla en la base de datos
        createNewTable();
        
        //Insertar datos
        InsertarDatosTabla idt = new InsertarDatosTabla();
        idt.insert(lista);

    }
    
    public static void createNewTable() {
        // Cadena de conexión SQLite
        String url = "jdbc:sqlite:mail.db";
        // Instrucción SQL para crear nueva tabla
        String sql = "CREATE TABLE IF NOT EXISTS direcc_email (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " direccion text NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            // Se crea la nueva tabla
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
