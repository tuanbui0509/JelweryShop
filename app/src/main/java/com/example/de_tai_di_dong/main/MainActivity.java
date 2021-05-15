package com.example.de_tai_di_dong.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.example.de_tai_di_dong.R;
import com.example.de_tai_di_dong.adapter.ItemClickListener;
import com.example.de_tai_di_dong.adapter.PopularAdapter;
import com.example.de_tai_di_dong.adapter.SanPhamAdapter;
import com.example.de_tai_di_dong.adapter.SellerAdapter;
import com.example.de_tai_di_dong.getData.CallAPI;
import com.example.de_tai_di_dong.getData.GetData_Product;
import com.example.de_tai_di_dong.model.SanPham;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    Toolbar toolbar;
    ArrayList<SanPham> arraySanPham;
    SanPhamAdapter adapterSP;
    ListView listsanpham;
    EditText etSearch;
    RecyclerView popularRecycler, sellRecycler;
    PopularAdapter popularAdapter;
    SellerAdapter sellerAdapter;
    ImageView account, btnCart;
    //navigation
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    int idsp = 0;
    int idKH = 0;
    NavigationView navigationView;
    Toolbar infoAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idKH = getIntent().getIntExtra("idKH", 0);
        setControl();
        requestSanPham();
        setEvent();

    }

    private void requestSanPham() {
        GetData_Product service = CallAPI.getRetrofitInstance().create(GetData_Product.class);
        Call<ArrayList<SanPham>> call = service.getAllSanPham();
        call.enqueue(new Callback<ArrayList<SanPham>>() {
            @Override
            public void onResponse(Call<ArrayList<SanPham>> call, Response<ArrayList<SanPham>> response) {
                Log.d("arrmovie", response.toString());
                //show san pham
                popularProduct(response.body());
                sellerProduct(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<SanPham>> call, Throwable t) {
                Log.d("arrmovie", t.toString());
            }

        });
    }

    private void setEvent() {
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idKH == 0) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    intent.putExtra("idKH", idKH);
                    startActivity(intent);
                }
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                intent.putExtra("idKH", idKH);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        arraySanPham = new ArrayList<>();
        listsanpham = findViewById(R.id.listSanPham);
        account = findViewById(R.id.account);
        btnCart = findViewById(R.id.btnCart);
//        infoAccount = findViewById(R.id.infoAccount);
//        setSupportActionBar(infoAccount);
    }

    private void popularProduct(List<SanPham> listSanPham) {
        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularAdapter = new PopularAdapter(this, listSanPham, idKH);
        popularRecycler.setAdapter(popularAdapter);
    }

    private void sellerProduct(List<SanPham> listSanPham) {
        sellRecycler = findViewById(R.id.seller_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        sellRecycler.setLayoutManager(layoutManager);
        sellerAdapter = new SellerAdapter(this, listSanPham, idKH);
        sellRecycler.setAdapter(sellerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        //getMenuInflater().inflate(R.menu.draw_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //sự kiện click vào item
    @Override
    public void ClickItem(int idSP) {
        idsp = idSP;
        Intent intent = new Intent(MainActivity.this, SanPhamActivity.class);
        intent.putExtra("idSP", idsp);
        intent.putExtra("idKH", idKH);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
