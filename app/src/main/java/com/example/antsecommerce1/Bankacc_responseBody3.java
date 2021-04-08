package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bankacc_responseBody3 {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Bankacc_responseBody2 data;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bankacc_responseBody2 getData() {
        return data;
    }

    public void setData(Bankacc_responseBody2 data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
