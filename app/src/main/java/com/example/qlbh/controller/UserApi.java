package com.example.qlbh.controller;

import com.example.qlbh.request.DoiMatKhauRequest;
import com.example.qlbh.request.UpdateUserRequest;
import com.example.qlbh.response.UserResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserApi {

  @GET("http://localhost:3000/user/ChiTietUser.php")
  Call<UserResponse> chiTietUser(@Query("id") String id);

  @GET("http://localhost:3000/user/HienThiUser.php")
  Call<List<UserResponse>> getAllUser();

  @PUT("http://localhost:3000/user/DoiMatKhau.php")
  Call<Void> doiMatKhau(@Body DoiMatKhauRequest doiMatKhauRequest);

  @PUT("http://localhost:3000/user/UpdateUser.php")
  Call<Void> updateUser(@Body UpdateUserRequest updateUserRequest);
}
