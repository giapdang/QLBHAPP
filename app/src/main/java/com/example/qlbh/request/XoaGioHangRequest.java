// src/main/java/com/example/qlbh/request/XoaGioHangRequest.java
package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class XoaGioHangRequest {

  @SerializedName("gio_hang_id")
  private Long gioHangId;

  @SerializedName("user_id")
  private Long userId;

  public XoaGioHangRequest(Long gioHangId, Long userId) {
    this.gioHangId = gioHangId;
    this.userId = userId;
  }

  public Long getGioHangId() {
    return gioHangId;
  }

  public void setGioHangId(Long gioHangId) {
    this.gioHangId = gioHangId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
