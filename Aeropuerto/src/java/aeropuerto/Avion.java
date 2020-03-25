package aeropuerto;

import conPostgres.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
* <h1>Avión</h1>
* Representa los datos de un avión. Utilizado por el sistema para gestionar los
* datos de la tabla avion de la base de datos. 
*
* @author  Vazquez Gutierrez Hugo Isaac
* @author  Trejo Castillo Uriel
* @author  Sosa Alcauter Erik Emmanuel
* 
* @version 1.0
* @since   2020-01-24
*/
public class Avion {

    private int id_avion;
    private int id_aerolinea;
    private String modelo;
    private int capacidad;
    private String disponibilidad;
    
    //Variable que es utilizada para realizar la conexion a la base de datos
    private Conexion con;

    /**
     * Constructor de la clase
     */
    public Avion() {
    }
    
    /**
     * Constructor de la clase que especifica la aerolinea.
     * Asigna el valor a la aerolinea que pertenece el avion
     * @param id_aerolinea 
     */
    public Avion(int id_aerolinea){
        this.id_aerolinea = id_aerolinea;
    }

    /**
     * Constructor de la clase que especifica todos los valores del avion
     * @param id_avion Identificador del avión
     * @param id_aerolinea Relación con la tabla aerolinea
     * @param modelo modelo del avion
     * @param capacidad Toneladas de peso que soporta el avión
     * @param disponibilidad Estado del avión. Si esta disponible, ocupado, 
     * en repación, etc.
     */
    public Avion(int id_avion, int id_aerolinea, String modelo, int capacidad, String disponibilidad) {
        this.id_avion = id_avion;
        this.id_aerolinea = id_aerolinea;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.disponibilidad = disponibilidad;
    }
    
    /**
     * Inserta un registro en la base de datos.
     * Asigna la aerolinea, modelo, capacidad y disponibilidad y lo ingresa dentro
     * de la base de datos.
     */
    public void registrarAvion() {
        try {
            con = new Conexion();

            final String SQL = "Insert Into avion(id_aerolinea, modelo, capacidad, disponibilidad) values(?, ?, ?, ?)";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setInt(1, id_aerolinea);
            sentencia.setString(2, modelo);
            sentencia.setInt(3, capacidad);
            sentencia.setString(4, disponibilidad);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Actualiza los datos de un registro en la base de datos.
     * Modifica todos los valores, salvo el identificador de la clase
     * 
     */
    public void modificarAvion(){
        try {
            con = new Conexion();
            //hacer mi consulta 
            final String mo = "UPDATE avion SET id_aerolinea=?, modelo=?, capacidad=?, disponibilidad=? WHERE id_avion=?";
            PreparedStatement sen = con.obtenerConexion().prepareStatement(mo);
            sen.setInt(1, id_aerolinea);
            sen.setString(2, modelo);
            sen.setInt(3, capacidad);
            sen.setString(4, disponibilidad);
            sen.setInt(5, id_avion);

            sen.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Utiliza el identificador para eliminar un avión en la base de datos.
     * Crea un String que representa la consulta a la base de datos y asigna el
     * valor de id_avion para no eliminar todos los registros.
     */
    public void eliminarAvion() {
        try {
            con = new Conexion();

            final String SQL = "Delete From avion where id_avion = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setInt(1, id_avion);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Elimina todos los aviones de una aerolinea dada.
     * Con el identificador de la aerolinea, elimina todos los aviones
     * pertenecientes a esta. Es utilizado para eliminar exitosamente la aerolinea
     */
    public void eliminarPorAerolinea(){
        try {
            con = new Conexion();

            final String SQL = "Delete From avion where id_aerolinea = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setInt(1, id_aerolinea);
            sentencia.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera una lista de Avion.
     * <p>
     * Realiza una consulta de tipo selección para obtener todos los aviones en
     * la base de datos. Utilizando Avion (id_avion, id_aerolinea, modelo, 
     * capacidad, disponibilidad) para crear una lista de tipo Avion
     * @return Lista con objetos tipo Avion con todos los datos inicializados.
     */
    public ArrayList<Avion> obtenerAviones() {
        ArrayList<Avion> listAviones = new ArrayList<Avion>();
        try {
            con = new Conexion();
            final String SQL = "Select * From avion";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            ResultSet record = sentencia.executeQuery();

            while (record.next()) {
                listAviones.add(new Avion(record.getInt("id_avion"), record.getInt("id_aerolinea"), record.getString("modelo"), record.getInt("capacidad"), record.getString("disponibilidad")));
            }
            return listAviones;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAviones;
    }

    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public int getId_aerolinea() {
        return id_aerolinea;
    }

    public void setId_aerolinea(int id_aerolinea) {
        this.id_aerolinea = id_aerolinea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
