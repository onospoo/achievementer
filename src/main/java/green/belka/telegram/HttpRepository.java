package green.belka.telegram;

import green.belka.telegram.model.Achievement;
import green.belka.telegram.model.ResponseData;
import green.belka.telegram.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpRepository {
    @POST("/user/")
    Call<ResponseData<Long>> addUser(@Body User user);

    @POST("/achievement/")
    Call<ResponseData<Long>> addAchievement(@Body Achievement achievement);


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