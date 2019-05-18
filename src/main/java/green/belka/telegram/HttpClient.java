package green.belka.telegram;

import green.belka.telegram.model.ResponseData;
import green.belka.telegram.model.User;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class HttpClient {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Settings.url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();
    private HttpRepository repository = retrofit.create(HttpRepository.class);

    public HttpClient(){

    }

    public ResponseData<Long> addUser(User user) throws IOException {
        Call<ResponseData<Long>> call = repository.addUser(user);
        Response<ResponseData<Long>> response = call.execute();
        ResponseData<Long> responseData = response.body();
        return responseData;
    }

}
