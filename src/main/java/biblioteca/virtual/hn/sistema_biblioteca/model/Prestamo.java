package biblioteca.virtual.hn.sistema_biblioteca.model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Prestamo implements Serializable
{
    private int id_prestamo;
    private String usuario;
    private String libro;
    private LocalDateTime fecha_prestamo;
    private LocalDateTime fecha_devolucion;
    private String estado;

    public Prestamo() {
        this.id_prestamo = 0;
        this.usuario = "";
        this.libro = "";
        this.fecha_prestamo = LocalDateTime.now();;
        this.fecha_devolucion = LocalDateTime.now().plusDays(1);
        this.estado = "";
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getLibro() {
        return libro;
    }

    public LocalDateTime getFecha_prestamo() {
        return fecha_prestamo;
    }

    public LocalDateTime getFecha_devolucion() {
        return fecha_devolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public void setFecha_prestamo(LocalDateTime fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public void setFecha_devolucion(LocalDateTime fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
