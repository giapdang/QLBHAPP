package com.example.qlbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.controller.BaseApi;
import com.example.qlbh.request.LoginRequest;
import com.example.qlbh.response.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  private EditText email, password;
  private Button login;
  private TextView dangky, quenmk;
  private static final String TAG = "LoginActivity";
  private static final String PREFS_NAME = "MyPrefs";
  private static final String USER_ID_KEY = "user_id";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    email = findViewById(R.id.edtEmail);
    password = findViewById(R.id.edtPassword);

    login = findViewById(R.id.btnLogin);
    dangky = findViewById(R.id.textdangky);
    quenmk = findViewById(R.id.tvForgotPassword);

    login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        login();
      }
    });

    dangky.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
      }
    });

    quenmk.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, EmailXacThucActivity.class);
        startActivity(intent);
      }
    });
  }

  private void login() {
      String email = this.email.getText().toString();
      String password = this.password.getText().toString();

      if (email.isEmpty()) {
          Toast.makeText(this, "email không được để trống", Toast.LENGTH_SHORT).show();
          this.email.requestFocus();
          return;
      }

      if (password.isEmpty()) {
          Toast.makeText(this, "password không được để trống", Toast.LENGTH_SHORT).show();
          this.password.requestFocus();
          return;
      }

      LoginRequest loginRequest = new LoginRequest(email, password);
      BaseApi baseApi = ApiClient.getRetrofitInstance().create(BaseApi.class);
      Call<LoginResponse> call = baseApi.loginUser(loginRequest);

      call.enqueue(new Callback<LoginResponse>() {
          @Override
          public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
              if (response.isSuccessful() && response.body() != null && "Đăng nhập thành công.".equals(response.body().getSuccess())) {
                  Log.d(TAG, "Login successful");
                  Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                  // Save user_id to SharedPreferences
                  SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putLong(USER_ID_KEY, response.body().getUserId());
                  editor.apply();

                  Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                  startActivity(intent);
              } else {
                  Log.e(TAG, "Login failed: " + response.code());
                  Toast.makeText(LoginActivity.this, "Đăng nhập thất bại: " + response.message(), Toast.LENGTH_SHORT).show();
              }
          }

          @Override
          public void onFailure(Call<LoginResponse> call, Throwable t) {
              Log.e(TAG, "API connection error: " + t.getMessage(), t);
              Toast.makeText(LoginActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
          }
      });
  }
}