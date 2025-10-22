package org.example.pelicula;

import org.example.plataforma.Calidad;
import org.example.plataforma.Idioma;

import java.time.LocalDate;

public class Documental extends Contenido{
    //atributo
    private String narrador;

    //super constructor de la clase padre
    //se va a modiiaqr para adaptarlo al nuevo atributo narrador
    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador = narrador;
    }

    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }

    //getters y setters
    public String getNarrador() {
        return narrador;
    }
}
