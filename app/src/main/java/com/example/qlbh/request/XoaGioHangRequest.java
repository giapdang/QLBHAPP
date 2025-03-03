// src/main/java/com/example/qlbh/request/XoaGioHangRequest.java
package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class XoaGioHangRequest {

  @SerializedName("gio_hang_id")
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
