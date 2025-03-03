package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class UpdateDanhMucRequest {

  @SerializedName("id")
  private Long id;

  @SerializedName("ten_danh_muc")
  private String tenDanhMuc;

  @SerializedName("mo_ta")
  private String moTa;

  public UpdateDanhMucRequest(Long id, String tenDanhMuc, String moTa) {
    this.id = id;
    this.tenDanhMuc = tenDanhMuc;
    this.moTa = moTa;
  }

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

  public String getMoTa() {
    return moTa;
  }

  public void setMoTa(String moTa) {
    this.moTa = moTa;
  }

}
