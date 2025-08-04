package biblioteca.virtual.hn.sistema_biblioteca.repository;


import biblioteca.virtual.hn.sistema_biblioteca.model.Usuario;
import biblioteca.virtual.hn.sistema_biblioteca.model.UsuariosResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface AuthRepositoryApi {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/pls/apex/ale_rj11/bibliotecauth/usuarios_biblioteca")
    Call<UsuariosResponse> listUsuarios();

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("/pls/apex/ale_rj11/bibliotecauth/usuarios_biblioteca")
    Call<ResponseBody> createUsuario(@Body Usuario usuario);

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @DELETE("/pls/apex/ale_rj11/bibliotecauth/usuarios_biblioteca/{id}")
    Call<ResponseBody> deleteUsuario(@Path("id") int id);
}
