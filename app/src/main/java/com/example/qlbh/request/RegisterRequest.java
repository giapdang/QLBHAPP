package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
  @SerializedName("ho_ten")
  private String hoTen;

  @SerializedName("mat_khau")
  private String matKhau;

  @SerializedName("xac_nhan_mat_khau")
  private String xacNhanMatKhau;

  @SerializedName("email")
  private String email;

  @SerializedName("so_dien_thoai")
  private String soDienThoai;

  @SerializedName("dia_chi")
  private String diaChi;

  public RegisterRequest(String hoTen, String matKhau, String xacNhanMatKhau, String email, String soDienThoai, String diaChi) {
    this.hoTen = hoTen;
    this.matKhau = matKhau;
    this.xacNhanMatKhau = xacNhanMatKhau;
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

  public String getMatKhau() {
    return matKhau;
  }

  public void setMatKhau(String matKhau) {
    this.matKhau = matKhau;
  }

  public String getXacNhanMatKhau() {
    return xacNhanMatKhau;
  }

  public void setXacNhanMatKhau(String xacNhanMatKhau) {
    this.xacNhanMatKhau = xacNhanMatKhau;
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
