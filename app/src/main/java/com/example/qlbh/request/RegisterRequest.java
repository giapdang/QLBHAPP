package com.example.qlbh.request;

public class RegisterRequest {

  private String username;
  private String email;
  private String phone;
  private String address;
  private String password;
  private String confirmPassword;

  public RegisterRequest(String username, String email, String phone, String address,
      String password,
      String confirmPassword) {
    this.username = username;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
