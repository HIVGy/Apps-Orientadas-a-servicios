package primerPaquete;

/**
 * <h1>Prueba de figuras</h1>
 * Clase principal de la práctica de figuras.
 * Utilizando un main ejecuta un ejemplo que prueba la funcionalidad del cuadrado
 * 
 * @author Vazquez Gutierrez Hugo Isaac
 * 
 * @version 1.0
 */
public class DemoFigura {

    /**
     * Clase principal de java encargada de ejecutar el código
     * @param args Argumentos que son generados por Java
     */
    public static void main(String[] args) {
        // Figura pedrito = new Figura();
        Cuadrado cuadrito = new Cuadrado();
        cuadrito.setLado(5);
        System.out.println(cuadrito.obtenerPerimetro());
    }
}
