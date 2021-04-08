package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profilepicnamedb_RequestBody {

    @SerializedName("sellerId")
    @Expose
    private String sellerId;
    @SerializedName("sellerImage")
    @Expose
    private String sellerImage;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerImage() {
        return sellerImage;
    }

    public void setSellerImage(String sellerImage) {
        this.sellerImage = sellerImage;
    }


}
