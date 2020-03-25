package aeropuerto;

import conPostgres.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
* <h1>Relacion Rutas y Escalas</h1>
* Representa los datos de rutas y escalas. Utilizado por el sistema para
* gestionar la logistica entre las tablas rutas y escalas, asi como manejar las
* fechas de llegada y partida del vuelo
*
* @author  Trejo Castillo Uriel
* @author  Sosa Alcauter Erik Emmanuel
* @author  Vazquez Gutierrez Hugo Isaac
* 
* @version 1.0
* @since   2020-01-24
*/
public class Rutas_Escalas {

    private int id_RutaEscala;
    private int id_ruta;
    private int id_escala;
    private int contexto;
    
    //Variable que es utilizada para realizar la conexion a la base de datos
    private Conexion con;

    /**
     * Constructor vacio
     */
    public Rutas_Escalas() {

    }

    /**
     * Constructor de la clase que especifica todos los valores de Rutas Escalas
     * @param id_RutaEscala Identificador de la table
     * @param id_ruta Relación con la tabla ruta
     * @param id_escala Relación con la tabla escala
     * @param contexto Valor que asigna informacion adicional necesaria para las
     * fecha y rutas.
     *<p> Si contexto es el primer numero (1), el sistema asigna origen
     *<p> Si contexto es el ultimo número (n), el sistema asigna destino
     *<p> Si contexto es un numero intermedio, el sistema asigna una de 2 opciones:
     *<p> Si contexto es <b>par</b>, el sistema asigna destino
     *<p> Si contexto es <b>impar</b>, el sistema asigna origen
     *          
     */
    public Rutas_Escalas(int id_RutaEscala, int id_ruta, int id_escala, int contexto) {
        this.id_RutaEscala = id_RutaEscala;
        this.id_ruta = id_ruta;
        this.id_escala = id_escala;
        this.contexto = contexto;        
    }
    
    /**
     * Inserta un registro en la base de datos.
     * Asigna ruta, escala y el contexto de la fecha y lo ingresa dentro de
     * la base de datos
     */
    public void registrarRutas_Escalas() {
        try {
            con = new Conexion();
            final String SQL = "INSERT INTO ruta_escalas(id_ruta, id_escala, contexto) values(?, ?, ?)";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            sentencia.setInt(1, id_ruta);
            sentencia.setInt(2, id_escala);
            sentencia.setInt(3, contexto);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Utiliza el identificador para eliminar una ruta_escala en la base de datos.
     * Crea un String que representa la consulta a la base de datos y asigna el
     * valor de id_RutaEscala para no eliminar todos los registros.
     */
    public void eliminarRutas() {
        try {
            con = new Conexion();
            final String SQL = "DELETE FROM ruta_escalas WHERE id_RutaEscala = ?";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);
            //Mecanismo para evitar un conexion SQL
            sentencia.setInt(1, id_RutaEscala);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera una lista de Ruta_Escalas.
     * <p>
     * Realiza una consulta de tipo selección para obtener todas las Rutas_Escalas
     * en la base de datos. Utilizando Rutas_Escalas (id_ruta, id_escala, contexto),
     * crea un numero de objetos iguales a las Rutas_Escalas en la base de datos
     * y los asigna a cada elemento de la lista tipo Rutas_Escalas.
     * @return Lista con objetos tipo RutasEscalas con todos los datos inicializados.
     */
    public ArrayList<Rutas_Escalas> obtenerRutas_Escalas() {
        ArrayList<Rutas_Escalas> listRutasEscalas = new ArrayList<>();
        try {
            con = new Conexion();
            final String SQL = "SELECT * FROM rutas";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            ResultSet record = sentencia.executeQuery();

            while (record.next()) {
                listRutasEscalas.add(new Rutas_Escalas(record.getInt("id_RutaEscala"), record.getInt("id_ruta"), record.getInt("id_escala"), record.getInt("contexto")));
            }
            return listRutasEscalas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRutasEscalas;
    }

    public int getId_RutaEscala() {
        return id_RutaEscala;
    }

    public void setId_RutaEscala(int id_RutaEscala) {
        this.id_RutaEscala = id_RutaEscala;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public int getId_escala() {
        return id_escala;
    }

    public void setId_escala(int id_escala) {
        this.id_escala = id_escala;
    }

    public int getContexto() {
        return contexto;
    }

    public void setContexto(int contexto) {
        this.contexto = contexto;
    }
}
