// src/main/java/com/example/qlbh/response/DonHangResponse.java
package com.example.qlbh.response;

import com.google.gson.annotations.SerializedName;

public class DonHangResponse {

  @SerializedName("id")
  private Long id;

  @SerializedName("tong_tien")
  private String tongTien;

  @SerializedName("trang_thai")
  private String trangThai;

  @SerializedName("ngay_tao")
  private String ngayTao;

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTongTien() {
    return tongTien;
  }

  public void setTongTien(String tongTien) {
    this.tongTien = tongTien;
  }

  public String getTrangThai() {
    return trangThai;
  }

  public void setTrangThai(String trangThai) {
    this.trangThai = trangThai;
  }

  public String getNgayTao() {
    return ngayTao;
  }

  public void setNgayTao(String ngayTao) {
    this.ngayTao = ngayTao;
  }
}
