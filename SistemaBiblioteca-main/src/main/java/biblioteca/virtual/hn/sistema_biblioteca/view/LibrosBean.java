package biblioteca.virtual.hn.sistema_biblioteca.view;

import biblioteca.virtual.hn.sistema_biblioteca.controller.LibrosInteractor;
import biblioteca.virtual.hn.sistema_biblioteca.controller.LibrosInteractorImpl;
import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("LibrosBean")
@ViewScoped
public class LibrosBean implements Serializable, LibrosViewModel {
    private List<Libro> libros;
    private List<Libro> selectedLibros;
    private Libro selectedLibro;
    private LibrosInteractor controller;

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

}
