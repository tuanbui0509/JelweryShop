package com.example.de_tai_di_dong.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.de_tai_di_dong.R;
import com.example.de_tai_di_dong.adapter.CarItemListener;
import com.example.de_tai_di_dong.adapter.GioHangAdapter;
import com.example.de_tai_di_dong.getData.CallAPI;
import com.example.de_tai_di_dong.getData.GetDataCart;
import com.example.de_tai_di_dong.getData.GetData_Product;
import com.example.de_tai_di_dong.model.Cart;
import com.example.de_tai_di_dong.model.ResultLogin;
import com.example.de_tai_di_dong.model.SanPham;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangActivity extends AppCompatActivity implements CarItemListener {
    int idKH=0;
    int idsp=0;
    double tongtien=0;
    GioHangAdapter adapterGioHang;
    ArrayList<SanPham>list_sp = new ArrayList<>();
    ListView listView;
    TextView tv_TT;
    Button btn_TT, btnMuaThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        idKH=getIntent().getIntExtra("idKH",idKH);
        setControl();
        setEvent();
    }

    private void setEvent() {
        LaySpCart(idKH);
        btn_TT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(GioHangActivity.this,ThanhTienActivity.class);
                intent.putExtra("idKH",idKH);
                startActivity(intent);
            }
        });
        btnMuaThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(GioHangActivity.this,MainActivity.class);
//                intent.putExtra("idUser",idKH);
//                startActivity(intent);
                finish();
            }
        });
    }
    public void LaySpCart(final int idKH){
        GetData_Product service = CallAPI.getRetrofitInstance().create(GetData_Product.class);
        Call<ArrayList<SanPham>> call=service.getProductCart(idKH);
        call.enqueue(new Callback<ArrayList<SanPham>>() {
            @Override
            public void onResponse(Call<ArrayList<SanPham>> call, Response<ArrayList<SanPham>> response) {
                assert response.body() != null;
            for(int i=0; i<response.body().size();i++){
                    list_sp.add(response.body().get(i));
                    tongtien=tongtien+(response.body().get(i).getPrice()*response.body().get(i).getQuantity());
                }
                tv_TT.setText(String.valueOf(tongtien));
                adapterGioHang = new GioHangAdapter(getApplicationContext(),list_sp, idKH,GioHangActivity.this);
                listView.setAdapter(adapterGioHang);
                adapterGioHang.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ArrayList<SanPham>> call, Throwable t) {

            }
        });

    }
    private void setControl() {
        listView=findViewById(R.id.listCartItem);
        tv_TT=findViewById(R.id.tvThanhTien);
        btn_TT=findViewById(R.id.btnThanhTien);
        btnMuaThem=findViewById(R.id.btnMuaThem);
    }

    @Override
    public void onDeleteCartItem(final int idSP , final int position) {
         GetDataCart service = CallAPI.getRetrofitInstance().create(GetDataCart.class);
                Call<ArrayList<ResultLogin>> call= service.deleteCartItem(idKH,idSP);
                call.enqueue(new Callback<ArrayList<ResultLogin>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ResultLogin>> call, Response<ArrayList<ResultLogin>> response) {
                        assert response.body() != null;
                        if(list_sp.size() >= position)
                            list_sp.remove(position);
                            adapterGioHang.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Call<ArrayList<ResultLogin>> call, Throwable t) {

                    }
                });
    }
    @Override
    public void ClickItem(int idSP) {
        idsp= idSP;
        Intent intent =new Intent(GioHangActivity.this, SanPhamActivity.class);
        intent.putExtra("idSP",idsp);
        intent.putExtra("idKH",idKH);
        startActivity(intent);
    }
}
