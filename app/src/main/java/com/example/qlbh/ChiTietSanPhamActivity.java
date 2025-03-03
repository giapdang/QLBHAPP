package com.example.qlbh;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.controller.SanPhamApi;
import com.example.qlbh.response.ChiTietSanPham;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    private static final String TAG = "ChiTietSanPhamActivity";
    private ImageView imgSanPham;
    private TextView tenSanPham, giaSanPham, moTaSanPham;
    private EditText soLuong;
    private Button btnThemVaoGioHang, btnTang, btnGiam;

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
        String idSanPham = getIntent().getStringExtra("idSanPham");
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
}