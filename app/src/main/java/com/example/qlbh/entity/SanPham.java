package com.example.qlbh.entity;

import com.google.gson.annotations.SerializedName;

public class SanPham {

  @SerializedName("gio_hang_id")
  private Long gioHangId;

  @SerializedName("san_pham_id")
  private Long sanPhamId;

  @SerializedName("so_luong")
  private int soLuong;

  @SerializedName("don_gia")
  private double donGia;

  public SanPham(Long gioHangId, Long sanPhamId, int soLuong, double donGia) {
    this.gioHangId = gioHangId;
    this.sanPhamId = sanPhamId;
    this.soLuong = soLuong;
    this.donGia = donGia;
  }

  public Long getGioHangId() {
    return gioHangId;
  }

  public void setGioHangId(Long gioHangId) {
    this.gioHangId = gioHangId;
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

  public double getDonGia() {
    return donGia;
  }

  public void setDonGia(double donGia) {
    this.donGia = donGia;
  }

}
