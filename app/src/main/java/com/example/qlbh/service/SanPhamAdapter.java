package com.example.qlbh.service;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.qlbh.R;
import com.example.qlbh.response.SanPhamResponse;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    private List<SanPhamResponse> sanPhamList;
    private Context context;
    private static final String TAG = "SanPhamAdapter";

    public SanPhamAdapter(Context context, List<SanPhamResponse> sanPhamList) {
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
        SanPhamResponse sanPham = sanPhamList.get(position);

        holder.tenSanPham.setText(sanPham.getTenSanPham());
        holder.gia.setText(String.format("%,d VND", sanPham.getGia()));

        // Xử lý đường dẫn ảnh để loại bỏ "../"
        String imagePath = sanPham.getHinhAnh();
        if (imagePath.startsWith("../")) {
            imagePath = imagePath.substring(3); // Cắt bỏ "../"
        }

        // Đường dẫn đầy đủ của ảnh
        String imageUrl = "http://10.0.2.2:3000/" + imagePath;

        // Log the image URL
        Log.d(TAG, "Image URL: " + imageUrl);

        Glide.with(context).load(imageUrl).into(holder.imgSanPham);
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

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