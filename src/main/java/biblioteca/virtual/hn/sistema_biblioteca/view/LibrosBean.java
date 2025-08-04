package biblioteca.virtual.hn.sistema_biblioteca.view;

import biblioteca.virtual.hn.sistema_biblioteca.controller.LibrosInteractor;
import biblioteca.virtual.hn.sistema_biblioteca.controller.LibrosInteractorImpl;
import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.model.ResponsiveOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("LibrosBean")
@ViewScoped
public class LibrosBean implements Serializable, LibrosViewModel {
    private List<Libro> libros;
    private List<Libro> selectedLibros;
    private Libro selectedLibro;
    private LibrosInteractor controller;
    private List<ResponsiveOption> responsiveOptions;

    public LibrosBean(){
        this.libros = new ArrayList<>();
        this.selectedLibros = new ArrayList<>();
        this.selectedLibro = null;
        controller = new LibrosInteractorImpl(this);
        controller.consultarLibros();
    }



    public void openNew() {
        this.selectedLibro = new Libro();
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedLibro()) {
            int size = this.selectedLibros.size();
            return size > 1 ? size + " libros seleccionados" : "1 libro seleccionado";
        }
        return "Eliminar";
    }


    public boolean hasSelectedLibro() {
        return this.selectedLibros != null && !this.selectedLibros.isEmpty();
    }

    public void guardarLibro() {
        if(this.selectedLibro.getId() == 0){
            controller.crearLibro(this.selectedLibro);
        }else{
            controller.actualizarLibro(this.selectedLibro);
        }
    }



    public void eliminarLibro() {
        this.libros.remove(this.selectedLibro);
        this.controller.eliminarLibro(this.selectedLibro.getId());
        this.selectedLibro = null;
    }

    public void eliminarLibros() {
        this.libros.removeAll(this.selectedLibros);
        this.controller.eliminarLibros(this.selectedLibros.stream().mapToInt(Libro::getId).toArray());
        this.selectedLibros = null;
    }

    private void mostrarMensaje(String mensaje, FacesMessage.Severity tipo) {
        FacesMessage message = new FacesMessage(tipo, mensaje, null);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }



    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Libro getSelectedLibro() {
        return selectedLibro;
    }

    public void setSelectedLibro(Libro selectedLibro) {
        this.selectedLibro = selectedLibro;
    }

    public List<Libro> getSelectedLibros() {
        return selectedLibros;
    }

    public void setSelectedLibros(List<Libro> selectedLibros) {
        this.selectedLibros = selectedLibros;
    }


    @Override
    public void mostrarLibrosDataTable(List<Libro> libros) {
    this.libros = libros;
    }

    @Override
    public void refrescarPantalla() {
        controller.consultarLibros();
    }

    @Override
    public void mostrarMensajeExito(String mensaje) {
        mostrarMensaje(mensaje, FacesMessage.SEVERITY_INFO);
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        mostrarMensaje(mensaje, FacesMessage.SEVERITY_ERROR);
    }


















    //Carrusel de Catalogo de Libros


    // Contadores por categoría
    private int cantidadClasicos;
    private int cantidadSatira;
    private int cantidadCienciaFiccion;
    private int cantidadNovela;
    private int cantidadFantasia;
    private int cantidadRealismoMagico;
    private int cantidadFiccion;
    private int cantidadTerror;
    private int cantidadEpica;
    private int cantidadFilosofia;
    private int cantidadAventura;

    // Métodos por categoría
    public List<Libro> getLibrosClasico() {
        List<Libro> clasicos = libros.stream()
                .filter(libro -> "Clásico".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadClasicos = clasicos.size();
        return clasicos;
    }

    public int getCantidadClasicos() {
        return cantidadClasicos;
    }

    public List<Libro> getLibrosSatira() {
        List<Libro> satira = libros.stream()
                .filter(libro -> "Sátira".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadSatira = satira.size();
        return satira;
    }

    public int getCantidadSatira() {
        return cantidadSatira;
    }

    public List<Libro> getLibrosCienciaFiccion() {
        List<Libro> cienciaFiccion = libros.stream()
                .filter(libro -> "Ciencia Ficción".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadCienciaFiccion = cienciaFiccion.size();
        return cienciaFiccion;
    }

    public int getCantidadCienciaFiccion() {
        return cantidadCienciaFiccion;
    }

    public List<Libro> getLibrosNovela() {
        List<Libro> novela = libros.stream()
                .filter(libro -> "Novela".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadNovela = novela.size();
        return novela;
    }

    public int getCantidadNovela() {
        return cantidadNovela;
    }

    public List<Libro> getLibrosFantasia() {
        List<Libro> fantasia = libros.stream()
                .filter(libro -> "Fantasía".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadFantasia = fantasia.size();
        return fantasia;
    }

    public int getCantidadFantasia() {
        return cantidadFantasia;
    }

    public List<Libro> getLibrosRealismoMagico() {
        List<Libro> realismoMagico = libros.stream()
                .filter(libro -> "Realismo mágico".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadRealismoMagico = realismoMagico.size();
        return realismoMagico;
    }

    public int getCantidadRealismoMagico() {
        return cantidadRealismoMagico;
    }

    public List<Libro> getLibrosFiccion() {
        List<Libro> ficcion = libros.stream()
                .filter(libro -> "Ficción".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadFiccion = ficcion.size();
        return ficcion;
    }

    public int getCantidadFiccion() {
        return cantidadFiccion;
    }

    public List<Libro> getLibrosTerror() {
        List<Libro> terror = libros.stream()
                .filter(libro -> "Terror".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadTerror = terror.size();
        return terror;
    }

    public int getCantidadTerror() {
        return cantidadTerror;
    }

    public List<Libro> getLibrosEpica() {
        List<Libro> epica = libros.stream()
                .filter(libro -> "Épica".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadEpica = epica.size();
        return epica;
    }

    public int getCantidadEpica() {
        return cantidadEpica;
    }

    public List<Libro> getLibrosFilosofia() {
        List<Libro> filosofia = libros.stream()
                .filter(libro -> "Filosofía".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadFilosofia = filosofia.size();
        return filosofia;
    }

    public int getCantidadFilosofia() {
        return cantidadFilosofia;
    }

    public List<Libro> getLibrosAventura() {
        List<Libro> aventura = libros.stream()
                .filter(libro -> "Aventura".equals(libro.getGenero()))
                .collect(Collectors.toList());
        cantidadAventura = aventura.size();
        return aventura;
    }

    public int getCantidadAventura() {
        return cantidadAventura;
    }

}
