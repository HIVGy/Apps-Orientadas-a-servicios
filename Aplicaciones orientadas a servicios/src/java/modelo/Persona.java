package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
* <h1>Datos de una persona</h1>
* Representa los datos de una persona. Utilizado por el sistema para gestionar
* los datos de la tabla persona de la base de datos. 
*
* @author  Vazquez Gutierrez Hugo Isaac
* 
* @version 1.0
*/
public class Persona {

    private String nombre, curp, apellidos, rfc, edo_civil;
    private long id_persona;

    /**
     * Constructor de la clase
     */
    public Persona() {
    }

    /**
     * Constructor de la clase que especifica los valores.
     * Es utilizado por el metodo obtenerPersonas
     * @param id_persona Identificador de la persona
     * @param nombre Nombre propio de la persona
     * @param apellidos Apellido paterno y materno. Algunas veces solo 1 apellido
     * @param curp Clave Unica de Registro Poblacional
     * @param rfc Registro Federal de Contribuyentes
     * @param edo_civil Estado civil de la persona
     */
    public Persona(long id_persona, String nombre, String apellidos, String curp, String rfc, String edo_civil) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curp = curp;
        this.rfc = rfc;
        this.edo_civil = edo_civil;
    }
    
    /**
     * Obtiene los datos de la persona utilizando la CURP.
     * Utilizando una consulta de tipo selección, asigna los datos de la 
     * coincidencia a las variables de la clase.
     */
    public void obtenerPersonaByCurp() {
        try {
            Conexion con = new Conexion();
            
            final String SQL = "Select * from persona where curp = ?";
            
            /*Precompila la consulta para mejorar la eficiencia del código
            o para ejecutar varias veces una misma consulta.
            */
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            /*
            Se utiliza un "?" para asignar el valor de forma posterior, impidiendo que
            se realice inyección SQL a la base de datos
            */
            sentencia.setString(1, curp);
            //Despues de asignar se ejecuta la consulta
            ResultSet record = sentencia.executeQuery();
            //Se crea un ciclo que se ejecutara hasta que no haya mas registros
            while (record.next()) {
                id_persona = record.getLong("id_persona");
                nombre = record.getString("nombre");
                System.out.println(nombre);
                curp = record.getString("curp");
                apellidos = record.getString("apellidos");
                rfc = record.getString("rfc");
                edo_civil = record.getString("edo_civil");
            }
        } catch (Exception e) {
            /*
            Se utiliza printStackTrace() para imprimir el error en la pantalla del
            usuario. Puede llegar a ser util para encontrar errores en el código y
            solventarlos.
            No se recomienda (se recomienda lo opuesto, no usarlo) usarlo solamente
            para pruebas o casos de aprendizaje donde no es necesario manejar los
            errores individualmente, pues se encuentra en un ambiente controlado.
            */
            e.printStackTrace();
        }
    }

    /**
     * Inserta un registro en la base de datos.
     * Asigna el nombre, apellidos, curp, rfc y estado civil de la persona y
     * lo ingresa dentro de la base de datos.
     */
    public void registrarPersona() {
        try {
            Conexion con = new Conexion();

            /*
            Se maneja persona(nombre, apellidos, curp, rfc, edo_civil) omitiendo
            el valor de la id_persona ya que se utiliza un serial en la tabla.
            Esto hace que no sea necesario indicar el valor del identificador
            */
            final String SQL = "Insert Into persona(nombre, apellidos, curp, rfc, edo_civil) values( ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            sentencia.setString(1, nombre);
            sentencia.setString(2, apellidos);
            sentencia.setString(3, curp);
            sentencia.setString(4, rfc);
            sentencia.setString(5, edo_civil);
            /*
            execute.- Metodo usado en general para cualquier accion, regresa un boleano
            executeQuery.- Usado solo para select, regresa un objeto ResultSet
                   que contiene el resultado del select realizado
            executeUpdate.- Usado en todos menos select, regresa un número entero
                    que representa cuantas columnas fueron afectadas o regresa 0
                    si la consulta no regresa nada
            */
            sentencia.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Elimina un registro de la base de datos.
     * <p>
     * Utilizando la Clave Única de Registro de Población (CUPR, elimina el registro
     * que coincida
     */
    public void eliminarPersonaByCurp(){
        try {
            Conexion con = new Conexion();
            
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
     * Realiza una consulta de tipo selección para obtener todas las personas
     * en la base de datos. Utilizando Persona (nombre, apellidos, curp, rfc,
     * edo_civil), crea un numero de objetos iguales a las personas y lo asigna
     * a cada elemento de la lista tipo Aerolinea.
     * @return Lista con objetos tipo Persona con todos los datos inicializados.
     */
    public ArrayList<Persona> obtenerPersonas() {
        /*
        Se utiliza una lista de tipo Persona para tener una lista que contenga
        todas las variables de persona sin complicar de más la logistica
        */
        ArrayList<Persona> listPersonas = new ArrayList<Persona>();
        try {
            Conexion con = new Conexion();
            final String SQL = "Select * from persona";
            PreparedStatement sentencia = con.obtenerConexion().prepareStatement(SQL);

            ResultSet record = sentencia.executeQuery();

            while (record.next()) {
                /*
                Se usa un constructor con valores inicializados para no asignarlos
                en distintas lineas de código
                */
                listPersonas.add(new Persona(record.getLong("id_persona"), record.getString("nombre"), record.getString("apellidos"), record.getString("curp"), record.getString("rfc"), record.getString("edo_civil")));
            }
            return listPersonas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPersonas;
    }
    
    /**
     * Modifica un registro en la base de datos. Cambia el nombre, apellidos,
     * curp, rfc y estado civil utilizando el identificador de la persona
     */
    public void modificarPersona() {
        try {
            Conexion con = new Conexion();
            //hacer mi consulta 
            final String mo = "UPDATE persona SET nombre=?,apellidos=?, curp=?, rfc=?, edo_civil=? WHERE id_persona=?";
            PreparedStatement sen = con.obtenerConexion().prepareStatement(mo);
            sen.setString(1, nombre);
            sen.setString(2, apellidos);
            sen.setString(3, curp);
            sen.setString(4, rfc);
            sen.setString(5, edo_civil);
            sen.setLong(6, id_persona);

            sen.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Utiliza el identificador para eliminar una persona en la base de datos.
     * Con el identificador de la persona, elimina la persona, para evitar
     * borrar todos los registros
     */
    public void eliminarPersona() {
        try {
            //Implementamos la conexion a la base de datos
            Conexion con = new Conexion();
            //Realizamos la consulta para eliminar los datos
            final String consulta = "DELETE FROM persona Where id_persona=?";
            PreparedStatement sen = con.obtenerConexion().prepareStatement(consulta);
            sen.setLong(1, id_persona);
            sen.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * A través de la CURP, el sistema determina la edad del usuario
     * La funcion hace uso de la clase LocalDate para calcular el periodo de tiempo
     * entre la fecha de nacimiento presente en la CURP y la fecha actual
     * <b>Nota:</b> Este metodo hace uso de la clase {@link LocalDate} que genera
     * una fecha sin zona horaria y que facilita la implementacion de varios metodos
     * como el calculo de la edad. También se hace uso de la clase Period que muestra
     * cierto periodo de tiempo basado en una fecha.
     * 
     * @return Años La edad de la persona en años
     * @see LocalDate
     * @see Period
     */
    public int obtenerEdad() {
        try{
            //Separa la fecha de la curp en sus componentes: año, mes y dia
            String year = curp.substring(4, 6);
            String mes = curp.substring(6, 8);
            String dia = curp.substring(8, 10);
            //Indicador que determina si la persona nacio en 1900's o 2000's
            String indicador = curp.substring(16, 17);
            //Variable que muestra la fecha actual
            LocalDate hoy = LocalDate.now();
            
            /*
            Se utiliza un switch debido a que se deben de considerar los 10
            digitos existentes y el caso de las letras, ya que seria un poco
            impractica estar escribiendo el mismo codigo utilizando la 
            estructura de If Else
            */
            switch (indicador) {
                //En caso de que sea un numero se asigna 19 del siglo + el año
                case "0":case "1":case "2":case "3":case "4":case "5":
                case "6":case "7":case "8":case "9":
                    year = "19" + year;
                    break;
                default:
                    //En cualquier otro caso (letra) se asigna siglo 20 + año
                    year = "20" + year;
                    break;
            }
            //Tras determinar el siglo de nacimiento ya se puede crear la fecha de nacimiento
            LocalDate nacimiento = LocalDate.of(Integer.parseInt(year), Integer.parseInt(mes),
                Integer.parseInt(dia));
            //Period permite calcular directamente la cantidad de años que han pasado
            return Period.between(nacimiento, hoy).getYears();
            }catch(Exception e){
                e.printStackTrace();
            }
        //Regresa -1 en caso de que ocurra algun error inesperado
        return -1;
        }
    
    /**
     * Obtiene los generos de las personas en la base de datos.
     * Genera una lista con la inicial del genero a partir de las CURP de las 
     * personas en la base de datos.
     * @return Lista con los generos a los que pertecen las personas.
     */
    public List<String> obtenerGenero(){
        List<Persona> lista = obtenerPersonas();
        List<String> genero = new ArrayList<String>();
        for (Persona list : lista){
            //Se añade a la lista el genero. Substring es la letra que corresponde
            genero.add(list.curp.substring(10,11));
        }
        return genero;
    }
    
    /**
     * Obtiene los estados de las personas en la base de datos.
     * Genera una lista con los códigos de estado de las CURP de las personas 
     * en la base de datos.
     * @return Lista con los estados a los que pertenecen las personas de la base
     */
    public List<String> obtenerEstados(){
        List<Persona> lista = obtenerPersonas();
        List<String> estado = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++){
            //Se añade el código de estado de la persona
            estado.add(lista.get(i).curp.substring(11,13));
        }
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEdo_civil() {
        return edo_civil;
    }

    public void setEdo_civil(String edo_civil) {
        this.edo_civil = edo_civil;
    }

    public long getId_persona() {
        return id_persona;
    }

    public void setId_persona(long id_persona) {
        this.id_persona = id_persona;
    }
}
