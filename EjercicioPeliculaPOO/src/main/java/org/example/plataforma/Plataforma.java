package org.example.plataforma;

import org.example.exception.PeliculaExistenteExcepcion;
import org.example.pelicula.Genero;
import org.example.pelicula.Pelicula;
import org.example.pelicula.ResumenContenido;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plataforma {
    //atributo
    private String nombre;
    private List<Pelicula> contenido;
    //añadiendo un nuevo maoa
    private Map<Pelicula, Integer> visualizaciones;

    //pelicula, visualizaciones
    //<Pelicula.titulo, >

    //constructores

    //cuando creemos una isntancia de plataforma, tenemos que pasarle un nombre y el listado de peliculas
    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>(); //el contenido es un array vacio para que yo incluya la pelicula
    }

    //metodos

    /**
     * agregar a una lista de la pelicula al this. contenido que le voy asignar un valor
    *  @param  pelicula me ayuda asignarle un elemento al contenido, o sea se hara una lista*/
    public void agregar(Pelicula pelicula){

        //buscamos la pelicula
        Pelicula contenido =this.buscarPorTitulo(pelicula.getTitulo());

        if (contenido != null){
            //trow se lanza una expecion
            //funciona tambien como un return, nos saca del metodo, ya no lee
            throw new PeliculaExistenteExcepcion(pelicula.getTitulo());
        }


        //add me sirve agregar un elemento al contenido
        this.contenido.add(pelicula); //ya no lee esto el trow

    }

    //polimorfismo
    // para la reproduccion depeliculas
    public void  reproducir(Pelicula contenido){
        //getdefault para saber como esta en el momento
        //es 0 porque si no tiene nada es que nunca lo han visto
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + "ha sido reproducido"+ conteoActual + " veces");

        //manejo de ingreso
        visualizaciones.put(contenido, conteoActual+1);

        contenido.reproducir();
    }



    /**eliminar: nos ayuda a eliminar peliculas del listado**/
    public void eliminar(Pelicula pelicula){
        this.contenido.remove(pelicula);
    }

    /**
     * mostrarTitulos nos ayuda a mostrar el array y recorre para mostrar cada uno**/
    public List<String> getTitulos(){
        return contenido.stream().map(Pelicula::getTitulo) //clase pelicula obtengo todos los titutlo y lopaso a una lista
                .toList();

        /*public void mostrarTitulos(){
        for (Pelicula pelicula: contenido){ //obtengo el titulo por el get y no directamente por el atributo
            System.out.println(pelicula.getTitulo());

            //for each
         this.contenido.forEach((Pelicula contenido)
                -> System.out.println(contenido.getTitulo()));
        }


      }*/
    }

    /**getResumenes obtiene algunos parametros indicados en la clase record ResumenContenido
     * usamos la clase Resumen de contenid
     * hace una copia en la nueva isntancia del contenido**/
    public List<ResumenContenido> getResumenes(){
        return contenido.stream().map(contenido-> new ResumenContenido(
                contenido.getTitulo(),
                        contenido.getDuracion(),
                        contenido.getGenero()))
                .toList();
    }

    /**
     * buscarPorTitulo compracion del titulo que ya tengo con el que ya se tiene como referencia ,
     * retorna el titulo o no**/
    public Pelicula buscarPorTitulo (String titulo){
       /* for (Pelicula pelicula: contenido){ //obtengo el titulo por el get y no directamente por el atributo
            //busqueda
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)){ //si esto es verdadero me regresa la pelicula
            // System.out.println(pelicula.getTitulo());
                return pelicula;
            }
        }
        return null; //retorna un vacio
        */

        //se retorna el contenid
        //stream -> porque el contenido es una lista, ahora se trabaja como un flujo
        return contenido.stream().filter((Pelicula contenido)
                        -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);


    }

    /** buscarPorGenero nueva funcion para buscar peliculas por genero
     * devuelve un listado de peliculas
     * @param genero nos ayudara a comparar los tipos de genero**/
    public List<Pelicula> buscarPorGenero(Genero genero){

        return contenido.stream().filter((Pelicula contenido)
            -> contenido.getGenero().equals(genero))
                .toList(); //regesa un listado
    }

    /** buscarIdioma nueva funcion para buscar peliculas por idiomas
     * devuelve un listado de peliculas
     * @param idioma nos ayudara a comparar los tipos de idiomas**/
    public List<Pelicula> buscarIdioma(Idioma idioma){

        return contenido.stream().filter((Pelicula contenido)
        -> contenido.getIdioma().equals(contenido))
                .toList(); //regesa el listado de los idiomas
    }

    /** buscarIdioma nueva funcion para buscar peliculas por calidad
     * devuelve un listado de peliculas
     * @param calidad nos ayudara a comparar los tipos de calidad exitences**/
    public List<Pelicula> buscarCalidad(Calidad calidad){

        return contenido.stream().filter((Pelicula contenido)
                        -> contenido.getCalidad().equals(contenido))
                .toList(); //regesa el listado de los idiomas
    }


    /**obtengo la duracion de todos los titulos**/
    public  int getDuracion(){
        //metodo de referencia
        //.sum() = suma todas las duraciones de las peliulas
        return contenido.stream().mapToInt(Pelicula::getDuracion).sum();
    }

    /**ranking de pelicula**/
    public List<Pelicula> getPopulares(){
        //reverse necesitamos de la mas baja a la mas alta
        return contenido.stream().sorted(
                Comparator.comparingDouble(Pelicula::getCalificacion)
                        .reversed()).
                toList();
    }

    /*public void mostrarTitulos2(){
        for (int i = 0; i < contenido.size(); i++) {
            Pelicula pelicula = contenido.get(i); //me va recoriiendo y obteniendo los titulos
            System.out.println(pelicula.getTitulo());
        }
    }*/

    /*Crea dos metodos que encuentre la película más larga y
        más corta aplicando funciones de transformación y
        reducción*/

    /**mostrarPeliculaCorta compara todas las peliculas con el metodo .max y los
     * Comparator.comparing
     * **/

    public  Pelicula peliculaLarga(){
        //basicamente lo compara conla funcion getDurancion y si no me devuelve vacio
        return contenido.stream().max(Comparator.comparing(Pelicula::getDuracion)).orElse(null);
    }

    /**PeliculaCorta compara todas las peliculas con el metodo .min y los
     * Comparator.comparing
     * **/

    public  Pelicula peliculaCorta(){
        //basicamente lo compara conla funcion getDurancion y si no me devuelve vacio
        return contenido.stream().min(Comparator.comparing(Pelicula::getDuracion)).orElse(null);
    }

    //Filtra las películas con calificación igual o superior a cuatro
    //usando streams y lambdas para obtener solo las más
    //populares
    /**peliculaPopular compara el listado de peliculas para saber cuales son mayores a la
     * calificacion de 4, en esto se utiliza filter que  nos ayuda a filtrar todo el contenido
     * y recolectar la lista en la funcion esPopular de la clase Pelicula**/
    public List<Pelicula> peliculaPopular(){
        return contenido.stream().filter((Pelicula contenido)
        -> contenido.esPopular()).collect(Collectors.toList());
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

    public void setContenido(List<Pelicula> contenido) {
        this.contenido = contenido;
    }
}
