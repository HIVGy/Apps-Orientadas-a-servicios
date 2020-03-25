package aeropuerto;

import conPostgres.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * <h1>Aerolinea</h1>
 * Representa los datos de una aerolinea. Utilizado por el sistema para
 * gestionar los datos de la tabla avion de la base de datos.
 * 
* @author Vazquez Gutierrez Hugo Isaac
 * @author Trejo Castillo Uriel
 * @author Sosa Alcauter Erik Emmanuel
 * @version 1.0
 * @since 2020-01-24
 */
public class Aerolinea {

    private int id_aerolinea;
    private String creacion;
    private String nombre;
    private String rfc;

    //Variable que es utilizada para realizar la conexion a la base de datos
    private Conexion con;

    /**
     * Constructor de la clase
     */
    public Aerolinea() {
    }

    /**
     * Constructor de la clase que especifica el nombre de la aerolinea
     *
     * @param nombre Nombre común con el que se identifica la aerolinea
     */
    public Aerolinea(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Constructor de la clase que especifica el id de aerolinea.
     *
     * @param id_aerolinea Identificador de la aerolinea
     */
    public Aerolinea(int id_aerolinea) {
        this.id_aerolinea = id_aerolinea;
    }

    /**
     * Constructor de la clase que especifica todos los valores de la aerolinea
     *
     * @param id_aerolinea Identificador de la aerolinea
     * @param nombre Nombre común con el que se identifica la aerolinea
     * @param rfc Registro Federal de Contribuyentes. Identifica como
     * contribuyente a la empresa.
     */
    public Aerolinea(int id_aerolinea, String nombre, String rfc, String creacion) {
        this.id_aerolinea = id_aerolinea;
        this.nombre = nombre;
        this.rfc = rfc;
        this.creacion = creacion;
    }

    /**
     * Inserta un registro en la base de datos. Asigna el nombre y el rfc de la
     * Aerolinea y lo ingresa dentro de la base de datos.
     */
    public void registrarAerolinea() {
        try {
            con = new Conexion();

            final String SQL = "Insert Into aerolinea(nombre, rfc, creacion) values(?, ?, ?)";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setString(1, nombre);
            sentencia.setString(2, rfc);
            sentencia.setDate(3, Date.valueOf(creacion));
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza los datos de un registro en la base de datos. Modifica todos
     * los valores de la clase, exceptuando el identificador de la clase
     *
     */
    public void modificarAerolinea() {
        try {
            con = new Conexion();
            //hacer mi consulta 
            final String mo = "UPDATE aerolinea SET nombre=?, rfc=?, creacion=? WHERE id_aerolinea=?";
            PreparedStatement sen = con.obtenerConexion().prepareStatement(mo);
            sen.setString(1, nombre);
            sen.setString(2, rfc);
            sen.setDate(3, Date.valueOf(creacion));
            sen.setInt(4, id_aerolinea);
            sen.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Utiliza el identificador para eliminar una aerolinea en la base de datos.
     * Crea un String que representa la consulta a la base de datos y asigna el
     * valor de id_aerolinea para no eliminar todos los registros.
     */
    public void eliminarAerolinea() {
        try {
            con = new Conexion();

            final String SQL = "Delete From aerolinea where id_aerolinea = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setInt(1, id_aerolinea);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera una lista de Aerolineas. Realiza una consulta de tipo selección
     * para obtener todas las aerolineas en la base de datos. Utilizando
     * Aerolinea (id_aerolinea, nombre, rfc), crea un numero de objetos iguales
     * a las aerolineas en la base de datos y los asigna a cada elemento de la
     * lista tipo Aerolinea.
     *
     * @return Lista con objetos tipo Aerolinea con todos los datos
     * inicializados.
     */
    public ArrayList<Aerolinea> obtenerAerolineas() {
        ArrayList<Aerolinea> listAerolineas = new ArrayList<Aerolinea>();
        try {
            con = new Conexion();
            final String SQL = "Select * From aerolinea";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            ResultSet record = sentencia.executeQuery();

            while (record.next()) {
                listAerolineas.add(new Aerolinea(record.getInt("id_aerolinea"),
                        record.getString("nombre"), record.getString("rfc"),
                        record.getString("creacion")));
            }
            return listAerolineas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAerolineas;
    }

    /**
     * Obtiene el identificador de la aerolinea utilizando el nombre de la
     * aerolinea. Utilizado para no mostrar el id_aerolinea en interfaces de
     * usuario.
     *
     * @return Identificador de la aerolinea
     */
    public int obtenerIdPorNombre() {
        try {
            con = new Conexion();
            final String SQL = "Select id_aerolinea From aerolinea where nombre = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setString(1, nombre);

            ResultSet record = sentencia.executeQuery();
            while (record.next()) {
                id_aerolinea = record.getInt("id_aerolinea");
            }
            return id_aerolinea;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_aerolinea;
    }

    /**
     * Obtiene el nombre de la aerolinea utilizando el identificador de la
     * aerolinea. Utilizado para mostrar el nombre en lugar del Id al usuario
     *
     * @returns Nombre de la aerolinea
     */
    public String obtenerNombrePorId() {
        try {
            con = new Conexion();
            final String SQL = "Select nombre From aerolinea where id_aerolinea = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setInt(1, id_aerolinea);

            ResultSet record = sentencia.executeQuery();
            while (record.next()) {
                nombre = record.getString("nombre");
            }
            return nombre;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombre;
    }

    public int getId_aerolinea() {
        return id_aerolinea;
    }

    public void setId_aerolinea(int id_aerolinea) {
        this.id_aerolinea = id_aerolinea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCreacion() {
        return creacion;
    }

    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }
}
