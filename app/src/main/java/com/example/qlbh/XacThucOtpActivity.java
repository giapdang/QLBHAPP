// XacThucOtpActivity.java
package com.example.qlbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qlbh.controller.BaseApi;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.request.VerifyOtpRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XacThucOtpActivity extends AppCompatActivity {

    private ImageView back;
    private EditText otp;
    private Button verify;
    private BaseApi baseApi;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacthucotp);

        back = findViewById(R.id.back);
        otp = findViewById(R.id.email);
        verify = findViewById(R.id.btndoipass);
        baseApi = ApiClient.getRetrofitInstance().create(BaseApi.class);

        // Lấy email từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", null);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpText = otp.getText().toString();
                verifyOtp(otpText, email);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void verifyOtp(String otp, String email) {
        VerifyOtpRequest request = new VerifyOtpRequest(otp, email);
        baseApi.verifyOtp(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Chuyển sang form ResetPassword
                    Intent intent = new Intent(XacThucOtpActivity.this, ResetPasswordActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(XacThucOtpActivity.this, "Failed to verify OTP", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(XacThucOtpActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}