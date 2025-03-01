// src/main/java/com/example/qlbh/response/ChiTietDonHangResponse.java
package com.example.qlbh.response;

import com.google.gson.annotations.SerializedName;

public class ChiTietDonHangResponse {

  @SerializedName("id")
  private Long id;

  @SerializedName("ten_san_pham")
  private String tenSanPham;

  @SerializedName("gia")
  private String gia;

  @SerializedName("so_luong")
  private int soLuong;

  @SerializedName("don_gia")
  private String donGia;

  @SerializedName("thanh_tien")
  private String thanhTien;

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTenSanPham() {
    return tenSanPham;
  }

  public void setTenSanPham(String tenSanPham) {
    this.tenSanPham = tenSanPham;
  }

  public String getGia() {
    return gia;
  }

  public void setGia(String gia) {
    this.gia = gia;
  }

  public int getSoLuong() {
    return soLuong;
  }

  public void setSoLuong(int soLuong) {
    this.soLuong = soLuong;
  }

  public String getDonGia() {
    return donGia;
  }

  public void setDonGia(String donGia) {
    this.donGia = donGia;
  }

  public String getThanhTien() {
    return thanhTien;
  }

  public void setThanhTien(String thanhTien) {
    this.thanhTien = thanhTien;
  }
}
