package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseBody {

    @SerializedName("seller_id")
    @Expose
    private Integer sellerId;
    @SerializedName("phonenumber")
    @Expose
    private String phonenumber;
    @SerializedName("emailid")
    @Expose
    private String emailid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("registrationdate")
    @Expose
    private String registrationdate;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("sellerImage")
    @Expose
    private String sellerImage;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(String registrationdate) {
        this.registrationdate = registrationdate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSellerImage() {
        return sellerImage;
    }

    public void setSellerImage(String sellerImage) {
        this.sellerImage = sellerImage;
    }

}
