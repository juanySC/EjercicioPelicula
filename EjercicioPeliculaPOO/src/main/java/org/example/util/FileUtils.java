package org.example.util;

import org.example.pelicula.Genero;
import org.example.pelicula.Pelicula;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<Pelicula> leerContenido(){

        //array para guardar las peliculas
        List<Pelicula> contenidoDesdeArchivo = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("contenido.txt"));


            lines.forEach(linea ->{
                String[] datos = linea.split("\\|");
                //pregunto
                if (datos.length == 5){

                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    Genero genero = Genero.valueOf(datos[2].toUpperCase());
                    //usamos isBlank porque solo esta en blanco y no es que se anulo
                    double calificacion = datos[3].isBlank() ? 0: Double.parseDouble(datos[3]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[4]);

                    //creamos la pelicula
                    Pelicula pelicula = new Pelicula(titulo, duracion, genero, calificacion,fechaEstreno);

                    //agregamos la plataforma
                    contenidoDesdeArchivo.add(pelicula);


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
