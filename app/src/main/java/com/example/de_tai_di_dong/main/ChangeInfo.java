package com.example.de_tai_di_dong.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.de_tai_di_dong.R;
import com.example.de_tai_di_dong.getData.CallAPI;
import com.example.de_tai_di_dong.getData.GetDataUser;
import com.example.de_tai_di_dong.model.ResultLogin;
import com.example.de_tai_di_dong.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeInfo extends AppCompatActivity {
    int idKH=0;
    EditText etname, etemail,etaddress,etphone,etavatar;
    Button btnThoat, btnChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        idKH=getIntent().getIntExtra("idKH",idKH);
        setControl();
        setEvent();
    }

    private void setEvent() {

        GetDataUser service =  CallAPI.getRetrofitInstance().create(GetDataUser.class);
        Call<ArrayList<User>> call = service.getInfoUser(idKH);
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                assert response.body() != null;
                etname.setText(response.body().get(0).getName());
                etemail.setText(response.body().get(0).getEmail());
                etphone.setText(response.body().get(0).getPhone());
                etaddress.setText(response.body().get(0).getAddress());
                //etavatar.setText(response.body().get(0).getAddress());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(ChangeInfo.this,UserActivity.class);
//                intent.putExtra("idKH",idKH);
//                startActivity(intent);
                finish();
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =etname.getText().toString();
                String email=etemail.getText().toString();
                String phone=etphone.getText().toString();
                String address=etaddress.getText().toString();
                String avatar="";

                GetDataUser service =CallAPI.getRetrofitInstance().create(GetDataUser.class);
                Call<ArrayList<ResultLogin>> call = service.putupdateUserBody(new User(idKH,"username","pass",name,email,phone,address,avatar));
                call.enqueue(new Callback<ArrayList<ResultLogin>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ResultLogin>> call, Response<ArrayList<ResultLogin>> response) {
                        assert response.body() != null;
                        if(response.body().get(0).getResult()== -1){
                            Toast.makeText(ChangeInfo.this,"Email hoặc SDT đã bị trùng!!!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(response.body().get(0).getResult()== 1){
                            Toast.makeText(ChangeInfo.this,"Thay đổi thành công!!!",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(ChangeInfo.this,UserActivity.class);
                            intent.putExtra("idKH",idKH);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ResultLogin>> call, Throwable t) {

                    }
                });

            }
        });

    }

    private void setControl() {
        etname=findViewById(R.id.editTextname);
        etemail=findViewById(R.id.editTextemail);
        etphone=findViewById(R.id.editTextphone);
        etaddress=findViewById(R.id.editTextaddress);
        //etavatar=findViewById(R.id.editTextavatar);
        btnChange=findViewById(R.id.buttonChange);
        btnThoat=findViewById(R.id.buttonExit);

    }
}
