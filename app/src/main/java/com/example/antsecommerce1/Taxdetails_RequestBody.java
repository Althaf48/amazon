package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Taxdetails_RequestBody {

    @SerializedName("panNumber")
    @Expose
    private String panNumber;
    @SerializedName("sellerId")
    @Expose
    private String sellerId;
    @SerializedName("gstNumber")
    @Expose
    private String gstNumber;

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }



}
