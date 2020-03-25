package aeropuerto;

import conPostgres.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * <h1>Boletos</h1>
 * Representa los datos de un boleto. Utilizado por el sistema para gestionar
 * los datos de la tabla boletos de la base de datos.
 * 
* @author Sosa Alcauter Erik Emmanuel
 * @author Vazquez Gutierrez Hugo Isaac
 * @author Trejo Castillo Uriel
 * 
* @version 1.0
 * @since 2020-01-24
 */
public class Boletos {

    private int id_boleto;
    private int num_vuelo;
    private int id_usuario;
    private String clase;
    private String codigo;

    //Variable que es utilizada para realizar la conexion a la base de datos
    private Conexion con;

    /**
     * Constructor vacio
     */
    public Boletos() {
    }

    /**
     * Constructor que inicializa el vuelo. Inicializa el numero de vuelo al que
     * pertenece el boleto.
     *
     * @param num_vuelo
     */
    public Boletos(int num_vuelo) {
        this.num_vuelo = num_vuelo;
    }

    /**
     * Constructor de la clase que especifica todos los valores del boleto
     *
     * @param id_boleto Identificador de boleto
     * @param num_vuelo Relación con la tabla vuelos
     * @param id_usuario Relación con la tabla usuario
     * @param asiento Lugar del avión donde viaja el usuario
     * @param clase Tipo de asiento que compra el usuario
     * @param numMaletas maletas del usuario en el avión
     * @param precio valor monetario del boleto
     */
    public Boletos(int id_boleto, int num_vuelo, int id_usuario, String clase, String codigo) {
        this.id_boleto = id_boleto;
        this.num_vuelo = num_vuelo;
        this.id_usuario = id_usuario;
        this.clase = clase;
        this.codigo = codigo;
    }
    
    public Boletos(int num_vuelo, int id_usuario, String clase, String codigo) {
        this.num_vuelo = num_vuelo;
        this.id_usuario = id_usuario;
        this.clase = clase;
        this.codigo = codigo;
    }

    /**
     * Inserta un registro en la base de datos. Asigna vuelo, asiento, clase,
     * precio y numero de maetas y lo ingresa dentro de la base de datos.
     */
    public void registrarBoleto() {
        try {
            con = new Conexion();

            final String SQL = "Insert Into Boletos(num_vuelo, id_usuario, clase, codigo) VALUES(?, ?, ?, ?)";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setInt(1, num_vuelo);
            sentencia.setInt(2, id_usuario);
            sentencia.setString(3, clase);
            sentencia.setString(4, codigo);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Utiliza el identificador para eliminar un boleto en la base de datos.
     * Crea un String que representa la consulta a la base de datos y asigna el
     * valor de id_boleto para no eliminar todos los registros.
     */
    public void eliminarBoleto() {
        try {
            con = new Conexion();

            final String SQL = "Delete From boletos where id_boleto = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            sentencia.setInt(1, id_boleto);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina todos los Boletos de un vuelo dada. Con el identificador del
     * vuelo, elimina todos los boletos pertenecientes a este. Es utilizado para
     * eliminar exitosamente los vuelos
     */
    public void eliminarPorVuelo() {
        try {
            con = new Conexion();

            final String SQL = "Delete From boletos where num_vuelo = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            sentencia.setInt(1, num_vuelo);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera una lista de Boletos.
     * <p>
     * Realiza una consulta de tipo selección para obtener todos los boletos en
     * la base de datos. Utilizando Boletos (id_boleto, num_vuelo, id_usuario,
     * asiento, clase, precio, numMaletas) para crear una lista de tipo Boletos
     *
     * @return Lista con objetos tipo Boletos con todos los datos inicializados.
     */
    public ArrayList<Boletos> obtenerBoletos() {
        ArrayList<Boletos> listBoletos = new ArrayList<>();
        try {
            con = new Conexion();
            final String SQL = "Select * From boletos where id_boleto = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            sentencia.setInt(1, id_boleto);
            ResultSet record = sentencia.executeQuery();

            while (record.next()) {
                listBoletos.add(new Boletos(record.getInt("id_boleto"), record.getInt("num_vuelo"), record.getInt("id_usuario"), record.getString("clase"), record.getString("codigo")));
            }
            return listBoletos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBoletos;
    }

    public int maxBoletos(int id, int resta) {
        int contador = 0;
        try {
            con = new Conexion();
            final String SQL = "Select id_boleto From boletos Where num_vuelo = ?";

            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            sentencia.setInt(1, id);
            ResultSet record = sentencia.executeQuery();
            while (record.next()) {
                contador++;
            }
            contador -= resta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contador;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getId_boleto() {
        return id_boleto;
    }

    public void setId_boleto(int id_boleto) {
        this.id_boleto = id_boleto;
    }

    public int getNum_vuelo() {
        return num_vuelo;
    }

    public void setNum_vuelo(int num_vuelo) {
        this.num_vuelo = num_vuelo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
