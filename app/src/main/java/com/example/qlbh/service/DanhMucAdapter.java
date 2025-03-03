package com.example.qlbh.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qlbh.R;
import com.example.qlbh.response.DanhMucResponse;
import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.ViewHolder> {

    private List<DanhMucResponse> danhMucList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public DanhMucAdapter(Context context, List<DanhMucResponse> danhMucList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.danhMucList = danhMucList;
        this.onItemClickListener = onItemClickListener;
    }

    // tao view holder de hien thi danh muc san pham len recyclerview danh muc
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_danh_muc, parent, false);
        return new ViewHolder(view);
    }

    // hien thi danh muc san pham len recyclerview danh muc
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DanhMucResponse danhMuc = danhMucList.get(position);
        holder.tenDanhMuc.setText(danhMuc.getTenDanhMuc());
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(danhMuc));
    }

    @Override
    public int getItemCount() {
        return danhMucList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenDanhMuc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenDanhMuc = itemView.findViewById(R.id.tenDanhMuc);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(DanhMucResponse danhMuc);
    }
}