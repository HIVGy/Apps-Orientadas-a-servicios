package primerPaquete;

/**
 * <h1>Lados de un cuadrado</h1>
 * Representa la abstracción de un cuadrado a través de sus lados Hereda de la
 * clase figura
 * 
 * @author Vazquez Gutierrez Hugo Isaac
 * 
 * @version 1.0
 */
public class Cuadrado extends Figura {

    private double lado;

    
    /**
     * Constructor vacio
     */
    public Cuadrado() {
        //Se debe escribir si se crea un contructor por sobrecarga
        //Constructor vacio, por defecto
    }

    /**
     * Constructor de clase que inicializa el lado
     * @param lado Medida en centimetros del lado del cuadrado
     */
    public Cuadrado(double lado) {
        this.lado = lado;
    }

    /**
     * Obtiene el área del cuadrado.
     * Multiplica el lado por si mismo para obtener el área
     * Sobreescribe el metodo obtenerArea de {@link Figura}
     * 
     * @return El área del cuadrado en centimetros cuadrados
     * @see Figura
     */
    @Override
    public double obtenerArea() {
        return lado * lado;
    }

    /**
     * Obtiene el perimetro del cuadrado.
     * Determina el perimetro utilizando una multiplicación en lugar de una suma
     * 
     * @return Perimetro del cuadrado en centimetros.
     */
    @Override
    public double obtenerPerimetro() {
        return lado * 4;
    }

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }
}
