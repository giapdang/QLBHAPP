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
import com.example.qlbh.response.ChiTietDonHangResponse;
import java.util.List;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.ChiTietDonHangViewHolder> {

    private List<ChiTietDonHangResponse> chiTietDonHangList;
    private Context context;

    public ChiTietDonHangAdapter(List<ChiTietDonHangResponse> chiTietDonHangList, Context context) {
        this.chiTietDonHangList = chiTietDonHangList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChiTietDonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet_donhang, parent, false);
        return new ChiTietDonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietDonHangViewHolder holder, int position) {
        ChiTietDonHangResponse chiTietDonHang = chiTietDonHangList.get(position);
        holder.tvTenSanPham.setText(chiTietDonHang.getTenSanPham());
        holder.tvGia.setText(String.format("%,.2f VND", chiTietDonHang.getGia()));
        holder.tvSoLuong.setText(String.valueOf(chiTietDonHang.getSoLuong()));
        holder.tvThanhTien.setText(String.valueOf(chiTietDonHang.getThanhTien()));

        String imagePath = chiTietDonHang.getHinhAnh();
        if (imagePath.startsWith("../")) {
            imagePath = imagePath.substring(3);
        }
        String imageUrl = "http://10.0.2.2:3000/" + imagePath;

        Glide.with(context).load(imageUrl).into(holder.imgSanPham);
    }

    @Override
    public int getItemCount() {
        return chiTietDonHangList.size();
    }

    public static class ChiTietDonHangViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham, tvGia, tvSoLuong, tvThanhTien;
        ImageView imgSanPham;

        public ChiTietDonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.tenSanPham);
            tvGia = itemView.findViewById(R.id.gia);
            tvSoLuong = itemView.findViewById(R.id.soLuong);
            tvThanhTien = itemView.findViewById(R.id.tvThanhTien);
            imgSanPham = itemView.findViewById(R.id.imgSanPham);
        }
    }
}