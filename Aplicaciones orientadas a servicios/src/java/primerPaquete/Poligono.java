package primerPaquete;

import static java.lang.Math.tan;

/**
* <h1>Figura con más de 4 lados</h1>
* Representa una figura de 5 o más lados.
* <p>
* Los poligonos pueden ser calculados usando la misma formula y eso pretende
* esta abstracción
* <p>
* <b>Nota:</b> Solo aplica para poligonos regulares. Los poligonos irregulares
* cuentan con otra formula
*
* @author  Vazquez Gutierrez Hugo Isaac
* 
* @version 1.0
*/
public class Poligono extends Figura {

    //Todo poligono regular tiene 360 grados internamente
    private static final int GRADOS = 360;
    private double lado, numLados;

    /**
     * Constructor vacio
     */
    public Poligono() {
    }

    /**
     * Constructor que inicializa el numero de lados y la medida de estos
     * @param numLados Lados del poligono
     * @param lado Medida del lado en centimetros
     */
    public Poligono(double numLados, double lado) {
        this.numLados = numLados;
        this.lado = lado;
    }

    /**
     * Obtiene el área de poligonos con lados mayores a 4.
     * Utiliza el Apotema y el perimetro para calcular el área
     * Sobreescribe el metodo obtenerArea de {@link Figura}
     * 
     * @return El área del poligono en centimetros cuadrados
     * @see Figura
     */
    @Override
    public double obtenerArea() {
        double apotema = obtenerApotema();
        double perimetro = obtenerPerimetro();
        return perimetro * apotema / 2;
    }

    /**
     * Multiplica el número de lados por su medida en centimetros
     * @return Perimetro de la figura en centimetros
     */
    @Override
    public double obtenerPerimetro() {
        return numLados * lado;
    }

    /**
     * Calcula el apotema de la figura.
     * <p>
     * El apotema es la distancia minima entre el centro de un poligono regular
     * y cualquiera de sus lados utilizando el teorema de pitagoras.
     * 
     * @return Apotema del poligono en centimetros
     */
    public double obtenerApotema() {
        double angulo = GRADOS / numLados;
        double apotema = lado / (2 * tan(angulo / 2));
        return apotema;
    }

}
