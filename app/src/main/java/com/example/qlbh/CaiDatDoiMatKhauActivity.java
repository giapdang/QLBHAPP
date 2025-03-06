package com.example.qlbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.controller.UserApi;
import com.example.qlbh.request.DoiMatKhauRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaiDatDoiMatKhauActivity extends AppCompatActivity {

  private EditText oldPassword, newPassword, confirmPassword;
  private Button btnDoiMatKhau;
  private ImageView back;
  private UserApi userApi;
  private Long userId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_caidatdoipassword);

    oldPassword = findViewById(R.id.passcu);
    newPassword = findViewById(R.id.pasmoi);
    confirmPassword = findViewById(R.id.nhaplaimkmoi);
    btnDoiMatKhau = findViewById(R.id.buttonresetpass);
    back = findViewById(R.id.quaylai);

    userApi = ApiClient.getRetrofitInstance().create(UserApi.class);

    // Lấy user_id từ SharedPreferences
    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    userId = sharedPreferences.getLong("user_id", -1);

    btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String oldPass = oldPassword.getText().toString();
        String newPass = newPassword.getText().toString();
        String confirmPass = confirmPassword.getText().toString();

        if (newPass.equals(confirmPass)) {
          DoiMatKhauRequest request = new DoiMatKhauRequest(userId, oldPass, newPass, confirmPass);
          doiMatKhau(request);
        } else {
          Toast.makeText(CaiDatDoiMatKhauActivity.this, "Mật khẩu mới không khớp",
              Toast.LENGTH_SHORT).show();
        }
      }
    });

    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(CaiDatDoiMatKhauActivity.this, CaiDatActivity.class);
        startActivity(intent);
        finish();
      }
    });
  }

  private void doiMatKhau(DoiMatKhauRequest request) {
    userApi.doiMatKhau(request).enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
          Toast.makeText(CaiDatDoiMatKhauActivity.this, "Đổi mật khẩu thành công",
              Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(CaiDatDoiMatKhauActivity.this, CaiDatActivity.class);
          startActivity(intent);
          finish();
        } else {
          Toast.makeText(CaiDatDoiMatKhauActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT)
              .show();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(CaiDatDoiMatKhauActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT)
            .show();
      }
    });
  }
}