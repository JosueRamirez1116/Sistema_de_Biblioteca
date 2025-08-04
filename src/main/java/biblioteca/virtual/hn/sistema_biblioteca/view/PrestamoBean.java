package biblioteca.virtual.hn.sistema_biblioteca.view;

import biblioteca.virtual.hn.sistema_biblioteca.controller.LibrosInteractor;
import biblioteca.virtual.hn.sistema_biblioteca.controller.LibrosInteractorImpl;
import biblioteca.virtual.hn.sistema_biblioteca.controller.PrestamoInteractor;
import biblioteca.virtual.hn.sistema_biblioteca.controller.PrestamoInteractorImpl;
import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import biblioteca.virtual.hn.sistema_biblioteca.model.Prestamo;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.model.ResponsiveOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("PrestamoBean")
@SessionScoped
public class PrestamoBean implements Serializable, PrestamoViewModel
{
    private List<Prestamo> prestamo;
    private Prestamo selectedPrestamo;
    private PrestamoInteractor controller;
    private List<ResponsiveOption> responsiveOptions;

    public PrestamoBean(){
        this.prestamo = new ArrayList<>();
        this.selectedPrestamo= null;
        controller = new PrestamoInteractorImpl(this);
        controller.consultarPrestamo();
        this.responsiveOptions = new ArrayList<>();
    }
    public void openNew() {
        this.selectedPrestamo= new Prestamo();
    }
    public void guardarPrestamo() {
        if(this.selectedPrestamo.getId_prestamo() == 0){
            controller.crearPrestamo(this.selectedPrestamo);
        }else{
            controller.actualizarPrestamo(this.selectedPrestamo);
        }
    }
    public void mostrarMensaje(String mensaje, FacesMessage.Severity tipo) {
        FacesMessage message = new FacesMessage(tipo, mensaje, null);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
    public List<Prestamo> getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(List<Prestamo> prestamo) {
        this.prestamo = prestamo;
    }

    public Prestamo getSelectedPrestamo()
    {
        return selectedPrestamo;
    }
    public void setSelectedPrestamo(Prestamo selectedPrestamo) {
        this.selectedPrestamo = selectedPrestamo;
    }

    public List<ResponsiveOption> getResponsiveOptions() { // Añadido getter
        return responsiveOptions;
    }

    public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) { // Añadido setter
        this.responsiveOptions = responsiveOptions;
    }
    @Override
    public void mostrarPrestamoDataTable(List<Prestamo> prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public void refrescarPantalla() {
        controller.consultarPrestamo();
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
