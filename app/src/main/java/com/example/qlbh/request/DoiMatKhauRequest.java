// src/main/java/com/example/qlbh/request/DoiMatKhauRequest.java
package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class DoiMatKhauRequest {

  @SerializedName("user_id")
  private Long userId;

  @SerializedName("old_password")
  private String oldPassword;

  @SerializedName("new_password")
  private String newPassword;

  @SerializedName("confirm_password")
  private String confirmPassword;

  public DoiMatKhauRequest(Long userId, String oldPassword, String newPassword,
      String confirmPassword) {
    this.userId = userId;
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
    this.confirmPassword = confirmPassword;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
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
