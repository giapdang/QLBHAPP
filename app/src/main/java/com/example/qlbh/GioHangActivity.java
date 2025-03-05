package com.example.qlbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.controller.GioHangApi;
import com.example.qlbh.controller.ThanhToan;
import com.example.qlbh.request.ThemDonHangRequest;
import com.example.qlbh.response.GioHangResponse;
import com.example.qlbh.service.GioHangAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangActivity extends AppCompatActivity implements
    GioHangAdapter.OnDeleteItemClickListener, GioHangAdapter.OnTotalPriceChangeListener {

  private RecyclerView recyclerViewGioHang;
  private GioHangAdapter gioHangAdapter;
  private List<GioHangResponse> gioHangList;
  private TextView totalPriceTextView;
  private Button btnThanhToan;
  private static final String TAG = "GioHangActivity";
  private ImageView imgHome, imgUser,imgDonHang,imgCaiDat;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_giohang);

    recyclerViewGioHang = findViewById(R.id.recyclerViewGioHang);
    totalPriceTextView = findViewById(R.id.totalPriceTextView);
    btnThanhToan = findViewById(R.id.btnThanhToan);
    recyclerViewGioHang.setLayoutManager(new LinearLayoutManager(this));

    // Get user_id from SharedPreferences
    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    Long userId = sharedPreferences.getLong("user_id", -1);
    if (userId != -1) {
      fetchGioHang(userId);
    } else {
      Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
    }

    btnThanhToan.setOnClickListener(v -> {
      List<GioHangResponse> selectedItems = gioHangAdapter.getSelectedItems();
      if (!selectedItems.isEmpty()) {
        createOrder(userId, selectedItems);
      } else {
        Toast.makeText(this, "No items selected", Toast.LENGTH_SHORT).show();
      }
    });

    // chuyển qua home
    imgHome = findViewById(R.id.imageView3);
    imgHome.setOnClickListener(v -> {
      Intent intent = new Intent(GioHangActivity.this, HomeActivity.class);
      startActivity(intent);
    });

    // chuyen qua don hang
    imgDonHang = findViewById(R.id.imageView4);
    imgDonHang.setOnClickListener(v -> {
      Intent intent = new Intent(GioHangActivity.this, DonHangActivity.class);
      startActivity(intent);
    });

    // chuyen qua user
    imgUser = findViewById(R.id.imageView);
    imgUser.setOnClickListener(v -> {
      Intent intent = new Intent(GioHangActivity.this, ProfileActivity.class);
      startActivity(intent);
    });
  }

  private void fetchGioHang(Long userId) {
    GioHangApi gioHangApi = ApiClient.getRetrofitInstance().create(GioHangApi.class);
    Call<List<GioHangResponse>> call = gioHangApi.hienThiGioHang(userId);

    call.enqueue(new Callback<List<GioHangResponse>>() {
      @Override
      public void onResponse(Call<List<GioHangResponse>> call,
          Response<List<GioHangResponse>> response) {
        if (response.isSuccessful()) {
          gioHangList = response.body();
          gioHangAdapter = new GioHangAdapter(GioHangActivity.this, gioHangList,
              GioHangActivity.this, GioHangActivity.this);
          recyclerViewGioHang.setAdapter(gioHangAdapter);
        } else {
          Toast.makeText(GioHangActivity.this, "Failed to fetch cart items", Toast.LENGTH_SHORT)
              .show();
        }
      }

      @Override
      public void onFailure(Call<List<GioHangResponse>> call, Throwable t) {
        Log.e(TAG, "API call failed", t);
        Toast.makeText(GioHangActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onDeleteItemClick(GioHangResponse sanPham) {
    // xóa sản phẩm khỏi giỏ hàng
    gioHangList.remove(sanPham);
    gioHangAdapter.notifyDataSetChanged();
    // lay user_id tu SharedPreferences
    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    Long userId = sharedPreferences.getLong("user_id", -1);
    if (userId != -1) {
      xoaSanPhamKhoiGioHang(sanPham.getId(), userId);
    } else {
      Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onTotalPriceChange(double totalPrice) {
    totalPriceTextView.setText(String.format("%,.2f VND", totalPrice));
  }

  // gọi API để xóa sản phẩm khỏi giỏ hàng
  private void xoaSanPhamKhoiGioHang(Long gioHangId, Long userId) {
    GioHangApi gioHangApi = ApiClient.getRetrofitInstance().create(GioHangApi.class);

    Call<Void> call = gioHangApi.xoaGioHang(gioHangId, userId);

    call.enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
//          Toast.makeText(GioHangActivity.this, "Đã xóa sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT)
//              .show();
          // load lại danh sách giỏ hàng
          fetchGioHang(userId);
        } else {
          Toast.makeText(GioHangActivity.this, "Failed to delete item from cart",
              Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        Log.e(TAG, "API call failed", t);
        Toast.makeText(GioHangActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
      }
    });
  }

  // gọi API để tạo đơn hàng
  private void createOrder(Long userId, List<GioHangResponse> selectedItems) {
    // chuyển đổi danh sách sản phẩm đã chọn thành danh sách sản phẩm để tạo đơn hàng
    List<ThemDonHangRequest.SanPhamDonHang> sanPhamList = new ArrayList<>();

    for (GioHangResponse item : selectedItems) {
      ThemDonHangRequest.SanPhamDonHang sanPham = new ThemDonHangRequest.SanPhamDonHang(
          item.getId(), item.getIdSanPham(), item.getSoLuong(), item.getGia());
      sanPhamList.add(sanPham);
    }

    // tạo request để gửi lên server
    ThemDonHangRequest request = new ThemDonHangRequest(userId, sanPhamList);
    ThanhToan thanhToanApi = ApiClient.getRetrofitInstance().create(ThanhToan.class);

    Call<Void> call = thanhToanApi.themDonHang(request);

    call.enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
//          Toast.makeText(GioHangActivity.this, "Order created successfully", Toast.LENGTH_SHORT)
//              .show();
          // load lại danh sách giỏ hàng
          fetchGioHang(userId);
          // reset total price
          totalPriceTextView.setText("0 VND");
          // xoá danh sách sản phẩm đã chọn
          selectedItems.clear();
          // qua form qr code
          Intent intent = new Intent(GioHangActivity.this, QrActivity.class);
          startActivity(intent);
        } else {
          Toast.makeText(GioHangActivity.this, "Failed to create order", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        Log.e(TAG, "API call failed", t);
        Toast.makeText(GioHangActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
      }
    });
  }
}