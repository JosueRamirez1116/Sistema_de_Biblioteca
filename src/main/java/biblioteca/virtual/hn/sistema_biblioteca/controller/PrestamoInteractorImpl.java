package biblioteca.virtual.hn.sistema_biblioteca.controller;

import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import biblioteca.virtual.hn.sistema_biblioteca.model.LibrosResponse;
import biblioteca.virtual.hn.sistema_biblioteca.model.Prestamo;
import biblioteca.virtual.hn.sistema_biblioteca.model.PrestamoResponse;
import biblioteca.virtual.hn.sistema_biblioteca.repository.DatabaseRepositoryImpl;
import biblioteca.virtual.hn.sistema_biblioteca.view.LibrosViewModel;
import biblioteca.virtual.hn.sistema_biblioteca.view.PrestamoBean;
import biblioteca.virtual.hn.sistema_biblioteca.view.PrestamoViewModel;

public class PrestamoInteractorImpl implements PrestamoInteractor
{
    private DatabaseRepositoryImpl repositorio;
    private PrestamoViewModel vista;

    public PrestamoInteractorImpl(PrestamoViewModel vista) {
        super();
        this.vista = vista;
        this.repositorio = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 3L);
    }


    @Override
    public void consultarPrestamo() {
        try{
            PrestamoResponse response = this.repositorio.consultarPrestamo();
            if(response == null || response.getItems() == null || response.getItems().isEmpty() || response.getCount() == 0){
                this.vista.mostrarMensajeError("No se encontraron Prestamos");
            }else{
                this.vista.mostrarPrestamoDataTable(response.getItems());
            }
        }catch(Exception error){
            error.printStackTrace();
        }
    }
    @Override
    public void crearPrestamo(Prestamo prestamo) {
        try{
            boolean creado = this.repositorio.crearPrestamo(prestamo);
            if(creado){
                this.vista.mostrarMensajeExito("Prestamo creado correctamente!");
                this.vista.refrescarPantalla();
            }else{
                this.vista.mostrarMensajeError("Error al crear el Prestamo");
            }
        }catch(Exception error){
            error.printStackTrace();
        }
    }
    @Override
    public void actualizarPrestamo(Prestamo prestamo) {
        try{
            boolean modificado = this.repositorio.actualizarPrestamo(prestamo);
            if(modificado){
                this.vista.mostrarMensajeExito("Prestamo actualizado correctamente!");
                this.vista.refrescarPantalla();
            }else{
                this.vista.mostrarMensajeError("Error al actualizar el Prestamo");
            }
        }catch(Exception error){
            error.printStackTrace();
        }
    }
}
