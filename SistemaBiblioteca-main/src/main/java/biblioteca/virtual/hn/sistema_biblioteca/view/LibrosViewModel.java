package biblioteca.virtual.hn.sistema_biblioteca.view;

import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;

import java.util.List;

public interface LibrosViewModel {
    void mostrarLibrosDataTable(List<Libro> libros);
    void refrescarPantalla();
    void mostrarMensajeExito(String mensaje);
    void mostrarMensajeError(String mensaje);
}
