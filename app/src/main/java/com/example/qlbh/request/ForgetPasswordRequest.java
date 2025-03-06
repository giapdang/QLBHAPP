package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordRequest {

  @SerializedName("email")
  private String email;

  @SerializedName("new_password")
  private String newPassword;

  @SerializedName("confirm_password")
  private String confirmPassword;

  public ForgetPasswordRequest(String email, String newPassword, String confirmPassword) {
    this.email = email;
    this.newPassword = newPassword;
    this.confirmPassword = confirmPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
