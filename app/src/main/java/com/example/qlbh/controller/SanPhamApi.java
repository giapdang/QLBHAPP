package com.example.qlbh.controller;

import com.example.qlbh.response.ChiTietSanPham;
import com.example.qlbh.response.SanPhamResponse;
import com.example.qlbh.response.TimKiemSanPhamResponse;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface SanPhamApi {

  @GET("http://localhost:3000/san_pham/HienThiAllSanPham.php")
  Call<List<SanPhamResponse>> getAllSanPham();

  @GET("http://localhost:3000/san_pham/HienThiSanPhamTheoDanhMUc.php")
  Call<List<SanPhamResponse>> hienThiSanPhamTheoIdDanhMuc(@Query("id_danh_muc") String idDanhMuc);

  @GET("http://localhost:3000/san_pham/ChiTietSanPham.php")
  Call<ChiTietSanPham> chiTietSanPham(@Query("id") String idSanPham);

  @GET("http://localhost:3000/san_pham/TimKiemSanPham.php")
  Call<List<TimKiemSanPhamResponse>> timKiemSanPham(@Query("ten_san_pham") String tenSanPham);

  @Multipart
  @POST("http://localhost:3000/san_pham/ThemSanPham.php")
  Call<Void> themSanPham(
      @Part("danh_muc_id") RequestBody danhMucId,
      @Part("ten_san_pham") RequestBody tenSanPham,
      @Part("mo_ta") RequestBody moTa,
      @Part("gia") RequestBody gia,
      @Part("so_luong") RequestBody soLuong,
      @Part("hinh_anh") MultipartBody.Part hinhAnh
  );

  @Multipart
  @POST("http://localhost:3000/san_pham/UpdateSanPham.php")
  Call<Void> updateSanPham(
      @Part("id") RequestBody id,
      @Part("ten_san_pham") RequestBody tenSanPham,
      @Part("gia") RequestBody gia,
      @Part("so_luong") RequestBody soLuong,
      @Part("mo_ta") RequestBody moTa,
      @Part("danh_muc_id") RequestBody danhMucId,
      @Part("hinh_anh") MultipartBody.Part hinhAnh
  );
}
