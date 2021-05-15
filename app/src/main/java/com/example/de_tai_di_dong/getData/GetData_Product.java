package com.example.de_tai_di_dong.getData;

import com.example.de_tai_di_dong.model.SanPham;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetData_Product {

    @GET("allProducts")
    Call<ArrayList<SanPham>> getAllSanPham() ;

    @GET("Statistic")
    Call<ArrayList<SanPham>> getStatisSanPham() ;

    @GET("orderItemOfOrderDetail")
    Call<ArrayList<SanPham>> getDetailOrder(
            @Query("orderid") int id
    ) ;

    @GET("allProsOfCart")
    Call<ArrayList<SanPham>> getProductCart(
        @Query("userId") int idUser
    );

    @GET("allProsOfProGroup")
    Call<List<SanPham>> getProductGroup(
            @Query("proGroupID") int idProductGroup
    );

    @GET("allSalePros")
    Call<List<SanPham>> getAllProductSale() ;

    @GET("proDetail")
    Call<ArrayList<SanPham>> getProduct(
            @Query("proId") int idProduct
    );

}
