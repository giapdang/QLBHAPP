// src/main/java/com/example/qlbh/request/UpdateUserRequest.java
package com.example.qlbh.request;

public class UpdateUserRequest {

  private String hoTen;
  private String email;
  private String soDienThoai;
  private String diaChi;

  public UpdateUserRequest(String hoTen, String email, String soDienThoai, String diaChi) {
    this.hoTen = hoTen;
    this.email = email;
    this.soDienThoai = soDienThoai;
    this.diaChi = diaChi;
  }

  public String getHoTen() {
    return hoTen;
  }

  public void setHoTen(String hoTen) {
    this.hoTen = hoTen;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSoDienThoai() {
    return soDienThoai;
  }

  public void setSoDienThoai(String soDienThoai) {
    this.soDienThoai = soDienThoai;
  }

  public String getDiaChi() {
    return diaChi;
  }

  public void setDiaChi(String diaChi) {
    this.diaChi = diaChi;
  }
}