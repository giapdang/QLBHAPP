package com.example.qlbh.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.qlbh.R;
import com.example.qlbh.response.TimKiemSanPhamResponse;
import java.util.List;

public class TimKiemSanPhamAdapter extends RecyclerView.Adapter<TimKiemSanPhamAdapter.ViewHolder> {

    private List<TimKiemSanPhamResponse> sanPhamList;
    private Context context;

    public TimKiemSanPhamAdapter(Context context, List<TimKiemSanPhamResponse> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimKiemSanPhamResponse sanPham = sanPhamList.get(position);
        holder.tenSanPham.setText(sanPham.getTenSanPham());
        holder.gia.setText(String.format("%s VND", sanPham.getGia()));

        // Xử lý đường dẫn ảnh để loại bỏ "../"
        String imagePath = sanPham.getHinhAnh();
        if (imagePath.startsWith("../")) {
            imagePath = imagePath.substring(3); // Cắt bỏ "../"
        }

        // Đường dẫn đầy đủ của ảnh
        String imageUrl = "http://10.0.2.2:3000/" + imagePath;

        // Load image using Glide
        Glide.with(context).load(imageUrl).into(holder.hinhAnh);
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenSanPham , gia;
        ImageView hinhAnh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSanPham = itemView.findViewById(R.id.tenSanPham);
            gia = itemView.findViewById(R.id.gia);
            hinhAnh = itemView.findViewById(R.id.imgSanPham);
        }
    }
}