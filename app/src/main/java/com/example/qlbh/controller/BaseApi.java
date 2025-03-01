package com.example.qlbh.controller;

import com.example.qlbh.request.ForgetPasswordRequest;
import com.example.qlbh.request.LoginRequest;
import com.example.qlbh.request.RegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BaseApi {

  @POST("http://localhost:3000/register/Register.php")
  Call<Void> registerUser(@Body RegisterRequest registerRequest);

  @POST("http://localhost:3000/login/Login.php")
  Call<Void> loginUser(@Body LoginRequest loginRequest);

  @POST("http://localhost:3000/fogetpass/checkMail.php")
  Call<Void> checkEmail(@Body String email);

  @POST("http://localhost:3000/fogetpass/verifyOtp.php")
  Call<Void> verifyOtp(@Body String otp);

  @POST("http://localhost:3000/fogetpass/forgetPassword.php")
  Call<Void> forgetPassword(@Body ForgetPasswordRequest forgetPasswordRequest);

}
