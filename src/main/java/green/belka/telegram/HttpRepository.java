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

/*
    @GET("customers")
    Call<ResponseData<List<Customer>>> getCustomers();

    @GET("customer/{id}")
    Call<ResponseData<Customer>> getCustomer(@Path("id") UUID id);

    @POST("customer/{id}")
    Call<ResponseData<UUID>> updateCustomer(@Path("id") UUID id, @Body Customer customer);

    @POST("customers")
    Call<ResponseData<UUID>> addCustomer(@Body Customer customer);

    @DELETE("customer/{id}")
    Call<ResponseData<UUID>> deleteCustomer(@Path("id") UUID id);
    */
}