package biblioteca.virtual.hn.sistema_biblioteca.model;

import java.io.Serializable;

public class Libro implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private String escritor;
    private String genero;
    private String portada;

    public Libro() {
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
        this.escritor = "";
        this.genero = "";
        this.portada = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}
