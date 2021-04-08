package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileuploadDoc_RequestBody {

    @SerializedName("sellerId")
    @Expose
    private int sellerId;
    @SerializedName("file")
    @Expose
    private String file;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
