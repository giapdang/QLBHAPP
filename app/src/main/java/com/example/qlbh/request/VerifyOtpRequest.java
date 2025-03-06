// VerifyOtpRequest.java
package com.example.qlbh.request;

public class VerifyOtpRequest {
    private String otp;
    private String email;

    public VerifyOtpRequest(String otp, String email) {
        this.otp = otp;
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
