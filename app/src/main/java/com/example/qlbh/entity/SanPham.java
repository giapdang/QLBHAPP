package com.example.qlbh.entity;

import com.google.gson.annotations.SerializedName;

public class SanPham {

  @SerializedName("ten_san_pham")
  private String tenSanPham;

  @SerializedName("gio_hang_id")
  private Long gioHangId;

  @SerializedName("san_pham_id")
  private Long sanPhamId;

  @SerializedName("so_luong")
  private int soLuong;

  @SerializedName("don_gia")
  private double donGia;

  @SerializedName("hinh_anh")
  private String hinhaAnh;

  public SanPham(String tenSanPham, Long gioHangId, Long sanPhamId, int soLuong, double donGia,
      String hinhaAnh) {
    this.tenSanPham = tenSanPham;
    this.gioHangId = gioHangId;
    this.sanPhamId = sanPhamId;
    this.soLuong = soLuong;
    this.donGia = donGia;
    this.hinhaAnh = hinhaAnh;
  }

  public String getTenSanPham() {
    return tenSanPham;
  }

  public void setTenSanPham(String tenSanPham) {
    this.tenSanPham = tenSanPham;
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

  public String getHinhaAnh() {
    return hinhaAnh;
  }

  public void setHinhaAnh(String hinhaAnh) {
    this.hinhaAnh = hinhaAnh;
  }
}
