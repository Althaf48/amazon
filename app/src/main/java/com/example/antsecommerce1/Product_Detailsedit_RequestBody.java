package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product_Detailsedit_RequestBody {

    @SerializedName("productVariantId")
    @Expose
    private Integer productVariantId;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("availabilityCount")
    @Expose
    private String availabilityCount;
    @SerializedName("productVariantName")
    @Expose
    private String productVariantName;
    @SerializedName("mrp")
    @Expose
    private String mrp;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;

    public Integer getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(Integer productVariantId) {
        this.productVariantId = productVariantId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAvailabilityCount() {
        return availabilityCount;
    }

    public void setAvailabilityCount(String availabilityCount) {
        this.availabilityCount = availabilityCount;
    }

    public String getProductVariantName() {
        return productVariantName;
    }

    public void setProductVariantName(String productVariantName) {
        this.productVariantName = productVariantName;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
