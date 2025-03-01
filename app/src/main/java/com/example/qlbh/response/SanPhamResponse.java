package com.example.qlbh.response;

import com.google.gson.annotations.SerializedName;

public class SanPhamResponse {

  @SerializedName("id")
  private Long id;

  @SerializedName("hinh_anh")
  private String hinhAnh;

  @SerializedName("ten_san_pham")
  private String tenSanPham;

  @SerializedName("gia")
  private Long gia;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getHinhAnh() {
    return hinhAnh;
  }

  public void setHinhAnh(String hinhAnh) {
    this.hinhAnh = hinhAnh;
  }

  public String getTenSanPham() {
    return tenSanPham;
  }

  public void setTenSanPham(String tenSanPham) {
    this.tenSanPham = tenSanPham;
  }

  public Long getGia() {
    return gia;
  }

  public void setGia(Long gia) {
    this.gia = gia;
  }
}
