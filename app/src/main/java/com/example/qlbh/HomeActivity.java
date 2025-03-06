package com.example.qlbh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.controller.DanhMucApi;
import com.example.qlbh.controller.SanPhamApi;
import com.example.qlbh.response.DanhMucResponse;
import com.example.qlbh.response.SanPhamResponse;
import com.example.qlbh.response.TimKiemSanPhamResponse;
import com.example.qlbh.service.DanhMucAdapter;
import com.example.qlbh.service.SanPhamAdapter;
import com.example.qlbh.service.TimKiemSanPhamAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements DanhMucAdapter.OnItemClickListener {

    private RecyclerView recyclerViewDanhMuc, recyclerViewSanPham;
    private DanhMucAdapter danhMucAdapter;
    private SanPhamAdapter sanPhamAdapter;
    private TimKiemSanPhamAdapter timKiemSanPhamAdapter;
    private EditText edtSearch;
    private ImageView btnSearch, imgGioHang, imgDonHang, imgUser, imgCaiDat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewDanhMuc = findViewById(R.id.recyclerViewDanhMuc);
        recyclerViewSanPham = findViewById(R.id.recyclerViewSanPham);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        recyclerViewDanhMuc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSanPham.setLayoutManager(new GridLayoutManager(this, 2));

        fetchDanhMuc();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = edtSearch.getText().toString().trim();
                if (!query.isEmpty()) {
                    searchProducts(query);
                } else {
                    Toast.makeText(HomeActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgGioHang = findViewById(R.id.imageView2);
        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, GioHangActivity.class));
            }
        });

        imgDonHang = findViewById(R.id.imageView4);
        imgDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, DonHangActivity.class));
            }
        });

        imgUser = findViewById(R.id.imageView);
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // chuyen sang cai dat
        imgCaiDat = findViewById(R.id.imageView5);
        imgCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CaiDatActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchDanhMuc() {
        DanhMucApi danhMucApi = ApiClient.getRetrofitInstance().create(DanhMucApi.class);
        Call<List<DanhMucResponse>> call = danhMucApi.hienThiAllDanhMuc();

        call.enqueue(new Callback<List<DanhMucResponse>>() {
            @Override
            public void onResponse(Call<List<DanhMucResponse>> call, Response<List<DanhMucResponse>> response) {
                if (response.isSuccessful()) {
                    List<DanhMucResponse> danhMucList = response.body();
                    danhMucAdapter = new DanhMucAdapter(HomeActivity.this, danhMucList, HomeActivity.this);
                    recyclerViewDanhMuc.setAdapter(danhMucAdapter);
                    if (danhMucList != null && !danhMucList.isEmpty()) {
                        fetchSanPham(danhMucList.get(0).getId());
                    }
                } else {
                    Toast.makeText(HomeActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DanhMucResponse>> call, Throwable t) {
                Log.e("HomeActivity", "API call failed", t);
                Toast.makeText(HomeActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchSanPham(Long danhMucId) {
        SanPhamApi sanPhamApi = ApiClient.getRetrofitInstance().create(SanPhamApi.class);
        Call<List<SanPhamResponse>> call = sanPhamApi.hienThiSanPhamTheoIdDanhMuc(String.valueOf(danhMucId));

        call.enqueue(new Callback<List<SanPhamResponse>>() {
            @Override
            public void onResponse(Call<List<SanPhamResponse>> call, Response<List<SanPhamResponse>> response) {
                if (response.isSuccessful()) {
                    List<SanPhamResponse> sanPhamList = response.body();
                    sanPhamAdapter = new SanPhamAdapter(HomeActivity.this, sanPhamList);
                    recyclerViewSanPham.setAdapter(sanPhamAdapter);
                } else {
                    Toast.makeText(HomeActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SanPhamResponse>> call, Throwable t) {
                Log.e("HomeActivity", "API call failed", t);
                Toast.makeText(HomeActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchProducts(String query) {
        SanPhamApi baseApi = ApiClient.getRetrofitInstance().create(SanPhamApi.class);
        Call<List<TimKiemSanPhamResponse>> call = baseApi.timKiemSanPham(query);

        call.enqueue(new Callback<List<TimKiemSanPhamResponse>>() {
            @Override
            public void onResponse(Call<List<TimKiemSanPhamResponse>> call, Response<List<TimKiemSanPhamResponse>> response) {
                if (response.isSuccessful()) {
                    List<TimKiemSanPhamResponse> searchResults = response.body();
                    timKiemSanPhamAdapter = new TimKiemSanPhamAdapter(HomeActivity.this, searchResults);
                    recyclerViewSanPham.setAdapter(timKiemSanPhamAdapter);
                } else {
                    Toast.makeText(HomeActivity.this, "Failed to fetch search results", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TimKiemSanPhamResponse>> call, Throwable t) {
                Log.e("HomeActivity", "API call failed", t);
                Toast.makeText(HomeActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // nhan vao tab bar imageView 2 thi chuyen sang gio hang cua 1 user


    @Override
    public void onItemClick(DanhMucResponse danhMuc) {
        fetchSanPham(danhMuc.getId());
    }
}