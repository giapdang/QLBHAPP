// src/main/java/com/example/qlbh/request/XacThucDonHangRequest.java
package com.example.qlbh.request;

public class XacThucDonHangRequest {

  private Long donHangId;
  private String trangThai;

  public XacThucDonHangRequest(Long donHangId, String trangThai) {
    this.donHangId = donHangId;
    this.trangThai = trangThai;
  }

  public Long getDonHangId() {
    return donHangId;
  }

  public void setDonHangId(Long donHangId) {
    this.donHangId = donHangId;
  }

  public String getTrangThai() {
    return trangThai;
  }

  public void setTrangThai(String trangThai) {
    this.trangThai = trangThai;
  }
}
