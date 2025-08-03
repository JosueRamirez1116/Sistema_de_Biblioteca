package biblioteca.virtual.hn.sistema_biblioteca.repository;


import biblioteca.virtual.hn.sistema_biblioteca.model.Usuario;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AuthRepository {

    public boolean autenticarUsuario(Usuario usuario) {
        try {
            DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/OracleAPEX");

            try (Connection conn = ds.getConnection();
                 CallableStatement cstmt = conn.prepareCall(
                         "{ ? = call apex_authentication.login(?, ?) }")) {

                cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
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
}
