package com.example.antsecommerce1;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RegisterInterface {

    @POST("antsEcommerce/api/v1/auth/signuprequest")
    @Headers({"Content-Type: application/json"})
    Call<Signupresponsebody2> getUserRegi(@Body SignupRequestbody object);


    @POST("antsEcommerce/api/v1/auth/generateOtp")
    @Headers({"Content-Type: application/json"})
    Call<Generateotp_responseBody> getGenerateotp(@Body Generateotp_requestBody object);

    @POST("antsEcommerce/api/v1/auth/verifyAndSendOtp")
    @Headers({"Content-Type: application/json"})
    Call<Generateotp_responseBody> getGenerateotp2(@Body GenererateotpforgotrequestBody object);


    @POST("antsEcommerce/api/v1/auth/ChangePassword")
    @Headers({"Content-Type: application/json"})
    Call<Reset_ResponseBody> getReset(@Body Reset_RequestBody object);


    @POST("antsEcommerce/api/v1/auth/validateOtp")
    @Headers({"Content-Type: application/json"})
    Call<Validateotp_ResponseBody> getValidate2(@Body Validateotp_RequestBody object);

    @POST("antsEcommerce/api/v1/auth/login")
    @Headers({"Content-Type: application/json"})
    Call<LoginResponseBody2> getLogin(@Body LoginRequestbody object);

    @POST("antsEcommerce/api/v1/auth/seller/saveAddress")
    @Headers({"Content-Type: application/json"})
    Call<Homepageseller_Responsebody2> getHomepagesellerprofileupdate(@Body Homepageseller_RequestBody object);


    @POST("antsEcommerce/api/v1/auth/seller/saveBankDetails")
    @Headers({"Content-Type: application/json"})
    Call<Bankacc_responseBody3> getBankaccountdetails(@Body Bankdetailsseller_requestBody object);


    @POST("antsEcommerce/api/v1/auth/seller/saveBankDetails")
    @Headers({"Content-Type: application/json"})
    Call<Bankacc_responseBody3> getBankaccountupdatedetails(@Body Bankaccupdate_RequestBody object);



    @POST("antsEcommerce/api/v1/auth/seller/saveGstDetails")
    @Headers({"Content-Type: application/json"})
    Call<Taxdetails_ResponseBody2> getTaxdetails(@Body Taxdetails_RequestBody object);

    //http://113.11.231.20:8070/antsEcommerce/api/v1/auth/cityInfo/Telangana
    @GET("antsEcommerce/api/v1/auth/cityInfo/{telangana}")
    Call<JsonObject> getJSONString2(@Path("telangana") String telangana);

    @GET("antsEcommerce/api/v1/auth/seller/address/{telangana}")
    Call<JsonObject> getsaveaddresgettingdetails(@Path("telangana") String telangana);

    @GET("antsEcommerce/api/v1/auth/seller/bankinfo/{telangana}")
    Call<JsonObject> getgettingBankdetails(@Path("telangana") String telangana);

    @GET("antsEcommerce/api/v1/auth/seller/gstinfo/{telangana}")
    Call<JsonObject> getgettingtaxdetails(@Path("telangana") String telangana);

 /*   @GET("antsEcommerce/api/v1/auth/cityInfo")
    Call<JsonObject> getJSONString();*/

    @GET("antsEcommerce/api/v1/auth/stateInfo")
    Call<JsonObject> getJSONString();


    @Multipart
    @POST("/images/upload")
    Call<ProfilepicResponse> uploadImage(@Part MultipartBody.Part image);


    @POST("antsEcommerce/api/v1/auth/seller/saveSellerCompanyDetails")
    @Headers({"Content-Type: application/json"})
    Call<Companyname_responseBody2> getCompanyname(@Body Companyname_RequestBody object);

    @POST("antsEcommerce/api/v1/auth/seller/saveSellerImage")
    @Headers({"Content-Type: application/json"})
    Call<Profilepicnamedb_ResponseBody2> getprofilepidb(@Body Profilepicnamedb_RequestBody object);

    @GET("antsEcommerce/downloadProductFile/{xlsxfile}")
    Call<ResponseBody> getelectronicsxlsx(@Path("xlsxfile") String xlsxfile);

    @GET("antsEcommerce/api/productDetailsBySellerId/{sellerId}/{categoryId}")
    Call<JsonObject> getmanageproductdetails(@Path("sellerId") String sellerId,@Path("categoryId") String categoryId);


    @POST("antsEcommerce/api/productDetailsUpdate")
    @Headers({"Content-Type: application/json"})
    Call<ProductdetaileditResponseBody2> getProducteditdetails(@Body Product_Detailsedit_RequestBody object);

    // option 1: a resource relative to your base URL
    @GET("/resource/example.zip")
    Call<ResponseBody> downloadFileWithFixedUrl();

    // option 2: using a dynamic URL
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);


}
