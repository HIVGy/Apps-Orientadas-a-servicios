package conPostgres;

import aeropuerto.Aerolinea;
import aeropuerto.Persona;
import java.util.Stack;

public class Validaciones {

    public Stack<String> validar(Aerolinea aerolinea) {
        Stack<String> errores = new Stack<>();

        if (!rfc(aerolinea.getRfc())) {
            errores.add("Formato de RFC incorrecto");
        }
        if (!validarRfc(aerolinea.getRfc(), aerolinea.getNombre(), aerolinea.getCreacion())) {
            errores.add("Los datos ingresados no coinciden con el RFC");
        }
        return errores;
    }

    public Stack<String> validar(Persona persona, String confirma, String fecha, String genero) {
        Stack<String> errores = new Stack<>();
        if (isEmpty(persona.getNombre()) || isEmpty(persona.getApellidos())
                || isEmpty(persona.getContra()) || isEmpty(persona.getCorreo())
                || isEmpty(persona.getCurp()) || isEmpty(fecha) || isEmpty(genero)) {
            errores.add("Debe llenar todos los campos");
        } else {
            if (!largoCurp(persona.getCurp())) {
                errores.add("La CURP debe medir 18 caracteres de largo");
            } else {
                if (!curp(persona.getCurp())) {
                    errores.add("Formato de CURP incorrecto");
                }
                if (!validarCurp(persona.getCurp(), persona, fecha, genero)) {
                    errores.add("Los datos ingresados no coinciden con la CURP");
                }
            }
            if (!confirmarContra(persona.getContra(), confirma)) {
                errores.add("Deben coincidir la contra y confirmar");
            } else {
                if (!correo(persona.getCorreo())) {
                    errores.add("Formato de correo incorrecto");
                }
            }
        }
        return errores;
    }

