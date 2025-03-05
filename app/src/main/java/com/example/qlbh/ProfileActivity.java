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
import com.example.qlbh.request.UpdateUserRequest;
import com.example.qlbh.response.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private EditText editTextHoTen, editTextEmail, editTextSoDienThoai, editTextDiaChi;
    private Button btnLuu, btnDong;
    private UserApi userApi;
    private Long userId;
    private ImageView imgHome, imgGioHang, imgCaiDat, imgDonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editTextHoTen = findViewById(R.id.editTextHoTen);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSoDienThoai = findViewById(R.id.editTextSoDienThoai);
        editTextDiaChi = findViewById(R.id.editTextDiaChi);
        btnLuu = findViewById(R.id.btnLuu);
        btnDong = findViewById(R.id.btnDong);

        imgHome = findViewById(R.id.imageView3);
        imgGioHang = findViewById(R.id.imageView2);
        imgCaiDat = findViewById(R.id.imageView5);
        imgDonHang = findViewById(R.id.imageView4);

        userApi = ApiClient.getRetrofitInstance().create(UserApi.class);

        // Lấy user_id từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        userId = sharedPreferences.getLong("user_id", -1);

        if (userId != -1) {
            fetchUserProfile(userId);
        } else {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
        }

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });

        imgDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DonHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchUserProfile(Long userId) {
        userApi.chiTietUser(userId).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserResponse user = response.body();
                    editTextHoTen.setText(user.getHoTen());
                    editTextEmail.setText(user.getEmail());
                    editTextSoDienThoai.setText(user.getSoDienThoai());
                    editTextDiaChi.setText(user.getDiaChi());
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to load user profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUserProfile() {
        String hoTen = editTextHoTen.getText().toString();
        String email = editTextEmail.getText().toString();
        String soDienThoai = editTextSoDienThoai.getText().toString();
        String diaChi = editTextDiaChi.getText().toString();

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(userId, hoTen, email, soDienThoai, diaChi);

        userApi.updateUser(updateUserRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}