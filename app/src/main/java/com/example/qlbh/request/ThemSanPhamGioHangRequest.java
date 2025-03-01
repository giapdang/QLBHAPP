// src/main/java/com/example/qlbh/request/ThemSanPhamGioHangRequest.java
package com.example.qlbh.request;

public class ThemSanPhamGioHangRequest {

  private Long sanPhamId;
  private int soLuong;

  public ThemSanPhamGioHangRequest(Long sanPhamId, int soLuong) {
    this.sanPhamId = sanPhamId;
    this.soLuong = soLuong;
  }

  public Long getSanPhamId() {
    return sanPhamId;
  }

  public void setSanPhamId(Long sanPhamId) {
    this.sanPhamId = sanPhamId;
  }

  public int getSoLuong() {
    return soLuong;
  }

  public void setSoLuong(int soLuong) {
    this.soLuong = soLuong;
  }
}