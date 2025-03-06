package com.example.qlbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.controller.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaiDatActivity extends AppCompatActivity {

    private Button btnXoaTaiKhoan,btndoiMatKhau;
    private ImageView imgHome, imgGioHang, imgDonHang, imgUser;
    private UserApi userApi;
    private Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);

        btnXoaTaiKhoan = findViewById(R.id.btnresetpass2);
        btndoiMatKhau = findViewById(R.id.btnresetpass);
        imgHome = findViewById(R.id.imageViewHome);
        imgGioHang = findViewById(R.id.imageViewCart);
        imgDonHang = findViewById(R.id.imageViewHistory);
        imgUser = findViewById(R.id.imageViewProfile);

        userApi = ApiClient.getRetrofitInstance().create(UserApi.class);

        // Lấy user_id từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        userId = sharedPreferences.getLong("user_id", -1);

        btnXoaTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId != -1) {
                    deleteUser(userId);
                } else {
                    Toast.makeText(CaiDatActivity.this, "User ID not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDatActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDatActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });

        imgDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDatActivity.this, DonHangActivity.class);
                startActivity(intent);
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDatActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btndoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDatActivity.this, CaiDatDoiMatKhauActivity.class);
                startActivity(intent);
            }
        });

    }

    private void deleteUser(Long userId) {
        userApi.deleteUser(userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Xóa thông tin người dùng khỏi SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();

                    // Xóa thông tin người dùng khỏi SharedPreferences
                    SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                    editor1.clear();

                    // hien thi thong bao
                    Toast.makeText(CaiDatActivity.this, "Xóa tài khoản thành công", Toast.LENGTH_SHORT).show();

                    // Chuyển hướng đến MainActivity
                    Intent intent = new Intent(CaiDatActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CaiDatActivity.this, "Failed to delete account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CaiDatActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}