package aeropuerto;

import conPostgres.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
* <h1>Avión</h1>
* Representa los datos de un vuelo. Utilizado por el sistema para gestionar los
* datos de la tabla vuelos de la base de datos. 
*
* @author  Vazquez Gutierrez Hugo Isaac
* @author  Trejo Castillo Uriel
* @author  Sosa Alcauter Erik Emmanuel
* @version 1.0
* @since   2020-01-24
*/
public class Vuelos {
    
    private int num_vuelo;
    private int id_avion;
    private int id_ruta;
    
    //Variable que es utilizada para realizar la conexion a la base de datos
    private Conexion con;

    /**
     * Constructor de la clase vacio
     */
    public Vuelos() {
    }

    /**
     * Constructor de la clase que especifica todos los valores de los vuelos
     * @param num_vuelo Identificador del vuelo
     * @param id_avion Relación con la tabla avion
     * @param id_ruta Relación con la tabla ruta
     */
    public Vuelos(int num_vuelo, int id_avion, int id_ruta) {
        this.num_vuelo = num_vuelo;
        this.id_avion = id_avion;
        this.id_ruta = id_ruta;
    }
    
    /**
     * Inserta un registro en la base de datos.
     * Asigna un avion y una ruta a seguir por el vuelo y lo ingresa en la base 
     * de datos.
     */
    public void registrarVuelo() {
        try {
            con = new Conexion();

            final String SQL = "Insert Into vuelos(id_avion, id_ruta) values(?, ?)";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setInt(1, id_avion);
            sentencia.setInt(2, id_ruta);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Utiliza el identificador para eliminar un registro en la base de datos.
     * Crea un String que representa la consulta a la base de datos y asigna el
     * valor de num_vuelo para no eliminar todos los registros.
     */
    public void eliminarVuelo() {
        try {
            con = new Conexion();

            final String SQL = "Delete From vuelos where num_vuelo = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setInt(1, num_vuelo);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Elimina todos los vuelos que utilizan un avión dado.
     * Con el identificador del avion, elimina todos los vuelos que utilizan
     * este. Es utilizado para eliminar exitosamente los aviones
     */
    public void eliminarPorAvion() {
        try {
            con = new Conexion();

            final String SQL = "Delete From vuelos where id_avion = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setInt(1, id_avion);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera una lista de Vuelos.
     * <p>
     * Realiza una consulta de tipo selección para obtener todos los vuelos en
     * la base de datos. Utilizando Vuelos (num_vuelo, id_avion, id_ruta) para 
     * crear una lista de tipo Vuelos
     * @return Lista con objetos tipo Avion con todos los datos inicializados.
     */
    public ArrayList<Vuelos> obtenerVuelos() {
        ArrayList<Vuelos> listVuelos = new ArrayList<>();
        try {
            con = new Conexion();
            final String SQL = "Select * From vuelos";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            ResultSet record = sentencia.executeQuery();

            while (record.next()) {
                listVuelos.add(new Vuelos(record.getInt("num_vuelo"), record.getInt("id_avion"), record.getInt("id_ruta")));
            }
            return listVuelos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVuelos;
    }
    
    public int getNum_vuelo() {
        return num_vuelo;
    }

    public void setNum_vuelo(int num_vuelo) {
        this.num_vuelo = num_vuelo;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }
}