package aeropuerto;

import conPostgres.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * <h1>Escalas</h1>
 * Representa los datos de una escala. Utilizado por el sistema para gestionar
 * los datos de la tabla escalas de la base de datos.
 *
 * @author Sosa Alcauter Erik Emmanuel
 * @author Vazquez Gutierrez Hugo Isaac
 * @author Trejo Castillo Uriel
 *
 * @version 1.0
 * @since 2020-01-24
 */
public class Escalas {

    private int id_escala;
    private String pais;
    private String aeropuerto;
    private String fecha;
    private String hora;

    //Variable que es utilizada para realizar la conexion a la base de datos
    private Conexion con;

    /**
     * Constructor de la clase
     */
    public Escalas() {
    }

    /**
     * Constructor de la clase que especifica todos los valores de la escala
     * <p>
     * <b>Nota:</b> La fecha y hora requiere de contexto presente en
     * Ruta_Escalas
     *
     * @param id_escala Identificador de la escala
     * @param pais Pais donde se encuentra el aeropuerto
     * @param aeropuerto Nombre del aeropuerto
     * @param fecha Dia, mes y año que la escala posee
     * @param hora Hora que la escala posee
     * @see Rutas_Escalas
     */
    public Escalas(int id_escala, String pais, String aeropuerto, String fecha, String hora) {
        this.id_escala = id_escala;
        this.pais = pais;
        this.aeropuerto = aeropuerto;
        this.fecha = fecha;
        this.hora = hora;
    }

    /**
     * Inserta un registro en la base de datos. Asigna pais de origen, nombre
     * aeropuerto, fecha y hora y lo ingresa dentro de la base de datos.
     */
    public void registrarEscalas() {
        try {
            con = new Conexion();

            final String SQL = "INSERT INTO escalas (pais, aeropuerto, fecha, hora) VALUES (?, ?, ?, ?);";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            //Mecanismo para evitar un conexion SQL
            sentencia.setString(1, pais);
            sentencia.setString(2, aeropuerto);
            sentencia.setDate(3, Date.valueOf(fecha));
            sentencia.setString(4, hora);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Utiliza el identificador para eliminar una escala en la base de datos.
     * Crea un String que representa la consulta a la base de datos y asigna el
     * valor de id_escala para no eliminar todos los registros.
     */
    public void eliminarEscalas() {
        try {
            con = new Conexion();
            final String SQL = "DELETE FROM escalas WHERE id_escala = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            //Mecanismo para evitar un conexion SQL
            sentencia.setInt(1, id_escala);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera una lista de Escalas.
     * <p>
     * Realiza una consulta de tipo selección para obtener todas las escalas en
     * la base de datos. Utilizando Escalas (pais, aeropuerto, fecha, hora) para
     * crear una lista de tipo Escalas
     *
     * @return Lista con objetos tipo Escalas con todos los datos inicializados.
     */
    public ArrayList<Escalas> obtenerEscalas() {
        ArrayList<Escalas> listEscalas = new ArrayList<>();

        try {
            con = new Conexion();
            final String SQL = "SELECT * FROM escalas";

            PreparedStatement ps = con.obtenerConexion().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listEscalas.add(new Escalas(rs.getInt("id_escala"), rs.getString("pais"), rs.getString("aeropuerto"), rs.getString("fecha"), rs.getString("hora")));
            }
            return listEscalas;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEscalas;
    }

    public ArrayList<String> obtenerEscalasSinRepetir() {
        ArrayList<String> listEscalas = new ArrayList<>();

        try {
            con = new Conexion();
            final String SQL = "SELECT pais FROM escalas";
            String iterador;
            boolean añadir = true;

            PreparedStatement ps = con.obtenerConexion().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String actual = rs.getString("pais").trim();
                for (int i = 0; i < listEscalas.size(); i++) {
                    if (añadir) {
                        iterador = listEscalas.get(i);
                        if (iterador.equals(actual)) {
                            añadir = false;
                        }
                    }
                }
                if (añadir) {
                    listEscalas.add(actual);
                }
                añadir = true;
            }
            return listEscalas;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEscalas;
    }
    
    public ArrayList<Escalas> obtenerEscalasPorPais(String pais) {
        ArrayList<Escalas> listEscalas = new ArrayList<>();

        try {
            con = new Conexion();
            final String SQL = "SELECT * FROM escalas Where pais = ?";
            

            PreparedStatement ps = con.obtenerConexion().prepareStatement(SQL);
            ps.setString(1, pais);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listEscalas.add(new Escalas(rs.getInt("id_escala"), rs.getString("pais"), rs.getString("aeropuerto"), rs.getString("fecha"), rs.getString("hora")));
            }
            return listEscalas;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEscalas;
    }

    public int getId_escala() {
        return id_escala;
    }

    public void setId_escala(int id_escala) {
        this.id_escala = id_escala;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(String aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
