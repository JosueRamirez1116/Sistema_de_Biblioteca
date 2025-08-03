package biblioteca.virtual.hn.sistema_biblioteca.repository;

import biblioteca.virtual.hn.sistema_biblioteca.model.Libro;
import biblioteca.virtual.hn.sistema_biblioteca.model.LibrosResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class DatabaseRepositoryImpl {
    private static DatabaseRepositoryImpl instance;
    private DatabaseClient client;

    private DatabaseRepositoryImpl(String url, Long timeout){
        client = new DatabaseClient(url, timeout);
    }

    //PATRON SINGLETON
    public static DatabaseRepositoryImpl getInstance(String url, Long timeout){
        if(instance == null){
            synchronized (DatabaseRepositoryImpl.class){
                if(instance == null){
                    instance = new DatabaseRepositoryImpl(url, timeout);
                }
            }
        }
        return instance;
    }

    public LibrosResponse consultarLibros() throws IOException {
        Call<LibrosResponse> call = client.getInstance().listLibros();
        Response<LibrosResponse> response = call.execute();//EJECUTA EL LLAMADO A LA BASE DE DATOS
        if(response.isSuccessful()){//CODIGO (STATUS CODE HTTP 200)
            return response.body();
        }else{
            return null;
        }
    }

    public boolean crearLibros(Libro nueva) throws IOException {
        Call<ResponseBody> call = client.getInstance().createLibro(nueva);
        Response<ResponseBody> response = call.execute();//EJECUTA EL LLAMADO A LA BASE DE DATOS
        return response.isSuccessful();
    }

    public boolean actualizarLibros(Libro existente) throws IOException {
        Call<ResponseBody> call = client.getInstance().updateLibro(existente);
        Response<ResponseBody> response = call.execute();//EJECUTA EL LLAMADO A LA BASE DE DATOS
        return response.isSuccessful();
    }

    public boolean eliminarLibros(int id) throws IOException {
        Call<ResponseBody> call = client.getInstance().deleteLibro(id);
        Response<ResponseBody> response = call.execute();//EJECUTA EL LLAMADO A LA BASE DE DATOS
        return response.isSuccessful();
    }



}
