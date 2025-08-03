package biblioteca.virtual.hn.sistema_biblioteca.controller;

import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;

public interface LibrosInteractor {
    void consultarLibros();
    void crearLibro(Libro libro);
    void actualizarLibro(Libro libro);
    void eliminarLibro(int id);
    void eliminarLibros(int[] ids);
}
