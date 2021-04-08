package com.example.antsecommerce1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Validateotp_RequestBody {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("otpexpirytime")
    @Expose
    private String otpexpirytime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getOtpexpirytime() {
        return otpexpirytime;
    }

    public void setOtpexpirytime(String otpexpirytime) {
        this.otpexpirytime = otpexpirytime;
    }
}
