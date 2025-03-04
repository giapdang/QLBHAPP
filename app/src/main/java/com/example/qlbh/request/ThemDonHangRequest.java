package com.example.qlbh.request;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ThemDonHangRequest {

  @SerializedName("user_id")
  private Long userId;

  @SerializedName("san_pham")
  private List<SanPhamDonHang> sanPhamList;

  public ThemDonHangRequest(Long userId, List<SanPhamDonHang> sanPhamList) {
    this.userId = userId;
    this.sanPhamList = sanPhamList;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public List<SanPhamDonHang> getSanPhamList() {
    return sanPhamList;
  }

  public void setSanPhamList(List<SanPhamDonHang> sanPhamList) {
    this.sanPhamList = sanPhamList;
  }

  public static class SanPhamDonHang {

    @SerializedName("gio_hang_id")
    private Long gioHangId;

    @SerializedName("san_pham_id")
    private Long sanPhamId;

    @SerializedName("so_luong")
    private int soLuong;

    @SerializedName("don_gia")
    private Double donGia;

    public SanPhamDonHang(Long gioHangId, Long sanPhamId, int soLuong, Double donGia) {
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

    public Double getDonGia() {
      return donGia;
    }

    public void setDonGia(Double donGia) {
      this.donGia = donGia;
    }
  }
}