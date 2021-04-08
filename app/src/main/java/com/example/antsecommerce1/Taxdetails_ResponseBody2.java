package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Taxdetails_ResponseBody2 {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private TaxdetailsResponseBody1 data;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaxdetailsResponseBody1 getData() {
        return data;
    }

    public void setData(TaxdetailsResponseBody1 data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
