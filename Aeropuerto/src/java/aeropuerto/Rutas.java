package aeropuerto;

import conPostgres.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * <h1>Boletos</h1>
 * Representa los datos de unas rutas. Utilizado por el sistema para gestionar
 * los datos de la tabla boletos de la base de datos.
 * 
* @author Sosa Alcauter Erik Emmanuel
 * @author Vazquez Gutierrez Hugo Isaac
 * @author Trejo Castillo Uriel
 * 
* @version 1.0
 * @since 2020-01-24
 */
public class Rutas {

    private int id_rutas;
    private double costoPorVuelo;
    private int megaVuelo;
    private String megaPais;
    private String megaAeropuerto;
    private String megaFecha;
    private String megaHora;

    //Variable que es utilizada para realizar la conexion a la base de datos
    private Conexion con;

    /**
     * Constructor vacio
     */
    public Rutas() {

    }

    /**
     * Constructor de la clase que especifica todos los valores de las rutas
     *
     * @param id_rutas Identificador de la ruta
     * @param costoPorVuelo Costo que lleva realizar esta ruta una vez
     */
    public Rutas(int id_rutas, double costoPorVuelo) {
        this.id_rutas = id_rutas;
        this.costoPorVuelo = costoPorVuelo;

    }

    public Rutas(int megaVuelo, String megaPais, String megaAeropuerto,
            String megaFecha, String megaHora) {
        this.megaVuelo = megaVuelo;
        this.megaPais = megaPais;
        this.megaAeropuerto = megaAeropuerto;
        this.megaFecha = megaFecha;
        this.megaHora = megaHora;
    }

    /**
     * Inserta un registro en la base de datos. Asigna costo de la ruta y lo
     * ingresa dentro de la base de datos
     */
    public void registrarRutas() {
        try {
            con = new Conexion();
            final String SQL = "INSERT INTO rutas(costoRuta) values(?)";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            sentencia.setDouble(1, costoPorVuelo);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Utiliza el identificador para eliminar una ruta en la base de datos. Crea
     * un String que representa la consulta a la base de datos y asigna el valor
     * de id_ruta para no eliminar todos los registros.
     */
    public void eliminarRutas() {
        try {
            con = new Conexion();
            final String SQL = "DELETE FROM rutas WHERE id_ruta = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            //Mecanismo para evitar un conexion SQL
            sentencia.setInt(1, id_rutas);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera una lista de Rutas.
     * <p>
     * Realiza una consulta de tipo selección para obtener todas las aerolineas
     * en la base de datos. Utilizando Rutas (id_rutas, costoRuta), crea un
     * numero de objetos iguales a las aerolineas en la base de datos y los
     * asigna a cada elemento de la lista tipo Rutas.
     *
     * @return Lista con objetos tipo Rutas con todos los datos inicializados.
     */
    public ArrayList<Rutas> obtenerRutas() {
        ArrayList<Rutas> listRutas = new ArrayList<>();
        try {
            con = new Conexion();
            final String SQL = "SELECT * FROM rutas";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            ResultSet record = sentencia.executeQuery();

            while (record.next()) {
                listRutas.add(new Rutas(record.getInt("id_ruta"), record.getDouble("costoRuta")));
            }
            return listRutas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRutas;
    }

    public ArrayList<Rutas> obtenerMegaRutas(String pais) {
        ArrayList<Rutas> listRutas = new ArrayList<>();
        int iterador;
        boolean añadir = true;

        try {
            con = new Conexion();
            final String SQL
                    = "Select v.num_vuelo, e.pais, e.aeropuerto, e.fecha, e.hora "
                    + "FROM vuelos v "
                    + "INNER JOIN ruta_escalas r on v.id_ruta = r.id_ruta "
                    + "INNER JOIN escalas e on r.id_escala = e.id_escala "
                    + "WHERE e.pais = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            sentencia.setString(1, pais);

            ResultSet record = sentencia.executeQuery();

            while (record.next()) {
                listRutas.add(new Rutas(record.getInt("num_vuelo"), record.getString("pais"),
                        record.getString("aeropuerto"), record.getString("fecha"),
                        record.getString("hora")));
            }
            return listRutas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRutas;
    }

    public int getId_rutas() {
        return id_rutas;
    }

    public void setId_rutas(int id_rutas) {
        this.id_rutas = id_rutas;
    }

    public double getCostoPorVuelo() {
        return costoPorVuelo;
    }

    public void setCostoPorVuelo(double costoPorVuelo) {
        this.costoPorVuelo = costoPorVuelo;
    }

    public int getMegaVuelo() {
        return megaVuelo;
    }

    public void setMegaVuelo(int megaVuelo) {
        this.megaVuelo = megaVuelo;
    }

    public String getMegaPais() {
        return megaPais;
    }

    public void setMegaPais(String megaPais) {
        this.megaPais = megaPais;
    }

    public String getMegaAeropuerto() {
        return megaAeropuerto;
    }

    public void setMegaAeropuerto(String megaAeropuerto) {
        this.megaAeropuerto = megaAeropuerto;
    }

    public String getMegaFecha() {
        return megaFecha;
    }

    public void setMegaFecha(String megaFecha) {
        this.megaFecha = megaFecha;
    }

    public String getMegaHora() {
        return megaHora;
    }

    public void setMegaHora(String megaHora) {
        this.megaHora = megaHora;
    }
}
