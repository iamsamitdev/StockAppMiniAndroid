package com.itgenius.stockappmini.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itgenius.stockappmini.R;
import com.itgenius.stockappmini.api.RestAPI;
import com.itgenius.stockappmini.api.RetrofitServer;
import com.itgenius.stockappmini.model.LoginResponse;
import com.itgenius.stockappmini.model.ProductShow;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ตรวจว่ามีข้อมูลการล็อกอินอยู่หรือไม่
        pref = getSharedPreferences("pref_login", Context.MODE_PRIVATE);
        if (pref.contains("pref_userid")) {
            // ส่งไปหน้า Main
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }


        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("1234")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Login Fail!!!", Toast.LENGTH_SHORT).show();
                }
                */

                // เรียกใช้งาน API Login ที่สร้างไว้ใน RestAPI
                RestAPI api = RetrofitServer.getClient().create(RestAPI.class);
                Call<LoginResponse> checkLogin = api.checkLogin(
                        username.getText().toString(),
                        password.getText().toString()
                );

                checkLogin.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.body().getStatus().equals("success")){

                            // เก็บข้อมูลการ Login ลงตัวแปร SharePreferrence
                            pref = getSharedPreferences("pref_login", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("pref_userid", response.body().getUserid());
                            editor.apply();

                            // พาไปหน้า MainActivity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"Login Fail!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Cannot connect to api server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // ทดสอบอ่านไฟล์ API Products ออกมาแสดง
        RestAPI api = RetrofitServer.getClient().create(RestAPI.class);
        Call<List<ProductShow>> getUser = api.getProductShow();
        getUser.enqueue(new Callback<List<ProductShow>>() {
            @Override
            public void onResponse(Call<List<ProductShow>> call, Response<List<ProductShow>> response) {
                // แสดงข้อมูลออกมาที่ Console
                // Log.d("Data = ", new Gson().toJson(response.body()));



            }

            @Override
            public void onFailure(Call<List<ProductShow>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
}
