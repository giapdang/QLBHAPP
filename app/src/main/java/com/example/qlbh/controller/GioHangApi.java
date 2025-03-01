package com.example.qlbh.controller;

import com.example.qlbh.request.ThemSanPhamGioHangRequest;
import com.example.qlbh.request.XoaGioHangRequest;
import com.example.qlbh.response.GioHangResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GioHangApi {

  @POST("gio_hang/ThemSanPhamGioHang.php")
  Call<Void> themSanPhamGioHang(@Body ThemSanPhamGioHangRequest themSanPhamGioHangRequest);

  @GET("gio_hang/HienThiGioHang.php")
  Call<List<GioHangResponse>> hienThiGioHang();

  @DELETE("gio_hang/XoaGioHang.php")
  Call<Void> xoaGioHang(@Body XoaGioHangRequest xoaGioHangRequest);
}
