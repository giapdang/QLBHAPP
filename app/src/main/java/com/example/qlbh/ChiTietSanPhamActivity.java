// ChiTietSanPhamActivity.java
package com.example.qlbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.controller.GioHangApi;
import com.example.qlbh.controller.SanPhamApi;
import com.example.qlbh.request.ThemSanPhamGioHangRequest;
import com.example.qlbh.response.ChiTietSanPham;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    private static final String TAG = "ChiTietSanPhamActivity";
    private ImageView imgSanPham , imgGioHang, imgDonHang, imgUser, imgCaiDat , imgHome;
    private TextView tenSanPham, giaSanPham, moTaSanPham;
    private EditText soLuong;
    private Button btnThemVaoGioHang, btnTang, btnGiam;
    private String idSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);

        imgSanPham = findViewById(R.id.imgSanPham);
        tenSanPham = findViewById(R.id.tenSanPham);
        giaSanPham = findViewById(R.id.giaSanPham);
        moTaSanPham = findViewById(R.id.moTaSanPham);
        soLuong = findViewById(R.id.soLuong);
        btnThemVaoGioHang = findViewById(R.id.btnThemVaoGioHang);
        btnTang = findViewById(R.id.btnTang);
        btnGiam = findViewById(R.id.btnGiam);

        // lấy id sản phẩm từ intent và gọi API để lấy thông tin chi tiết sản phẩm
        idSanPham = getIntent().getStringExtra("idSanPham");
        if (idSanPham != null) {
            fetchChiTietSanPham(idSanPham);
        }

        // tang so luong san pham
        btnTang.setOnClickListener(v -> {
            int soLuongSanPham = Integer.parseInt(soLuong.getText().toString());
            soLuongSanPham++;
            soLuong.setText(String.valueOf(soLuongSanPham));
        });

        // giam so luong san pham
        btnGiam.setOnClickListener(v -> {
            int soLuongSanPham = Integer.parseInt(soLuong.getText().toString());
            if (soLuongSanPham > 1) {
                soLuongSanPham--;
                soLuong.setText(String.valueOf(soLuongSanPham));
            }
        });

        // thêm sản phẩm vào giỏ hàng
        btnThemVaoGioHang.setOnClickListener(v -> {
            int soLuongSanPham = Integer.parseInt(soLuong.getText().toString());
            // lay user_id tu SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            Long userId = sharedPreferences.getLong("user_id", -1);
            if (userId != -1) {
                themSanPhamVaoGioHang(userId, Long.parseLong(idSanPham), soLuongSanPham);
            } else {
                Toast.makeText(ChiTietSanPhamActivity.this, "User ID not found", Toast.LENGTH_SHORT).show();
            }
        });

        // chuyen sang gio hang
        imgGioHang = findViewById(R.id.imageViewCart);
        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });

        // chuyen sang don hang
        imgDonHang = findViewById(R.id.imageViewHistory);
        imgDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, DonHangActivity.class);
                startActivity(intent);
            }
        });

        // chuyen ve trang chu
        imgHome = findViewById(R.id.imageViewHome);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // chuyen sang trang ca nhan
        imgUser = findViewById(R.id.imageViewProfile);
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPhamActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    // gọi API để lấy thông tin chi tiết sản phẩm
    private void fetchChiTietSanPham(String idSanPham) {
        SanPhamApi sanPhamApi = ApiClient.getRetrofitInstance().create(SanPhamApi.class);
        Call<ChiTietSanPham> call = sanPhamApi.chiTietSanPham(idSanPham);

        call.enqueue(new Callback<ChiTietSanPham>() {
            @Override
            public void onResponse(Call<ChiTietSanPham> call, Response<ChiTietSanPham> response) {
                if (response.isSuccessful()) {
                    ChiTietSanPham chiTietSanPham = response.body();

                    // hiển thị thông tin chi tiết sản phẩm lên giao diện
                    if (chiTietSanPham != null) {
                        updateUI(chiTietSanPham);
                    }
                } else {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Failed to fetch product details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChiTietSanPham> call, Throwable t) {
                Log.e(TAG, "API call failed", t);
                Toast.makeText(ChiTietSanPhamActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // hiển thị thông tin chi tiết sản phẩm lên giao diện
    private void updateUI(ChiTietSanPham chiTietSanPham) {
        tenSanPham.setText(chiTietSanPham.getTenSanPham());
        giaSanPham.setText(String.format("%,d VND", chiTietSanPham.getGia()));
        moTaSanPham.setText(chiTietSanPham.getMoTa());

        String imagePath = chiTietSanPham.getHinhAnh();
        if (imagePath.startsWith("../")) {
            imagePath = imagePath.substring(3);
        }
        String imageUrl = "http://10.0.2.2:3000/" + imagePath;
        Glide.with(this).load(imageUrl).into(imgSanPham);
    }

    // thêm sản phẩm vào giỏ hàng
    private void themSanPhamVaoGioHang(Long userId, Long idSanPham, int soLuong) {
        GioHangApi gioHangApi = ApiClient.getRetrofitInstance().create(GioHangApi.class);
        ThemSanPhamGioHangRequest request = new ThemSanPhamGioHangRequest(userId, idSanPham, soLuong);

        Call<Void> call = gioHangApi.themSanPhamGioHang(request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();

                    // chuyển sang gio hang
                    Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "API call failed", t);
                Toast.makeText(ChiTietSanPhamActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}