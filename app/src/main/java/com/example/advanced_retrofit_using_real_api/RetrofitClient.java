package com.example.advanced_retrofit_using_real_api;

import com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers.PlayerArrayDataListClass;
import com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers.TeamClass;
import com.example.advanced_retrofit_using_real_api.GET_RequestCode.ContinentListDataClass;
import com.example.advanced_retrofit_using_real_api.GET_RequestCode.ContinentObjectDataClass;
import com.example.advanced_retrofit_using_real_api.POST_RequestCode.MainObjectClass;
import com.example.advanced_retrofit_using_real_api.POST_RequestCode.POST_Method_with_FormUrlEncode.CreateUserClass;
import com.example.advanced_retrofit_using_real_api.POST_RequestCode.Response.MainResponseObjectClass;
import com.example.advanced_retrofit_using_real_api.PUT_PATCH_DELETE_RequestCode.ObjectStructure;
import com.example.advanced_retrofit_using_real_api.Upload_Image_with_Retrofit.ResponseImageClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitClient {
    // GET
    @GET("continents")
    Call<ContinentListDataClass> getData(@Query("api_token") String token);

    @GET("continents/{id}")
    Call<ContinentObjectDataClass> getData(
            @Path ("id") int id,
            @Query("api_token") String token
    );

    @GET("teams/{id}")
    Call<TeamClass> getTeamData(
            @Path ("id") int id,
            @Query("api_token") String token
    );

    @GET("players")
    Call<PlayerArrayDataListClass> getPlayerData(
            @Query("api_token") String token,
            @Query("country_id") int cid
    );

    // POST
    @POST("q")
    Call<MainResponseObjectClass> getPostValue(@Body MainObjectClass mainObjectClass);

    // Create form with user information
    @FormUrlEncoded
    @POST("posts")
    Call<CreateUserClass> createUser(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String body
    );

    // PUT
    @PUT("posts/{id}")
    Call<ObjectStructure> putRequest(
            @Path("id") int id,
            @Body ObjectStructure objectStructure
    );

    // PATCH
    @PATCH("posts/{id}")
    Call<ObjectStructure> patchRequest(
            @Path("id") int id,
            @Body ObjectStructure objectStructure
    );

    // DELETE
    @DELETE("posts/{id}")
    Call<Void> deleteRequest(@Path("id") int id);

    // Capture and Upload Image
    @POST("retrofit.php")
    @FormUrlEncoded
    Call<ResponseImageClass> CaptureUploadImage(
            @Field("name") String name,
            @Field("image") String image
    );
}
