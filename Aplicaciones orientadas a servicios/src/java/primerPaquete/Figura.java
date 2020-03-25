package primerPaquete;

//
/**
* <h1>Clases abstractas</h1>
* Demuestra el funcionamiento de las clases abstractas.
* <p>
* Abstracción de una figura geometrica en general para poder heredarles a otras
* figuras geometricas ya especificadas.
* Hereda de la clase figura
*
* @author  Vazquez Gutierrez Hugo Isaac
* 
* @version 1.0
*/
public abstract class Figura {

    /**
     * Determina el área de una figura
     * <p>
     * <b>Nota</b>: Necesita ser implementado en la clase que herede
     * 
     * @return Valor numérico del área en centimetros
     */
    public abstract double obtenerArea();

    /**
     * Determina el perimetro de una figura
     * <p>
     * <b>Nota</b>: Necesita ser implementado en la clase que herede
     *
     * @return Valor numérico del perimetro en centimetros
     */
    public abstract double obtenerPerimetro();
}
