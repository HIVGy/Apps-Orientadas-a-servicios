package primerPaquete;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

/**
* <h1>Circulos</h1>
* Representa la abstracción de un circulo a través de sus propiedades
* Hereda de la clase figura
*
* @author  Vazquez Gutierrez Hugo Isaac
* 
* @version 1.0
*/
public class Circulo extends Figura {

    private double radio;

    /**
     * Constructor vacio
     */
    public Circulo() {
    }

    /**
     * Constructor de clase que inicializa el radio
     * @param radio Medida en centimetros del radio del circulo
     */
    public Circulo(double radio) {
        this.radio = radio;
    }

    /**
     * Obtiene el área del circulo.
     * Utiliza la formula de calculo de área del circulo (Pi * radio ^ 2)
     * para determinar el área del circulo
     * Sobreescribe el metodo obtenerArea de {@link Figura}
     * 
     * @return El área del cuadrado en centimetros cuadrados
     * @see Figura
     */
    @Override
    public double obtenerArea() {
        return PI * pow(radio, 2);
    }

    /**
     * Obtiene el área del circulo.
     * Utiliza la formula de calculo de perimetro del circulo (2 * Pi * radio)
     * para determinar el perimetro del circulo
     * Sobreescribe el metodo obtenerPerimetro
     * 
     * @return El área del cuadrado en centimetros cuadrados
     * @see Figura
     */
    @Override
    public double obtenerPerimetro() {
        return 2 * PI * radio;
    }
}
