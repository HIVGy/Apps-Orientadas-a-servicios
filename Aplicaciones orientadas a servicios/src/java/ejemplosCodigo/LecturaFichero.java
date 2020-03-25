package ejemplosCodigo;

//Se importa File para crear un archivo que Java pueda leer
//Se importa Scanner para leer el archivo del disco duro
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Leer archivos</h1>
 * Selecciona un documento e imprime sus contenidos en consola.
 * 
 * @author Vazquez Gutierrez Hugo Isaac
 * 
 * @version 1.0
 */
public class LecturaFichero {

    /**
     * Crea un nuevo archivo de tipo File al que pueda acceder Java y despues lo
     * lee usando un Scanner.
     * @param args Argumentos de main
     * @see File
     * @see Scanner
     */
    public static void main(String args[]) {
        String Ruta = "G:\\School\\Apps orientadas a servicios\\VazquezGutierrezHugoIsaac8.2\\src\\java\\ejemplosCodigo\\miArchivo.txt";
            //El código actualmente solo funciona con ruta absoluta
            File archivo = new File(Ruta);
            Scanner leer;
        try {
            leer = new Scanner(archivo);
            //Mientras haya más archivo, imprime archivo
            while (leer.hasNext()) {
                System.out.println(leer.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
