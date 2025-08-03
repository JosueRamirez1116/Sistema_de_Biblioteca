package biblioteca.virtual.hn.sistema_biblioteca.repository;

import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class DatabaseClient {

    private Retrofit retrofit;
    //ESTA VARIABLE ME SIRVE PARA IMPRIMIR EN CONSOLA
    //LO QUE ESTA PASANDO AL CONECTARME A MI BD
    private HttpLoggingInterceptor interceptor;

    public DatabaseClient(String url, Long timeout) {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()))
                .build();
    }

    public DatabaseRepository getInstance() {
        return retrofit.create(DatabaseRepository.class);
    }
}
