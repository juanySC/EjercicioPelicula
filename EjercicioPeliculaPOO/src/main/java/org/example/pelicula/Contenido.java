package org.example.pelicula;

import org.example.plataforma.Calidad;
import org.example.plataforma.Idioma;

import java.time.LocalDate;

//NO primitivos se comportan como objetos
public class Contenido {
    //los atributos solo se pueden modificar por medio de metodos
    private String titulo;
    private String descripcion;
    //para obtener con la nueva duncion
    private int duracion; // cuanto dura en minutos
    private Genero genero;
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponible;
    private Idioma idioma;
    private Calidad calidad;

    // metodo y no retorna nada
    public Contenido(String titulo, int duracion, Genero genero, double calificacion, Idioma idioma, Calidad calidad){
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.calificacion = calificacion;
        this.fechaEstreno = LocalDate.now();
        this.disponible = false;
        this.idioma =idioma;
        this.calidad = calidad;

    }

    //lo usare para mi clase docuemtal
    public Contenido(String titulo, int duracion, Genero genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;

    }

    public Contenido(String titulo, int duracion, Genero genero, double calificacion) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        //agrego la fecha
        this.fechaEstreno = LocalDate.now();
        this.calificacion = calificacion;

        //this.disponible = disponible;
    }

    //para el uso del nuevo txt
    public Contenido(String titulo, int duracion, Genero genero, double calificacion, LocalDate fechaEstreno) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.calificacion = calificacion;
        this.fechaEstreno = fechaEstreno;
    }

    //creacion de metodos

    /**reproducir: imprime en la terminal el titulo**/
    public void reproducir(){
        System.out.println("Reproduciendo : "+this.titulo);
    }

    /**obtenerFichaTecnica: ayuda a obtener el titulo, fecha de estreno, genero y calificacion**/
    public String obtenerFichaTecnica(){
        return this.titulo + "\n(" + this.fechaEstreno + ")" +
                "\ngenero: "+this.genero + "\nCalificación: "+ this.calificacion;
    }

    /**calificar: comparo un parametro para ver si cumple un rango, si es asi si le asigna la calificacion
     * al de la estructura se le asigan el valor**/
    public void calificar(double calificacion){
        if((calificacion >= 0) && (calificacion <= 5)){
            //le asigna la calificacion al objeto solo si esta entre 0 a 5
            this.calificacion = calificacion;
        }
    }

    //getters and setters
    public boolean esPopular(){
        //solo si la calificacion es mayor a 4 entonces es true, de caso contrario no
        return this.calificacion >= 4;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Genero getGenero() {
        return genero;
    }


    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isDisponible() { //se cumple o no se cumple?
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Calidad getCalidad() {
        return calidad;
    }

    public void setCalidad(Calidad calidad) {
        this.calidad = calidad;
    }
}
