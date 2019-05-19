package green.belka.telegram;

import green.belka.telegram.model.Achievement;
import green.belka.telegram.model.ResponseData;
import green.belka.telegram.model.User;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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


    public ResponseData<Long> addAchievement(Achievement achievement) throws IOException {
        Call<ResponseData<Long>> call = repository.addAchievement(achievement);
        Response<ResponseData<Long>> response = call.execute();
        ResponseData<Long> responseData = response.body();
        return responseData;
    }


    public ResponseData<List<UUID>> getKeys(long id) throws IOException {
        Call<ResponseData<List<UUID>>> call = repository.getKeys(id);
        Response<ResponseData<List<UUID>>> response = call.execute();
        ResponseData<List<UUID>> responseData = response.body();
        return responseData;
    }


    public ResponseData<Long> approve(String key, Long id) throws IOException {
        Call<ResponseData<Long>> call = repository.approve(key, id);
        Response<ResponseData<Long>> response = call.execute();
        ResponseData<Long> responseData = response.body();
        return responseData;
    }

    public ResponseData<Long> admin(String nickname) throws IOException {
        Call<ResponseData<Long>> call = repository.admin(nickname);
        Response<ResponseData<Long>> response = call.execute();
        ResponseData<Long> responseData = response.body();
        return responseData;
    }
}
