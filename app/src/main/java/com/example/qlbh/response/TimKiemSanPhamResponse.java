package com.example.qlbh.response;

import com.google.gson.annotations.SerializedName;

public class TimKiemSanPhamResponse {

  @SerializedName("id")
  private Long id;

  @SerializedName("ten_san_pham")
  private String tenSanPham;

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
}
