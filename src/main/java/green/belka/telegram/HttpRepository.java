package green.belka.telegram;

import green.belka.telegram.model.Achievement;
import green.belka.telegram.model.ResponseData;
import green.belka.telegram.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import javax.ws.rs.PathParam;
import java.util.List;
import java.util.UUID;

public interface HttpRepository {
    @POST("/user/")
    Call<ResponseData<Long>> addUser(@Body User user);

    @POST("/achievement/")
    Call<ResponseData<Long>> addAchievement(@Body Achievement achievement);

    @GET("/keys/{id}/")
    Call<ResponseData<List<UUID>>> getKeys(@Path("id") Long id);

    @POST("/approve/{key}/{id}/")
    Call<ResponseData<Long>> approve(@Path("key") String key, @Path("id") Long id);

    @POST("/admin/{nickname}/")
    Call<ResponseData<Long>> admin(@Path("nickname") String nickname);

    @GET("/admin/{nickname}/")
    Call<ResponseData<Boolean>> isAdmin(@Path("nickname") String nickname);

}