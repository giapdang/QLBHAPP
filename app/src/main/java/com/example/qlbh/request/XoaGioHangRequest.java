// src/main/java/com/example/qlbh/request/XoaGioHangRequest.java
package com.example.qlbh.request;

public class XoaGioHangRequest {

  private Long gioHangId;

  public XoaGioHangRequest(Long gioHangId) {
    this.gioHangId = gioHangId;
  }

  public Long getGioHangId() {
    return gioHangId;
  }

  public void setGioHangId(Long gioHangId) {
    this.gioHangId = gioHangId;
  }
}
