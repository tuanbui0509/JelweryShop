package com.example.de_tai_di_dong.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.de_tai_di_dong.R;
import com.example.de_tai_di_dong.adapter.GioHangAdapter;
import com.example.de_tai_di_dong.adapter.HoaDonAdapter;
import com.example.de_tai_di_dong.adapter.ItemClickListener;
import com.example.de_tai_di_dong.getData.CallAPI;
import com.example.de_tai_di_dong.getData.GetDataCart;
import com.example.de_tai_di_dong.getData.GetDataOrders;
import com.example.de_tai_di_dong.getData.GetData_Product;
import com.example.de_tai_di_dong.model.Orders;
import com.example.de_tai_di_dong.model.SanPham;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToan_HoaDonActivity extends AppCompatActivity implements ItemClickListener {
    int idKH;
    ListView listView;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<Orders> listHD = new ArrayList<>();
    Button btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan__hoa_don);
        idKH= getIntent().getIntExtra("idKH",idKH);
        setControl();
        setEvent();
    }

    private void setControl() {
        listView=findViewById(R.id.listHoaDon);
        btnThoat=findViewById(R.id.button);
    }
    private void LayHD(final int idKH){
        GetDataOrders service = CallAPI.getRetrofitInstance().create(GetDataOrders.class);
        Call<ArrayList<Orders>> call=service.getallOrdersUser(idKH);
        call.enqueue(new Callback<ArrayList<Orders>>() {
            @Override
            public void onResponse(Call<ArrayList<Orders>> call, Response<ArrayList<Orders>> response) {
                assert response.body() != null;
                Log.d("suss",response.body().toString());
                for(int i=0; i<response.body().size();i++){
                    listHD.add(response.body().get(i)); }
                show(listHD);
            }

            @Override
            public void onFailure(Call<ArrayList<Orders>> call, Throwable t) {
                Log.d("abc",t.toString());
            }
        });
    }

    private void show(ArrayList<Orders> listHD){
        hoaDonAdapter = new HoaDonAdapter(listHD,this, idKH,this);
        listView.setAdapter(hoaDonAdapter);
        hoaDonAdapter.notifyDataSetChanged();
    }
    private void setEvent() {
        LayHD(idKH);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ThanhToan_HoaDonActivity.this,MainActivity.class);
//                intent.putExtra("idUser",idKH);
//                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void ClickItem(int idOrder) {
        Intent intent =new Intent(ThanhToan_HoaDonActivity.this,CT_HoaDonActivity.class);
        intent.putExtra("idOrder",idOrder);
        intent.putExtra("idKH",idKH);
        startActivity(intent);
    }
}
