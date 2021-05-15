package com.example.de_tai_di_dong.getData;

import com.example.de_tai_di_dong.model.ItemOrders;
import com.example.de_tai_di_dong.model.Orders;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface GetDataOrders {
    @GET("allOrdersOfUser")
    Call<ArrayList<Orders>> getallOrdersUser(
            @Query("userId") int userId
    );

    @GET("orderItemOfOrder")
    Call<ArrayList<ItemOrders>> getItemOrders(
            @Query("orderId") int orderId
    );

    @PUT("order")
    void cfOrder(
            @Query("orderId") int orderId
    );

    @GET("allOrders")
    Call<ArrayList<Orders>> getallOrders(
    );
}
