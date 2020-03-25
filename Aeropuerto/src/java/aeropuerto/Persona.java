package aeropuerto;

import conPostgres.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * <h1>Persona</h1>
 * Representa los datos de una persona. Utilizado por el sistema para gestionar
 * los datos de la tabla boletos de la base de datos.
 * 
* @author Trejo Castillo Uriel
 * @author Vazquez Gutierrez Hugo Isaac
 * @author Sosa Alcauter Erik Emmanuel
 * 
* @version 1.0
 * @since 2020-01-24
 */
public class Persona {

    private int id_persona;
    private String nombre;
    private String apellidos;
    private String curp;
    private String correo;
    private String contra;
    private int nivel;
    private Conexion con;

    /**
     * Constructor vacio
     */
    public Persona() {
    }
    
    public Persona(String nombre, String apellidos){
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    /**
     * Constructor de la clase que especifica todos los valores de la persona
     *
     * @param id_persona Identificador de la persona
     * @param nombre Nombre propio de la person
     * @param apellidos Apellido paterno y materno o un solo apellido
     * @param curp Clave Única de Registro de Población
     * @param correo correo electronico del usuario. Usado como nombre de
     * usuario
     * @param contra Contraseña del usuario. Usado para comprobar identidad del
     * usuario
     * @param nivel Determina si un usuario es usuario comun o administrador
     */
    public Persona(int id_persona, String nombre, String apellidos, String curp, String correo, String contra, int nivel) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curp = curp;
        this.correo = correo;
        this.contra = contra;
        this.nivel = nivel;
    }

    /**
     * Inserta un registro en la base de datos. Asigna nombre, apellidos, curp,
     * correo y contraseña y lo ingresa dentro de la base de datos.
     */
    public void registrarPersona() {
        try {
            con = new Conexion();
            //consulta que tendra que hacer para insertar nuevas personas
            final String cons = "INSERT INTO persona(nombre,apellidos,curp,correo,contra, nivel) VALUES (?,?,?,?,?,1)";
            //lectura de mi cons hacia la base de datos
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(cons);
            //lectura de mis variables
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setString(3, curp);
            sentencia.setString(4, correo);
            sentencia.setString(5, contra);

            sentencia.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Modifica un registro en la base de datos. Cambia el nombre, apellidos,
     * curp, correo y contraseña utilizando el identificador de la persona
     */
    public void modificarPersona() {
        try {
            con = new Conexion();
            //hacer mi consulta 
            final String mo = "UPDATE persona SET nombre=?,apellidos=?, curp=?, correo=?, contra=? WHERE id_persona=?";
            PreparedStatement sen = con.obtenerConexion().prepareStatement(mo);
            sen.setString(1, nombre);
            sen.setString(2, apellidos);
            sen.setString(3, curp);
            sen.setString(4, correo);
            sen.setString(5, contra);
            sen.setInt(6, id_persona);

            sen.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Utiliza el identificador para eliminar una persona en la base de datos.
     * Crea un String que representa la consulta a la base de datos y asigna la
     * curp para no eliminar todos los registros.
     */
    public void eliminarPersona() {
        try {
            //Implementamos la conexion a la base de datos
            con = new Conexion();
            //Realizamos la consulta para eliminar los datos
            final String consulta = "DELETE FROM persona Where curp=?";
            PreparedStatement sen = con.obtenerConexion().prepareStatement(consulta);
            sen.setString(1, curp);
            sen.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Genera una lista de Personas.
     * <p>
     * Realiza una consulta de tipo selección para obtener todas las personas en
     * la base de datos. Utilizando Persona (nombre, apellidos, curp, correo,
     * contra para crear una lista de tipo Persona
     *
     * @return Lista con objetos tipo Persona con todos los datos inicializados.
     */
    public ArrayList<Persona> obtenerPersonas() {
        ArrayList<Persona> listPersonas = new ArrayList<>();
        try {
            con = new Conexion();
            final String SQL = "Select * From personas";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            ResultSet record = sentencia.executeQuery();

            while (record.next()) {
                listPersonas.add(new Persona(record.getInt("id_persona"), record.getString("nombre"), record.getString("apellidos"), record.getString("curp"), record.getString("correo"), record.getString("contra"), record.getInt("nivel")));
            }
            return listPersonas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPersonas;
    }
    
    /**
     * Inicio de sesión de la página.
     * Comprueba el correo, contraseña y el tipo de usuario
     */
    public void session() {

        try {
            con = new Conexion();
            //final String sql = "SELECT correo, contra FROM persona WHERE correo=? and contra=?";
            final String sql = "SELECT nivel FROM persona WHERE correo=? and contra=?";
            
            PreparedStatement sen = con.obtenerConexion().prepareStatement(sql);
            sen.setString(1, correo);
            sen.setString(2, contra);
            ResultSet rs = sen.executeQuery();
            if (rs.next()) {
                nivel=rs.getInt("nivel");
                setCorreo(rs.getString("correo")) ;
                setContra(rs.getString("contra"));
            } else {
                setCorreo("");
                setContra("");
                //setNivel("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getUsuario() {
        return correo;
    }

    public void setUsuario(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
}
