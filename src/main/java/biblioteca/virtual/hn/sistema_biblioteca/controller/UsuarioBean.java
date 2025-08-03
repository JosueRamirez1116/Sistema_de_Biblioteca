package biblioteca.virtual.hn.sistema_biblioteca.controller;



import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("UsuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private List<Libro> librosDisponibles;
    private List<Libro> librosPrestados;

    @PostConstruct
    public void init() {
        librosDisponibles = new ArrayList<>();
        librosPrestados = new ArrayList<>();

        // Aquí cargas los libros disponibles desde la base o mock
        // Por ejemplo:
        // librosDisponibles = libroRepository.obtenerLibrosDisponibles();
    }

    public void prestarLibro(Libro libro) {
        if (libro != null && librosDisponibles.contains(libro)) {
            librosDisponibles.remove(libro);
            librosPrestados.add(libro);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro prestado",
                            "Has prestado el libro: " + libro.getNombre_libro()));
        }
    }

    public void devolverLibro(Libro libro) {
        if (libro != null && librosPrestados.contains(libro)) {
            librosPrestados.remove(libro);
            librosDisponibles.add(libro);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro devuelto",
                            "Has devuelto el libro: " + libro.getNombre_libro()));
        }
    }

    // Getters y Setters

    public List<Libro> getLibrosDisponibles() {
        return librosDisponibles;
    }

    public void setLibrosDisponibles(List<Libro> librosDisponibles) {
        this.librosDisponibles = librosDisponibles;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(List<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }
}
