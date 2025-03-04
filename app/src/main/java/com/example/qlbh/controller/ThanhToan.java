package com.example.qlbh.controller;

import com.example.qlbh.request.ThemDonHangRequest;
import com.example.qlbh.request.XacThucDonHangRequest;
import com.example.qlbh.response.ChiTietDonHangResponse;
import com.example.qlbh.response.DonHangResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ThanhToan {

  @POST("thanh_toan/ThemDonHang.php")
  Call<Void> themDonHang(@Body ThemDonHangRequest themDonHangRequest);

  @GET("thanh_toan/HienThiChiTietDonHang.php")
  Call<List<ChiTietDonHangResponse>> hienThiChiTietDonHang(@Query("don_hang_id") Long donHangId);

  @GET("thanh_toan/HienThiDonHang.php")
  Call<List<DonHangResponse>> hienThiDonHang(@Query("user_id") Long userId);

  @PUT("thanh_toan/XacThucDonHang.php")
  Call<Void> xacThucDonHang(@Body XacThucDonHangRequest xacThucDonHangRequest);
}
