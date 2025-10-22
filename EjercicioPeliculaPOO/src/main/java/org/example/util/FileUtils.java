package org.example.util;

import org.example.pelicula.Documental;
import org.example.pelicula.Genero;
import org.example.pelicula.Contenido;
import org.example.pelicula.Pelicula;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    /**metodo que permite escribir un archivo**/
    public static void escribirContenido(Contenido contenido){
        //String linea = contenido. getTitulo() + "|" + contenido.getDuracion() + "|" + contenido.getGenero() + "|" +
          //      contenido.getCalificacion() + "|" + contenido.getFechaEstreno() + "\n";

    //metodo join que arma toda mi linea, o sea todo lo que ira en la inormacion de esa linea
        String linea = String.join("|",
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                contenido.getGenero().toString(),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString()
                ) + "\n";

        //creando linea final
        String lineaFinal;

        //polimorfismo
        //instanceof me dice que tipo d ela clase es: pelicula o documental  del contenido
        if (contenido instanceof Documental){
            //casteando, se crea una variable documental que es igual al contenido casteado
            //como no s epuede igual docuemnal con contenido directamente, es por eso que se castea
            Documental documental = (Documental) contenido;
            //liena normal ocn el que estamos trabajando desde un inicio
            lineaFinal = "DOCUMENTAL" + "|" + linea + "|"+ documental.getNarrador();
        } else{
            lineaFinal = "PELICULA" + "|" + linea;
        }

        //me ayuda a imprimir linea por linea para mi informacion
        System.out.println(linea + "pelicula escrita en el archivo");

        try {
            Files.writeString(Paths.get("contenido.txt"),
                    linea + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**leerContenido nos ayuda a crear una lista sobre lo que teemos en el archivo**/
    public static List<Contenido> leerContenido(){

        //array para guardar las peliculas
        List<Contenido> contenidoDesdeArchivo = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("contenido.txt"));


            lines.forEach(linea ->{
                String[] datos = linea.split("\\|");

                //tengo que ripo de contenido traigo
                String tipoContenido = datos[0];

                //pregunto
                if (("PELICULA".equals(tipoContenido) && datos.length == 6 ) || ("DOCUMENTAL".equals(tipoContenido) && datos.length ==7)){

                    String titulo = datos[1];
                    int duracion = Integer.parseInt(datos[2]);
                    Genero genero = Genero.valueOf(datos[3].toUpperCase());
                    //usamos isBlank porque solo esta en blanco y no es que se anulo
                    double calificacion = datos[4].isBlank() ? 0: Double.parseDouble(datos[3]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[5]);

                    //creamos un documental o pelicula
                    //Contenido pelicula = new Contenido(titulo, duracion, genero, calificacion,fechaEstreno);

                    Contenido contenido;

                    if ("PELICULA".equals(tipoContenido)){
                        contenido = new Pelicula(titulo, duracion, genero, calificacion);
                    } else {
                        String narrador = datos[6];
                        contenido= new Documental(titulo, duracion, genero, calificacion, narrador);
                    }

                    contenido.setFechaEstreno(fechaEstreno);
                    //agregamos la plataforma
                    contenidoDesdeArchivo.add(contenido);


                }

            });

            //lines.forEach(linea -> System.out.println(linea));

        }catch (
                IOException e){
            System.out.println("Error leyendo el archivo " + e.getMessage());
        }

        return contenidoDesdeArchivo;
    }

}
