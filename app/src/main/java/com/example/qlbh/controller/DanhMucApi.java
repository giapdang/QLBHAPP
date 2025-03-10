package com.example.qlbh.controller;

import com.example.qlbh.request.ThemDanhMucRequest;
import com.example.qlbh.request.UpdateDanhMucRequest;
import com.example.qlbh.response.DanhMucChiTietResponse;
import com.example.qlbh.response.DanhMucResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface DanhMucApi {

  @POST("danh_muc/ThemDanhMuc.php")
  Call<Void> themDanhMuc(@Body ThemDanhMucRequest themDanhMucRequest);

  @GET("danh_muc/HienThiAllDanhMuc.php")
  Call<List<DanhMucResponse>> hienThiAllDanhMuc();

  @GET("danh_muc/ChiTietDanhMuc.php")
  Call<DanhMucChiTietResponse> chiTietDanhMuc(@Query("id") Long id);

  @PUT("danh_muc/UpdateDanhMuc.php")
  Call<Void> updateDanhMuc(@Body UpdateDanhMucRequest updateDanhMucRequest);

}
