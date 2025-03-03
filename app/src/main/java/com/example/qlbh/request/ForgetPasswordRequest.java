package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordRequest {

  @SerializedName("new_password")
  private String newPassword;

  @SerializedName("confirm_password")
  private String confirmPassword;

  public ForgetPasswordRequest(String newPassword, String confirmPassword) {
    this.newPassword = newPassword;
    this.confirmPassword = confirmPassword;
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
