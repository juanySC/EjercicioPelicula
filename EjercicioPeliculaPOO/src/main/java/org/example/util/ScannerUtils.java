package org.example.util;

import org.example.pelicula.Genero;
import org.example.plataforma.Calidad;
import org.example.plataforma.Idioma;

import java.util.Scanner;

/*Ahorrar lineas de codigo para ingresas titutlo, genero, duracion*/
public class ScannerUtils {

    //para preguntar datos
    public static final Scanner scanner = new Scanner(System.in);

    /**capturarTexto nos ayudam a recuperar la informacion dada
     *@param mensaje me devuelve el contenido de este **/
    public static String capturarTexto (String mensaje){
        System.out.println(mensaje + ":");
        return scanner.nextLine();
    }

    /**capturarNumero nos ayudam a capturar solo los numeros enteros
     *@param mensaje me devuelve el contenido de este **/
    public static int  capturarNumero (String mensaje){
        System.out.println(mensaje + ":");
        int dato = scanner.nextInt();
        scanner.nextLine();
        return dato;
    }

    /**capturarDecimal nos ayudam a recuperar informacion de los numeros con decimales
     *@param mensaje me devuelve el contenido de este **/
    public static double capturarDecimal (String mensaje){
        System.out.println(mensaje + ":");
        double dato = scanner.nextDouble();
        scanner.nextLine();
        return dato;
    }

    /**capturarGenero con ayuda de la funcion capturarTexto obtendremos el genero**/
    public static Genero capturarGenero(String mensaje){
        while (true){
            System.out.println(mensaje+"... opciones: ");
            //recorro los vvalores de genero
            for (Genero genero : Genero.values()){
                //imprimo los valores de los que estoy recorriendo
                System.out.println("-" + genero.name());
            }

            System.out.println("\n¿Cuál quieres?");

            //usamos la funcion capturar texto
            String entrada = capturarTexto(mensaje);

            //manejando el error
            try {
                //utilizp toUpperCase porque el enum es todo en mayuscula
                return Genero.valueOf(entrada.toUpperCase());
                //el tipo de dato no se encuentra
            }catch (IllegalArgumentException e){
                System.out.println("\nGenero no encontrado" +mensaje+ ":");
            }
        }
    }

    // capturo el texto del idioma
    public static Idioma capturarIdioma(String mensaje){
        while (true){
            System.out.println("...opciones: ");

            //recorro los valores del idioma
            for (Idioma idioma : Idioma.values()){
                System.out.println("-. "+ idioma.name());
            }

            System.out.println("\n¿Cual deseas?");

            //uso la funcion capturarTexto
            String entrada = capturarTexto(mensaje);

            try {
                //para que nuevamente me lea las mayusculas
                return Idioma.valueOf(entrada.toUpperCase());
            } catch (IllegalArgumentException e){
                System.out.println("\nIdioma no encontrado" +mensaje+ ":");
            }

        }
    }

    //capturo al texto de la calidad
    public static Calidad capturarCalidad(String mensaje){
        while (true){
            System.out.println("...opciones: ");

            //recorro los valores del idioma
            for (Calidad calidad : Calidad.values()){
                System.out.println("-. "+ calidad.name());
            }

            System.out.println("\n¿Cual calidad deseas?");

            //uso la funcion capturarTexto
            String entrada = capturarTexto(mensaje);

            try {
                //para que nuevamente me lea las mayusculas
                return Calidad.valueOf(entrada.toUpperCase());
            } catch (IllegalArgumentException e){
                System.out.println("\nCalidad no encontrado" +mensaje+ ":");
            }

        }
    }
}
