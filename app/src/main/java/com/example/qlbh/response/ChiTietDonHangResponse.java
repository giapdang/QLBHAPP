// src/main/java/com/example/qlbh/response/ChiTietDonHangResponse.java
package com.example.qlbh.response;

import com.google.gson.annotations.SerializedName;

public class ChiTietDonHangResponse {

  @SerializedName("id")
  private Long id;

  @SerializedName("ten_san_pham")
  private String tenSanPham;

  @SerializedName("gia")
  private Double gia;

  @SerializedName("so_luong")
  private int soLuong;

  @SerializedName("hinh_anh")
  private String hinhAnh;


  @SerializedName("thanh_tien")
  private String thanhTien;

  public ChiTietDonHangResponse(Long id, String tenSanPham, Double gia, int soLuong, String hinhAnh,
      String thanhTien) {
    this.id = id;
    this.tenSanPham = tenSanPham;
    this.gia = gia;
    this.soLuong = soLuong;
    this.hinhAnh = hinhAnh;
    this.thanhTien = thanhTien;
  }

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

  public Double getGia() {
    return gia;
  }

  public void setGia(Double gia) {
    this.gia = gia;
  }

  public int getSoLuong() {
    return soLuong;
  }

  public void setSoLuong(int soLuong) {
    this.soLuong = soLuong;
  }

  public String getHinhAnh() {
    return hinhAnh;
  }

  public void setHinhAnh(String hinhAnh) {
    this.hinhAnh = hinhAnh;
  }

  public String getThanhTien() {
    return thanhTien;
  }

  public void setThanhTien(String thanhTien) {
    this.thanhTien = thanhTien;
  }
}
