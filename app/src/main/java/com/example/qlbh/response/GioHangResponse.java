// src/main/java/com/example/qlbh/response/GioHangResponse.java
package com.example.qlbh.response;

import com.google.gson.annotations.SerializedName;

public class GioHangResponse {

  @SerializedName("id")
  private Long id;

  @SerializedName("id_san_pham")
  private Long idSanPham;

  @SerializedName("ten_san_pham")
  private String tenSanPham;

  @SerializedName("gia")
  private Double gia;

  @SerializedName("so_luong")
  private int soLuong;

  @SerializedName("hinh_anh")
  private String hinhAnh;

  public GioHangResponse(Long id, Long idSanPham, String tenSanPham, Double gia, int soLuong,
      String hinhAnh) {
    this.id = id;
    this.idSanPham = idSanPham;
    this.tenSanPham = tenSanPham;
    this.gia = gia;
    this.soLuong = soLuong;
    this.hinhAnh = hinhAnh;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdSanPham() {
    return idSanPham;
  }

  public void setIdSanPham(Long idSanPham) {
    this.idSanPham = idSanPham;
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
}