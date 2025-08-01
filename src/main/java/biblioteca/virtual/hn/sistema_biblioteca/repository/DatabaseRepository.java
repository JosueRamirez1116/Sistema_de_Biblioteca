package biblioteca.virtual.hn.sistema_biblioteca.repository;

import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import biblioteca.virtual.hn.sistema_biblioteca.model.LibrosResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface DatabaseRepository {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/pls/apex/ale_rj11/bibliotecauth/libros")
    Call<LibrosResponse> listLibros();

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("/pls/apex/ingenieria_uth/appcine/peliculas")
    Call<ResponseBody> createLibro(@Body Libro libro);

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @PUT("/pls/apex/ingenieria_uth/appcine/peliculas")
    Call<ResponseBody> updateLibro(@Body Libro libro);

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @DELETE("/pls/apex/ingenieria_uth/appcine/peliculas")
    Call<ResponseBody> deleteLibro(@Query("id") int id);
}
