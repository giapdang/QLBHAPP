package com.example.qlbh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrActivity extends AppCompatActivity {

  private ImageView imgHome, imgGioHang, imgUser , imgDonHang , imgCaiDat;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_qr);

    ImageView qrImageView = findViewById(R.id.qrImageView);
    Button btnXacNhan = findViewById(R.id.btnXacNhan);

    // Generate QR code
    String qrContent = "Your QR content here";
    Bitmap qrBitmap = generateQRCode(qrContent);
    if (qrBitmap != null) {
      qrImageView.setImageBitmap(qrBitmap);
    }

    btnXacNhan.setOnClickListener(v -> {
      // chuyen sang form don hang
      Intent intent = new Intent(QrActivity.this, DonHangActivity.class);
      startActivity(intent);

    });

    // chuyen sang form home
    imgHome = findViewById(R.id.imageViewHome);
    imgHome.setOnClickListener(v -> {
      Intent intent = new Intent(QrActivity.this, HomeActivity.class);
      startActivity(intent);
    });

    // chuyen sang form gio hang
    imgGioHang = findViewById(R.id.imageViewCart);
    imgGioHang.setOnClickListener(v -> {
      Intent intent = new Intent(QrActivity.this, GioHangActivity.class);
      startActivity(intent);
    });

    // chuyen sang form don hang
    imgDonHang = findViewById(R.id.imageViewHistory);
    imgDonHang.setOnClickListener(v -> {
      Intent intent = new Intent(QrActivity.this, DonHangActivity.class);
      startActivity(intent);
    });

    // chuyen sang user
    imgUser = findViewById(R.id.imageViewProfile);
    imgUser.setOnClickListener(v -> {
      Intent intent = new Intent(QrActivity.this, ProfileActivity.class);
      startActivity(intent);
    });

    // chuyen sang form cai dat
    imgCaiDat = findViewById(R.id.imageViewSettings);
    imgCaiDat.setOnClickListener(v -> {
      Intent intent = new Intent(QrActivity.this, CaiDatActivity.class);
      startActivity(intent);
    });
  }

  private Bitmap generateQRCode(String content) {
    QRCodeWriter writer = new QRCodeWriter();
    try {
      int width = 300;
      int height = 300;
      com.google.zxing.common.BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height);
      Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          bitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
        }
      }
      return bitmap;
    } catch (WriterException e) {
      e.printStackTrace();
      return null;
    }
  }
}