// src/main/java/com/example/qlbh/request/ThemDanhMucRequest.java
package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class ThemDanhMucRequest {

  @SerializedName("ten_danh_muc")
  private String tenDanhMuc;

  @SerializedName("mo_ta")
  private String moTa;

  public ThemDanhMucRequest(String tenDanhMuc, String moTa) {
    this.tenDanhMuc = tenDanhMuc;
    this.moTa = moTa;
  }

  public String getTenDanhMuc() {
    return tenDanhMuc;
  }

  public void setTenDanhMuc(String tenDanhMuc) {
    this.tenDanhMuc = tenDanhMuc;
  }

  public String getMoTa() {
    return moTa;
  }

  public void setMoTa(String moTa) {
    this.moTa = moTa;
  }
}
