package com.itgenius.stockappmini.api;

import com.itgenius.stockappmini.model.LoginResponse;
import com.itgenius.stockappmini.model.ProductShow;
import com.itgenius.stockappmini.model.UserShow;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestAPI {

    // อ่านข้อมูลจากตาราง Users
    @GET("users")
    Call<List<UserShow>> getUserShow();

    // อ่านข้อมูลจากตาราง Products
    @GET("products")
    Call<List<ProductShow>> getProductShow();

    // ล็อกอินเข้าสู่ระบบ
    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResponse> checkLogin(
            @Field("username") String username,
            @Field("password") String password
    );

}
