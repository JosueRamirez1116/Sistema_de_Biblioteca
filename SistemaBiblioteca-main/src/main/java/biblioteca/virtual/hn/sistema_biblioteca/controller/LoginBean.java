package biblioteca.virtual.hn.sistema_biblioteca.controller;

import biblioteca.virtual.hn.sistema_biblioteca.model.Usuario;
import biblioteca.virtual.hn.sistema_biblioteca.repository.AuthRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private Usuario usuario = new Usuario();
    private final AuthRepository authRepository = new AuthRepository();

    public String validarLogin() {
        // Usuario admin de prueba temporal
        if ("admin".equals(usuario.getUsername()) && "1234".equals(usuario.getPassword())) {
            usuario.setTipo("admin");
            guardarEnSesion(usuario);
            return "/index?faces-redirect=true";
        }

        // Usuario temporal no admin para pruebas
        if ("usuario".equals(usuario.getUsername()) && "1234".equals(usuario.getPassword())) {
            usuario.setTipo("usuario");
            usuario.setNombre("Usuario Temporal");
            usuario.setEmail("usuario@temporal.com");
            guardarEnSesion(usuario);
            return "/librosUsuario?faces-redirect=true";
        }

        // Autenticación real
        if (authRepository.autenticarUsuario(usuario)) {
            Usuario usuarioBD = authRepository.obtenerPorUsername(usuario.getUsername());
            if (usuarioBD != null) {
                usuario = usuarioBD; // reemplazar con datos reales
                guardarEnSesion(usuario);

                if ("admin".equalsIgnoreCase(usuario.getTipo())) {
                    return "index?faces-redirect=true";
                } else {
                    return "/librosUsuario?faces-redirect=true";
                }
            } else {
                mostrarError("Error", "No se pudo recuperar el usuario.");
                return null;
            }
        } else {
            mostrarError("Credenciales inválidas", "Usuario o contraseña incorrectos");
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    private void guardarEnSesion(Usuario usuario) {
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("usuario", usuario);
    }

    private void mostrarError(String resumen, String detalle) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, resumen, detalle);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    // Getter y Setter
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
