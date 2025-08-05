package biblioteca.virtual.hn.sistema_biblioteca.controller;

import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import biblioteca.virtual.hn.sistema_biblioteca.model.LibrosResponse;
import biblioteca.virtual.hn.sistema_biblioteca.repository.DatabaseRepositoryImpl;
import biblioteca.virtual.hn.sistema_biblioteca.view.LibrosViewModel;

public class LibrosInteractorImpl implements  LibrosInteractor {
    private DatabaseRepositoryImpl repositorio;
    private LibrosViewModel vista;

    public LibrosInteractorImpl(LibrosViewModel vista) {
        super();
        this.vista = vista;
        this.repositorio = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 3L);
    }

    @Override
    public void consultarLibros() {
        try{
            LibrosResponse response = this.repositorio.consultarLibros();
            if(response == null || response.getItems() == null || response.getItems().isEmpty() || response.getCount() == 0){
                this.vista.mostrarMensajeError("No se encontraron libros");
            }else{
                this.vista.mostrarLibrosDataTable(response.getItems());

            }
        }catch(Exception error){
            error.printStackTrace();
        }
    }

    @Override
    public void crearLibro(Libro libro) {
        try{
            boolean creado = this.repositorio.crearLibros(libro);
            if(creado){
                this.vista.mostrarMensajeExito("Libro creado correctamente!");
                this.vista.refrescarPantalla();
            }else{
                this.vista.mostrarMensajeError("Error al crear el libro");
            }
        }catch(Exception error){
            error.printStackTrace();
        }
    }



    @Override
    public void actualizarLibro(Libro libro) {
        try{
            boolean modificado = this.repositorio.actualizarLibros(libro);
            if(modificado){
                this.vista.mostrarMensajeExito("Libro actualizado correctamente!");
                this.vista.refrescarPantalla();
            }else{
                this.vista.mostrarMensajeError("Error al actualizar el libro");
            }
        }catch(Exception error){
            error.printStackTrace();
        }
    }

    @Override
    public void eliminarLibro(int id) {
        try{
            boolean eliminado = this.repositorio.eliminarLibros(id);
            if(eliminado){
                this.vista.mostrarMensajeExito("Libro eliminado correctamente!");
                this.vista.refrescarPantalla();
            }else{
                this.vista.mostrarMensajeError("Error al eliminar el libro");
            }
        }catch(Exception error){
            error.printStackTrace();
        }
    }

    @Override
    public void eliminarLibros(int[] ids) {
        try{
            for(int id : ids){
                boolean eliminado = this.repositorio.eliminarLibros(id);
                if(!eliminado){
                    this.vista.mostrarMensajeError("Error al eliminar el libro");
                    this.vista.refrescarPantalla();
                    return;
                }
            }
            this.vista.mostrarMensajeExito("Libro eliminado correctamente!");
            this.vista.refrescarPantalla();
        }catch(Exception error){
            error.printStackTrace();
        }
    }
}
