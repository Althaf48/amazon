package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Homepageseller_Responsebody2 {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Homepage_ResponseBody1 data;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Homepage_ResponseBody1 getData() {
        return data;
    }

    public void setData(Homepage_ResponseBody1 data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
