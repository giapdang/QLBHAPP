package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

  @SerializedName("email")
  private String email;

  @SerializedName("mat_khau")
  private String password;

  public LoginRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
