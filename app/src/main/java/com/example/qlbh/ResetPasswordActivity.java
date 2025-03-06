package com.example.qlbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qlbh.controller.BaseApi;
import com.example.qlbh.config.ApiClient;
import com.example.qlbh.request.ForgetPasswordRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText password, confirmPassword;
    private Button reset;
    private BaseApi baseApi;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        password = findViewById(R.id.passmoi);
        confirmPassword = findViewById(R.id.comfimpassmoi);
        reset = findViewById(R.id.btndoipass);
        baseApi = ApiClient.getRetrofitInstance().create(BaseApi.class);

        // Lấy email từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", null);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = password.getText().toString();
                String confirmPasswordText = confirmPassword.getText().toString();
                if (newPassword.equals(confirmPasswordText)) {
                    resetPassword(email, newPassword, confirmPasswordText);
                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetPassword(String email, String newPassword, String confirmPassword) {
        ForgetPasswordRequest request = new ForgetPasswordRequest(email, newPassword, confirmPassword);
        baseApi.forgetPassword(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ResetPasswordActivity.this, "Password reset successfully", Toast.LENGTH_SHORT).show();
                    // ve trang login
                    Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ResetPasswordActivity.this, "Failed to reset password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ResetPasswordActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}