// src/main/java/com/example/qlbh/request/ThemDonHangRequest.java
package com.example.qlbh.request;

import com.example.qlbh.entity.SanPham;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ThemDonHangRequest {

  @SerializedName("san_pham")
  private List<SanPham> sanPham;

  public ThemDonHangRequest(List<SanPham> sanPham) {
    this.sanPham = sanPham;
  }

  public List<SanPham> getSanPham() {
    return sanPham;
  }

  public void setSanPham(List<SanPham> sanPham) {
    this.sanPham = sanPham;
  }
}
