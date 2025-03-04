package com.example.qlbh;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.controller.ThanhToan;
import com.example.qlbh.response.DonHangResponse;
import com.example.qlbh.service.DonHangAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangActivity extends AppCompatActivity {

  private RecyclerView recyclerViewDonHang;
  private DonHangAdapter donHangAdapter;
  private static final String TAG = "DonHangActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_donhang);

    recyclerViewDonHang = findViewById(R.id.recyclerViewDonHang);
    recyclerViewDonHang.setLayoutManager(new LinearLayoutManager(this));

    // lay user_id tu SharedPreferences
    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    Long userId = sharedPreferences.getLong("user_id", -1);
    if (userId != -1) {
      fetchDonHang(userId);
    } else {
      Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
    }
  }

  // Lay danh sach don hang tu API
  private void fetchDonHang(Long userId) {
    ThanhToan thanhToanApi = ApiClient.getRetrofitInstance().create(ThanhToan.class);
    Call<List<DonHangResponse>> call = thanhToanApi.hienThiDonHang(userId);

    call.enqueue(new Callback<List<DonHangResponse>>() {
      @Override
      public void onResponse(Call<List<DonHangResponse>> call, Response<List<DonHangResponse>> response) {
        if (response.isSuccessful()) {
          List<DonHangResponse> donHangList = response.body();
          donHangAdapter = new DonHangAdapter(DonHangActivity.this, donHangList);
          recyclerViewDonHang.setAdapter(donHangAdapter);
        } else {
          Toast.makeText(DonHangActivity.this, "Failed to fetch orders", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<List<DonHangResponse>> call, Throwable t) {
        Log.e(TAG, "API call failed", t);
        Toast.makeText(DonHangActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
      }
    });
  }
}