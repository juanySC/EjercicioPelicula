package org.example.utilidades;
import org.example.pelicula.Pelicula;

import java.time.LocalDateTime;

public class Usuario {
    //atributos
    public String nombre;
    public String email;
    public LocalDateTime fechaResgistro;

    //metodo
    /**ver: visualizacion del nombre del usuario y a la vez llamo a la clase Pelicula**/
    public void ver(Pelicula pelicula){
        System.out.println(this.nombre + "Esta viendo...");
        pelicula.reproducir();
    }
}
