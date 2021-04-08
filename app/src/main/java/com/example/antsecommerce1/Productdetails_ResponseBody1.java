package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Productdetails_ResponseBody1 {
    @SerializedName("productVariantId")
    @Expose
    private Integer productVariantId;
    @SerializedName("productID")
    @Expose
    private Integer productID;
    @SerializedName("productVariantName")
    @Expose
    private String productVariantName;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("availabilityCount")
    @Expose
    private String availabilityCount;
    @SerializedName("percentageDiscount")
    @Expose
    private Object percentageDiscount;
    @SerializedName("unitWeight")
    @Expose
    private String unitWeight;
    @SerializedName("unitInStock")
    @Expose
    private String unitInStock;
    @SerializedName("mrp")
    @Expose
    private String mrp;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;

    public Integer getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(Integer productVariantId) {
        this.productVariantId = productVariantId;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductVariantName() {
        return productVariantName;
    }

    public void setProductVariantName(String productVariantName) {
        this.productVariantName = productVariantName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public Object getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Object percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public String getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(String unitWeight) {
        this.unitWeight = unitWeight;
    }

    public String getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(String unitInStock) {
        this.unitInStock = unitInStock;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
