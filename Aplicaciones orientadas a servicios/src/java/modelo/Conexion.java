package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <h1>Conexion a la base de datos</h1>
 * Conexión con PostgreSQL.
 * Gestiona la conexión a la base de datos de PostgreSQL.
 * 
 * @author Vazquez Gutierrez Hugo Isaac
 * 
 * @version 1.0
 */
public class Conexion {

    /*
    Llama a Conection para realizar las consultas a la base
     y establecer la conexión más adelante
    */
    private Connection con;
    
    //Variables con el usuario, contraseña y direccion de la base de datos
    private String user, passwd, url;

    
    /**
     * Constructor que inicializa las variables con los mismos valores.
     * En caso de querer modificar la conexión se debe modificar este código
     * o modificarlo cada vez que se realice conexión a la base de datos
     */
    public Conexion() {
        user = "postgres";
        passwd = "123";
        url = "jdbc:postgresql://localhost:5432/UTTEC";
    }

    /**
     * Genera la conexión a la base de datos con el usuario correspondiente
     * @return Un objeto de tipo Connection si se realizo la conexión correctamente,
     * o nada si la conexión es incorrecta.
     * @see Connection
     */
    public Connection obtenerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            return con = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            /*
            Se imprime el error en pantalla, no debe utilizarse ya que las 
            excepciones deben ser manejadas individualmente
            */
            e.printStackTrace();
        }
        //Regresa nulo si la conexión no se realizo
        return con = null;
    }

}
