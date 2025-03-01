package com.example.qlbh.request;

public class UpdateDanhMucRequest {

  private Long id;
  private String tenDanhMuc;
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
