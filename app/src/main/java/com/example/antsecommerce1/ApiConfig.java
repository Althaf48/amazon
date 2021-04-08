package com.example.antsecommerce1;

/**
 * Created by delaroystudios on 10/5/2016.
 */
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;


public interface ApiConfig {

    @Multipart
    @POST("antsEcommerce/uploadFile")
    Call<ProfilepicResponse> upload( @Header("Authorization") String authorization,@PartMap Map<String, RequestBody> map);


    @Multipart
    @POST("antsEcommerce/uploadProductFile")
    Call<FileuploadDoc_ResponseBody> uploaddoc( @Header("Authorization") String authorization,@PartMap Map<String, RequestBody> map);


    @POST("antsEcommerce/uploadProductFile")
    @Headers({"Content-Type: application/json"})
    Call<FileuploadDoc_ResponseBody> getuploaddoc(@Body FileuploadDoc_RequestBody object);

}