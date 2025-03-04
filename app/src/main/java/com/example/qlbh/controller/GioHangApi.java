package com.example.qlbh.controller;

import com.example.qlbh.request.ThemSanPhamGioHangRequest;
import com.example.qlbh.response.GioHangResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GioHangApi {

  @POST("gio_hang/ThemSanPhamGioHang.php")
  Call<Void> themSanPhamGioHang(@Body ThemSanPhamGioHangRequest themSanPhamGioHangRequest);

  @GET("gio_hang/HienThiGioHang.php")
  Call<List<GioHangResponse>> hienThiGioHang(@Query("user_id") Long userId);

  @DELETE("gio_hang/XoaGioHang.php")
  Call<Void> xoaGioHang(@Query("gio_hang_id") Long gioHangId, @Query("user_id") Long userId);
}
