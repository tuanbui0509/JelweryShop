package com.example.de_tai_di_dong.getData;

import com.example.de_tai_di_dong.model.Cart;
import com.example.de_tai_di_dong.model.ResultCartItem;
import com.example.de_tai_di_dong.model.ResultLogin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface GetDataCart {
    @PUT("checkOut")
    Call<ArrayList<ResultLogin>> putCheckOut(
            @Query("userID") int userID,
            @Query("name")String name,
            @Query("phone") String phone,
            @Query("email") String email,
            @Query("address")String  address,
            @Query("note") String note
    );

    @GET("cartCount")
    Call<ArrayList<Cart>> getCartCount(
            @Query("userId") int userId
    );

    @PUT("plusCartItem")
    Call<ArrayList<ResultCartItem>> getCartItem(
            @Query("userId") int userId,
            @Query("productId") int productId,
            @Query("quantity") int quantily
    );
    @DELETE("deleteCartItem")
    Call<ArrayList<ResultLogin>> deleteCartItem(
            @Query("userId") int userId,
            @Query("productId") int productId
    );
}


