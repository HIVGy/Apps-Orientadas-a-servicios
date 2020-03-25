package primerPaquete;

/**
 * <h1>Lados de un circulo</h1>
 * Representa la abstracción de un circulo.
 * Objeto que muestra las características principales del rectangulo: base y altura.
 * <p>Hereda de la clase figura
 * 
* @author Vazquez Gutierrez Hugo Isaac
 * 
* @version 1.0
 */
public class Rectangulo extends Figura {

    private double base, altura;

    /**
     * Constructor vacio
     */
    public Rectangulo(){
        
    }
    
    /**
     * Constructor de la clase que inicializa los lados.
     * @param base Una de las medidas del rectangulo. En centimetros
     * @param altura Medida en centimetros de la altura del rectangulo.
     */
    public Rectangulo(double base, double altura){
        this.altura = altura;
        this.base = base;
    }
    
    
    /**
     * Obtiene el área del rectangulo.
     * Utiliza la formula de base * altura para determinar el área
     * Sobreescribe el metodo obtenerArea de {@link Figura}
     * 
     * @return El área del rectangulo en centimetros cuadrados
     * @see Figura
     */
    @Override
    public double obtenerArea() {
        return base * altura;
    }
    
    /**
     * Obtiene el perimetro del cuadrado.
     * Suma 2 bases y 2 alturas para obtener el perimetro.
     * 
     * @return El perimetro del circulo en centimetros
     * @see Figura
     */
    @Override
    public double obtenerPerimetro() {
        return 2 * base + 2 * altura;
    }

}
