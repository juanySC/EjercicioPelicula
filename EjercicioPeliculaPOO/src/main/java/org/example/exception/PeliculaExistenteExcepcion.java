package org.example.exception;
//clase padre para poder manejar las demas clases

public class PeliculaExistenteExcepcion extends  RuntimeException{

    public PeliculaExistenteExcepcion(String titulo){
       //hace referencia a la super clase: runtimeexception
        super("La pelicula" + titulo + " YA EXISTE");
    }
}
