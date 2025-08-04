package biblioteca.virtual.hn.sistema_biblioteca.controller;

import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import biblioteca.virtual.hn.sistema_biblioteca.model.Prestamo;

public interface PrestamoInteractor
{
    void consultarPrestamo();
    void crearPrestamo(Prestamo prestamo);
    void actualizarPrestamo(Prestamo prestamo);
}
