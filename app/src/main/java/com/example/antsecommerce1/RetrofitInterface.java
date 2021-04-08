package com.example.antsecommerce1;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitInterface {
    @Multipart
    @POST("/antsEcommerce/uploadFile")
    Call<ProfilepicResponse> uploadImage(@Part MultipartBody.Part image);
}
