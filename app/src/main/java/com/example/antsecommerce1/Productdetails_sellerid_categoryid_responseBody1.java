package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Productdetails_sellerid_categoryid_responseBody1 {

    @SerializedName("productID")
    @Expose
    private Integer productID;
    @SerializedName("productVariantId")
    @Expose
    private Integer productVariantId;
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
    private String percentageDiscount;
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
    @SerializedName("productCategory")
    @Expose
    private Integer productCategory;
    @SerializedName("productImage1")
    @Expose
    private String productImage1;

    public Productdetails_sellerid_categoryid_responseBody1(Integer productID, Integer productVariantId, String productVariantName, String sku, Double price, String availabilityCount, String percentageDiscount, String unitWeight, String unitInStock, String mrp, Boolean isActive, Integer productCategory, String productImage1) {
        this.productID = productID;
        this.productVariantId = productVariantId;
        this.productVariantName = productVariantName;
        this.sku = sku;
        this.price = price;
        this.availabilityCount = availabilityCount;
        this.percentageDiscount = percentageDiscount;
        this.unitWeight = unitWeight;
        this.unitInStock = unitInStock;
        this.mrp = mrp;
        this.isActive = isActive;
        this.productCategory = productCategory;
        this.productImage1 = productImage1;
    }


    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(Integer productVariantId) {
        this.productVariantId = productVariantId;
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

    public String getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(String percentageDiscount) {
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

    public Integer getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Integer productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductImage1() {
        return productImage1;
    }

    public void setProductImage1(String productImage1) {
        this.productImage1 = productImage1;
    }


}
