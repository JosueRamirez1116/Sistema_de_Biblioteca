package biblioteca.virtual.hn.sistema_biblioteca.controller;


import biblioteca.virtual.hn.sistema_biblioteca.model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("UserBean")
@ViewScoped
public class UserBean implements Serializable {

    private Usuario usuario;
    private Usuario usuarioSeleccionado;
    private List<Usuario> listaUsuarios;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listaUsuarios = new ArrayList<>();
    }

    public void guardarUsuario() {
        listaUsuarios.add(usuario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario guardado", usuario.getUsername()));
        usuario = new Usuario(); // Limpia el formulario
    }

    public void limpiarFormulario() {
        usuario = new Usuario();
    }

    public void eliminarUsuario(Usuario user) {
        listaUsuarios.remove(user);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario eliminado", user.getUsername()));
    }

    // Getters y Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
}
