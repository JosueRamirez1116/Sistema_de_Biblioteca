package biblioteca.virtual.hn.sistema_biblioteca.controller;


import biblioteca.virtual.hn.sistema_biblioteca.model.Usuario;
import biblioteca.virtual.hn.sistema_biblioteca.repository.AuthRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;


@Named // Usa CDI en lugar de @ManagedBean
@SessionScoped

public class LoginBean implements Serializable {

    private Usuario usuario = new Usuario();
    private final AuthRepository authRepository = new AuthRepository();

    public String validarLogin() {
        // Usuario de prueba temporal
        if ("admin".equals(usuario.getUsername()) && "1234".equals(usuario.getPassword())) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("usuario", usuario);
            return "/index?faces-redirect=true";
        }

        // Autenticación normal con la base de datos
        if (authRepository.autenticarUsuario(usuario)) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("usuario", usuario);
            return "/index?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Credenciales inválidas", "Usuario o contraseña incorrectos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public String logout() {
        // Invalidar sesión
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    private void mostrarError(String resumen, String detalle) {
        FacesMessage msg = new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                resumen,
                detalle
        );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    // Getters & Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}