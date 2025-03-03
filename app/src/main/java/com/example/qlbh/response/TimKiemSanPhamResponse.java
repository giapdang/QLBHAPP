package com.example.qlbh.response;

import com.google.gson.annotations.SerializedName;

public class TimKiemSanPhamResponse {

  @SerializedName("id")
  private Long id;

  @SerializedName("ten_san_pham")
  private String tenSanPham;

  @SerializedName("gia")
  private String gia;

  @SerializedName("hinh_anh")
  private String hinhAnh;

  public TimKiemSanPhamResponse(Long id, String tenSanPham, String gia, String hinhAnh) {
    this.id = id;
    this.tenSanPham = tenSanPham;
    this.gia = gia;
    this.hinhAnh = hinhAnh;
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

  public String getGia() {
    return gia;
  }

  public void setGia(String gia) {
    this.gia = gia;
  }

  public String getHinhAnh() {
    return hinhAnh;
  }

  public void setHinhAnh(String hinhAnh) {
    this.hinhAnh = hinhAnh;
  }
}
