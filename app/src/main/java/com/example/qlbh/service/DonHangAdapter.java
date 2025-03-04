package com.example.qlbh.service;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qlbh.ChiTietDonHangActivity;
import com.example.qlbh.R;
import com.example.qlbh.response.DonHangResponse;
import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolder> {

  private Context context;
  private List<DonHangResponse> donHangList;

  public DonHangAdapter(Context context, List<DonHangResponse> donHangList) {
    this.context = context;
    this.donHangList = donHangList;
  }

  @NonNull
  @Override
  public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_donhang, parent, false);
    return new DonHangViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {
    DonHangResponse donHang = donHangList.get(position);
    holder.tvSoThuTu.setText(String.valueOf(position + 1));
    holder.tvTongTien.setText(String.format("%,.2f VND", Double.parseDouble(donHang.getTongTien())));    holder.tvNgayTao.setText(donHang.getNgayTao());

    // click vao don hang de xem chi tiet don hang lay id don hang
    holder.btnXemChiTiet.setOnClickListener(v -> {
      Intent intent = new Intent(context, ChiTietDonHangActivity.class);
      intent.putExtra("don_hang_id", donHang.getId());
      context.startActivity(intent);
    });
  }

  @Override
  public int getItemCount() {
    return donHangList.size();
  }

  public static class DonHangViewHolder extends RecyclerView.ViewHolder {

    TextView tvSoThuTu, tvTongTien, tvTrangThai, tvNgayTao;
    Button btnXemChiTiet;

    public DonHangViewHolder(@NonNull View itemView) {
      super(itemView);
      tvSoThuTu = itemView.findViewById(R.id.tvSoThuTu);
      tvTongTien = itemView.findViewById(R.id.tvTongTien);
      tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
      tvNgayTao = itemView.findViewById(R.id.tvNgayTao);
      btnXemChiTiet = itemView.findViewById(R.id.btnXemChiTiet);
    }
  }
}
