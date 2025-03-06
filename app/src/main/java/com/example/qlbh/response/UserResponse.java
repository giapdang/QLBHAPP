package com.example.qlbh.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

  @SerializedName("id")
  private Long id;

  @SerializedName("ho_ten")
  private String hoTen;

  @SerializedName("email")
  private String email;

  @SerializedName("so_dien_thoai")
  private String soDienThoai;

  @SerializedName("dia_chi")
  private String diaChi;

  public UserResponse(Long id, String hoTen, String email, String soDienThoai, String diaChi) {
    this.id = id;
    this.hoTen = hoTen;
    this.email = email;
    this.soDienThoai = soDienThoai;
    this.diaChi = diaChi;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
