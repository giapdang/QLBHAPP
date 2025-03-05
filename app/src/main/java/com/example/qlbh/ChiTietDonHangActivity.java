
package com.example.qlbh;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.response.ChiTietDonHangResponse;
import com.example.qlbh.service.ChiTietDonHangAdapter;
import com.example.qlbh.controller.ThanhToan;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDonHangActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChiTietDonHang;
    private ChiTietDonHangAdapter chiTietDonHangAdapter;
    private Button btdong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietdonhang);

        recyclerViewChiTietDonHang = findViewById(R.id.recyclerViewChiTietDonHang);
        recyclerViewChiTietDonHang.setLayoutManager(new LinearLayoutManager(this));

        Long donHangId = getIntent().getLongExtra("don_hang_id", -1);
        if (donHangId != -1) {
            fetchChiTietDonHang(donHangId);
        } else {
            Toast.makeText(this, "Order ID not found", Toast.LENGTH_SHORT).show();
        }

        btdong = findViewById(R.id.btnDong);
        btdong.setOnClickListener(v -> {
            finish();
        });
    }

    private void fetchChiTietDonHang(Long donHangId) {
        ThanhToan thanhToan = ApiClient.getRetrofitInstance().create(ThanhToan.class);
        thanhToan.hienThiChiTietDonHang(donHangId).enqueue(new Callback<List<ChiTietDonHangResponse>>() {
            @Override
            public void onResponse(Call<List<ChiTietDonHangResponse>> call, Response<List<ChiTietDonHangResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    chiTietDonHangAdapter = new ChiTietDonHangAdapter(response.body(), ChiTietDonHangActivity.this);
                    recyclerViewChiTietDonHang.setAdapter(chiTietDonHangAdapter);
                } else {
                    Toast.makeText(ChiTietDonHangActivity.this, "Failed to load order details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ChiTietDonHangResponse>> call, Throwable t) {
                Toast.makeText(ChiTietDonHangActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}