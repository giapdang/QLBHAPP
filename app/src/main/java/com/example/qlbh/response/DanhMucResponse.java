// src/main/java/com/example/qlbh/response/DanhMucResponse.java
package com.example.qlbh.response;

import com.google.gson.annotations.SerializedName;

public class DanhMucResponse {

  @SerializedName("id")
  private Long id;

  @SerializedName("ten_danh_muc")
  private String tenDanhMuc;

  @SerializedName("mo_ta")
  private String moTa;

  public String getMoTa() {
    return moTa;
  }

  public void setMoTa(String moTa) {
    this.moTa = moTa;
  }

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTenDanhMuc() {
    return tenDanhMuc;
  }

  public void setTenDanhMuc(String tenDanhMuc) {
    this.tenDanhMuc = tenDanhMuc;
  }
}