    private boolean isEmpty(String valor) {
        if (valor.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean confirmarContra(String valor, String confirma) {
        if (!valor.equals(confirma)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean largoCurp(String valor) {
        if (valor.length() != 18) {
            return false;
        } else {
            return true;
        }
    }

    private boolean correo(String valor) {
        /*
         1 ([\\p{L}-\\.]+){1,64} \p{L} es para aceptar letras de cualquier lenguaje
         se busca cualquier letra que puede tener puntos enmedio desde 1 hasta
         64 caracteres
         2 @ busca que la expresión tenga un "@"
         3 ([\\w&&[^_]]+){2,255} la parte del dominio, busca que exista un grupo de
         letras y guines bajos varias veces desde 2 hasta 255 caracteres
         4 . busca que haya un punto despues del punto 3
         5 [a-z]{2,} busca letras minusculas y que existan un minimo de 2 letras
         5 $ busca que sea el fin de la linea
         REGEX conseguido en: https://stackoverflow.com/a/44674038
         */
        String patron = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        if (valor.matches(patron)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean curp(String valor) {
        //Pequeña lista con todas las claves de estado de la curp, se usa en pattern
        String estados = "(AS|BS|CL|CS|DF|GT|HG|MC|MS|NL|PL|QR|SL|TC|TL"
                + "|YN|NE|BC|CC|CM|CH|DG|GR|JC|MN|NT|OC|QT|SP|SR|TS|VZ|ZS)";
        /*
         1 [A-Z]{4} busca cualquier letra mayuscula por 4 caracteres
         2 \d {6} busca cualquier digito por 6 caracteres
         3 H|M {1} busca ya sea H o M
         4 estados{2} usa el string estados para determinar que el estado sea valido
         5 [A-Z]{3} busca cualquier letra mayuscula por 3 caracteres
         6 A|Z|\d{1} busca cualquier digito o las letras A o Z, pues son las unicas homoclaves
         7 \d{1} busca un digito
         */
        String patron = "[A-Z]{4}\\d{6}(H|M){1}" + estados + "{1}[A-Z]{3}(A|Z|\\d){1}\\d{1}";
        if (valor.matches(patron)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean rfc(String valor) {
        String patron = "[A-Z]{3}\\d{6}+([A-Z]|\\d){3}";
        if (valor.matches(patron)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validarRfc(String valor, String nombre, String fechaCreacion) {
        String rfc = "";
        String[] lista = nombre.split(" ");
        String fecha = fechaCreacion.split("-")[0].substring(2, 4)
                + fechaCreacion.split("-")[1] + fechaCreacion.split("-")[2];
        switch (lista.length) {
            case 1:
                rfc += nombre.substring(0, 3);
                System.out.println(rfc);
                break;
            case 2:
                rfc += lista[0].substring(0, 1) + lista[1].substring(0, 2);
            case 3:
                rfc += lista[0].substring(0, 1) + lista[1].substring(0, 1)
                        + lista[2].substring(0, 1);
                break;
        }
        rfc += fecha;
        if (rfc.equals(valor.substring(0, 9))) {
            return true;
        }
        return false;

    }

    private boolean validarCurp(String valor, Persona persona, String fecha, String genero) {
        System.out.println(fecha);
        String curp = "";
        String consonantes = "(B|C|D|F|G|H|J|K|L|M|N|Ñ|P|Q|R|S|T|V|W|X|Y|Z)";
        String vocales = "(A|E|I|O|U)";
        String ape = persona.getApellidos().toUpperCase();
        String usarLuego = "";

        String correctaFecha = fecha.split("-")[0].substring(2, 4)
                + fecha.split("-")[1] + fecha.split("-")[2];
        System.out.println(correctaFecha);
        char[] chars;
        String pVocal = "";
        switch (ape.split(" ").length) {
            case 1:
                chars = ape.toCharArray();
                for (int i = 1; i < chars.length; i++) {
                    if (usarLuego.length() < 1) {
                        if (String.valueOf(chars[i]).matches(consonantes)) {
                            usarLuego += chars[i] + chars[i];
                        }
                    } else if (pVocal.length() < 1) {
                        if (String.valueOf(chars[i]).matches(vocales)) {
                            pVocal += chars[i];
                        }
                    }
                }
                curp += ape.substring(0, 1) + pVocal + ape.substring(0, 1);
                break;
            case 2:
                String[] part = ape.split(" ");
                chars = part[0].toCharArray();
                for (int i = 1; i < chars.length; i++) {
                    if (usarLuego.length() < 1) {
                        System.out.println(String.valueOf(chars[i]));
                        if (String.valueOf(chars[i]).matches(consonantes)) {
                            usarLuego += chars[i];
                        }
                    } else if (pVocal.length() < 1) {
                        if (String.valueOf(chars[i]).matches(vocales)) {
                            pVocal += chars[i];
                        }
                    }
                }
                chars = part[1].toCharArray();
                for (int i = 1; i < chars.length; i++) {
                    if (usarLuego.length() < 2) {
                        if (String.valueOf(chars[i]).matches(consonantes)) {
                            usarLuego += chars[i];
                        }
                    } else if (pVocal.length() < 1) {
                        if (String.valueOf(chars[i]).matches(vocales)) {
                            pVocal += chars[i];
                        }
                    }
                }
                curp += part[0].substring(0, 1) + pVocal + part[1].substring(0, 1);
                break;
        }
        curp += persona.getNombre().substring(0, 1);
        curp += correctaFecha;
        curp += genero.substring(0, 1);
        String estados = "(AS|BS|CL|CS|DF|GT|HG|MC|MS|NL|PL|QR|SL|TC|TL"
                + "|YN|NE|BC|CC|CM|CH|DG|GR|JC|MN|NT|OC|QT|SP|SR|TS|VZ|ZS)";
        if (valor.substring(11, 13).matches(estados)) {
            curp += valor.substring(11, 13);
        }
        curp += usarLuego;
        usarLuego = "";
        chars = persona.getNombre().toUpperCase().toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (usarLuego.length() < 1) {
                if (String.valueOf(chars[i]).matches(consonantes)) {
                    usarLuego += chars[i];
                }
            }
        }
        curp += usarLuego;
        System.out.println(curp);
        System.out.println(valor.substring(0, 16));
        if (curp.equals(valor.substring(0, 16))) {
            return true;
        } else {
            return false;
        }
    }
}
