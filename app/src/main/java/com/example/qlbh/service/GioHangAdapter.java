package com.example.qlbh.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.qlbh.R;
import com.example.qlbh.response.GioHangResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    private List<GioHangResponse> sanPhamList;
    private Context context;
    private OnDeleteItemClickListener onDeleteItemClickListener;
    private OnTotalPriceChangeListener onTotalPriceChangeListener;
    private Set<GioHangResponse> selectedItems = new HashSet<>();

    public GioHangAdapter(Context context, List<GioHangResponse> sanPhamList, OnDeleteItemClickListener onDeleteItemClickListener, OnTotalPriceChangeListener onTotalPriceChangeListener) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.onDeleteItemClickListener = onDeleteItemClickListener;
        this.onTotalPriceChangeListener = onTotalPriceChangeListener;
    }

    // tao view holder de hien thi san pham len recyclerview gio hang
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gio_hang, parent, false);
        return new ViewHolder(view);
    }

    // hien thi san pham len recyclerview gio hang
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHangResponse sanPham = sanPhamList.get(position);
        holder.tenSanPham.setText(sanPham.getTenSanPham());
        holder.gia.setText(String.format("%,.2f VND", sanPham.getGia()));
        holder.soLuong.setText(String.valueOf(sanPham.getSoLuong()));

        String imagePath = sanPham.getHinhAnh();
        if (imagePath.startsWith("../")) {
            imagePath = imagePath.substring(3);
        }
        String imageUrl = "http://10.0.2.2:3000/" + imagePath;

        Glide.with(context).load(imageUrl).into(holder.hinhAnh);

        holder.btnXoa.setOnClickListener(v -> onDeleteItemClickListener.onDeleteItemClick(sanPham));

        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedItems.add(sanPham);
            } else {
                selectedItems.remove(sanPham);
            }
            calculateTotalPrice();
        });
    }

    // dem so luong san pham trong gio hang
    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    // view holder de hien thi san pham len recyclerview gio hang
    private void calculateTotalPrice() {
        double totalPrice = 0;
        for (GioHangResponse sanPham : selectedItems) {
            totalPrice += sanPham.getGia() * sanPham.getSoLuong();
        }
        onTotalPriceChangeListener.onTotalPriceChange(totalPrice);
    }

    // lay danh sach cac san pham duoc chon
    public List<GioHangResponse> getSelectedItems() {
        return new ArrayList<>(selectedItems);
    }

    // view holder de hien thi san pham len recyclerview gio hang
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenSanPham, gia, soLuong;
        ImageView hinhAnh;
        Button btnXoa;
        CheckBox checkbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSanPham = itemView.findViewById(R.id.tenSanPham);
            gia = itemView.findViewById(R.id.gia);
            soLuong = itemView.findViewById(R.id.soLuong);
            hinhAnh = itemView.findViewById(R.id.imgSanPham);
            btnXoa = itemView.findViewById(R.id.btnXoa);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }

    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(GioHangResponse sanPham);
    }

    public interface OnTotalPriceChangeListener {
        void onTotalPriceChange(double totalPrice);
    }
}