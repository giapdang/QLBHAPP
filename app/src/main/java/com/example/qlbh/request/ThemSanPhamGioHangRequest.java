package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;

public class ThemSanPhamGioHangRequest {

  @SerializedName("user_id")
  private Long userId;

  @SerializedName("san_pham_id")
  private Long sanPhamId;

  @SerializedName("so_luong")
  private int soLuong;

  public ThemSanPhamGioHangRequest(Long userId, Long sanPhamId, int soLuong) {
    this.userId = userId;
    this.sanPhamId = sanPhamId;
    this.soLuong = soLuong;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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