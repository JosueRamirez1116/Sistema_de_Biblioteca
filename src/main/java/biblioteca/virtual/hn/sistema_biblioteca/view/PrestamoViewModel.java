package biblioteca.virtual.hn.sistema_biblioteca.view;

import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import biblioteca.virtual.hn.sistema_biblioteca.model.Prestamo;

import java.util.List;

public interface PrestamoViewModel
{
    void mostrarPrestamoDataTable(List<Prestamo> prestamo);
    void refrescarPantalla();
    void mostrarMensajeExito(String mensaje);
    void mostrarMensajeError(String mensaje);
}
