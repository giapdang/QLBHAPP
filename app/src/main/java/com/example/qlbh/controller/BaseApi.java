package com.example.qlbh.controller;

import com.example.qlbh.request.ForgetPasswordRequest;
import com.example.qlbh.request.LoginRequest;
import com.example.qlbh.request.RegisterRequest;
import com.example.qlbh.request.VerifyOtpRequest;
import com.example.qlbh.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface BaseApi {

  @POST("register/Register.php")
  Call<Void> registerUser(@Body RegisterRequest registerRequest);

  @POST("login/Login.php")
  Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

  @GET("fogetpass/checkMail.php")
  Call<Void> checkEmail(@Query("email") String email);

  @PUT("fogetpass/verifyOtp.php")
  Call<Void> verifyOtp(@Body VerifyOtpRequest verifyOtpRequest);

  @PUT("fogetpass/forgetPassword.php")
  Call<Void> forgetPassword(@Body ForgetPasswordRequest forgetPasswordRequest);

}
