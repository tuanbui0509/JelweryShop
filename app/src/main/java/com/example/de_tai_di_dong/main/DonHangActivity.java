package com.example.de_tai_di_dong.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.de_tai_di_dong.R;
import com.example.de_tai_di_dong.adapter.DonHangAdapter;
import com.example.de_tai_di_dong.adapter.DonHangClickListener;
import com.example.de_tai_di_dong.adapter.SanPhamAdapter;
import com.example.de_tai_di_dong.getData.CallAPI;
import com.example.de_tai_di_dong.getData.GetDataOrders;
import com.example.de_tai_di_dong.getData.GetData_Product;
import com.example.de_tai_di_dong.model.Orders;
import com.example.de_tai_di_dong.model.SanPham;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangActivity extends AppCompatActivity implements DonHangClickListener {

    ArrayList<Orders> arrayDonHang;
    DonHangAdapter adapter;
    ListView listDonHang;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhang);
        setControl();
        setEvent();
    }

    private void reqestDonHang(){
        GetDataOrders service =  CallAPI.getRetrofitInstance().create(GetDataOrders.class);
        Call<ArrayList<Orders>> call = service.getallOrders();
        call.enqueue(new Callback<ArrayList<Orders>>() {
            @Override
            public void onResponse(Call<ArrayList<Orders>> call, Response<ArrayList<Orders>> response) {
                Log.d("arrmovie", response.toString());
                //show san pham
                //showSanPham(response.body());

            }
            @Override
            public void onFailure(Call<ArrayList<Orders>> call, Throwable t) {
                Log.d("arrmovie", t.toString());
            }

        });
    }
    private void setEvent() {

    }

    private void setControl() {
        listDonHang = findViewById(R.id.listDonHang);
    }

    @Override
    public void clickDonHang(int id) {

    }
}
