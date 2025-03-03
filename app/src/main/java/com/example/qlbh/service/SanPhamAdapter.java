package com.example.qlbh.service;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.qlbh.ChiTietSanPhamActivity;
import com.example.qlbh.R;
import com.example.qlbh.response.SanPhamResponse;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    private List<SanPhamResponse> sanPhamList;
    private Context context;

    public SanPhamAdapter(Context context, List<SanPhamResponse> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    // tao view holder de hien thi san pham len recyclerview san pham
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
        return new ViewHolder(view);
    }

    // hien thi san pham len recyclerview san pham
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPhamResponse sanPham = sanPhamList.get(position);

        holder.tenSanPham.setText(sanPham.getTenSanPham());
        holder.gia.setText(String.format("%,d VND", sanPham.getGia()));

        String imagePath = sanPham.getHinhAnh();
        if (imagePath.startsWith("../")) {
            imagePath = imagePath.substring(3);
        }
        String imageUrl = "http://10.0.2.2:3000/" + imagePath;

        Glide.with(context).load(imageUrl).into(holder.imgSanPham);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
            intent.putExtra("idSanPham", String.valueOf(sanPham.getId()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    // view holder de hien thi san pham len recyclerview san pham
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenSanPham, gia;
        ImageView imgSanPham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSanPham = itemView.findViewById(R.id.tenSanPham);
            gia = itemView.findViewById(R.id.gia);
            imgSanPham = itemView.findViewById(R.id.imgSanPham);
        }
    }
}