package biblioteca.virtual.hn.sistema_biblioteca.repository;

import biblioteca.virtual.hn.sistema_biblioteca.model.Usuario;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class AuthRepository {

    public boolean autenticarUsuario(Usuario usuario) {
        try {
            DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/OracleAPEX");

            try (Connection conn = ds.getConnection();
                 CallableStatement cstmt = conn.prepareCall(
                         "{ ? = call apex_authentication.login(?, ?) }")) {

                cstmt.registerOutParameter(1, Types.INTEGER);
                cstmt.setString(2, usuario.getUsername());
                cstmt.setString(3, usuario.getPassword());
                cstmt.execute();

                return cstmt.getInt(1) == 1;
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario obtenerPorUsername(String username) {
        Usuario usuario = null;

        try {
            DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/OracleAPEX");
            try (Connection conn = ds.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                         "SELECT username, nombre, email, telefono, tipo FROM usuarios WHERE username = ?")) {
                pstmt.setString(1, username);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario();
                        usuario.setUsername(rs.getString("username"));
                        usuario.setNombre(rs.getString("nombre"));
                        usuario.setEmail(rs.getString("email"));
                        usuario.setTelefono(rs.getString("telefono"));
                        usuario.setTipo(rs.getString("tipo"));
                    }
                }
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
