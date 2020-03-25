package conPostgres;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <h1>Persona</h1>
 * Representa los datos de una persona. Utilizado por el sistema para gestionar
 * los datos de la tabla boletos de la base de datos.
 * 
 * @author Sosa Alcauter Erik Emmanuel
 * @author Trejo Castillo Uriel
 * @author Vazquez Gutierrez Hugo Isaac
 *  
 * @version 1.0
 * @since 2020-01-24
 */
public class Conexion {

    private String user, passwd, url;
    private Connection con;

    /**
     * Constructor vacio que inicializa las variables para la conexión.
     */
    public Conexion() {
        user = "postgres";
        passwd = "123";
        url = "jdbc:postgresql://localhost:5432/Aeropuerto";
    }

    /**
     * Genera una conexión  a PostgreSQL. Utilizando usuario, su contraseña y la
     * dirección de la base de datos, crea una conexión a PostgreSQL
     * @return Objeto ResultSet para realizar la conexión a la base de datos
     */
    public Connection obtenerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            return con = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con = null;
    }
}
