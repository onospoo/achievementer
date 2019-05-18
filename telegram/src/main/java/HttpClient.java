import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8765/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();
    private HttpRepository repository = retrofit.create(HttpRepository.class);

    public HttpClient(){

    }


}
