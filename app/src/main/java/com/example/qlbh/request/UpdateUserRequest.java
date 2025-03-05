// src/main/java/com/example/qlbh/request/UpdateUserRequest.java
package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class UpdateUserRequest {

  @SerializedName("user_id")
  private Long userId;

  @SerializedName("ho_ten")
  private String hoTen;

  @SerializedName("email")
  private String email;

  @SerializedName("so_dien_thoai")
  private String soDienThoai;

  @SerializedName("dia_chi")
  private String diaChi;

  public UpdateUserRequest(Long userId, String hoTen, String email, String soDienThoai,
      String diaChi) {
    this.userId = userId;
    this.hoTen = hoTen;
    this.email = email;
    this.soDienThoai = soDienThoai;
    this.diaChi = diaChi;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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