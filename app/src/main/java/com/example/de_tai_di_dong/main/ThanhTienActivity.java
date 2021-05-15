package com.example.de_tai_di_dong.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.de_tai_di_dong.R;
import com.example.de_tai_di_dong.getData.CallAPI;
import com.example.de_tai_di_dong.getData.GetDataCart;
import com.example.de_tai_di_dong.getData.GetDataUser;
import com.example.de_tai_di_dong.model.ResultLogin;
import com.example.de_tai_di_dong.model.User;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhTienActivity extends AppCompatActivity {
        int idKH=0;
        EditText editTextName, editTextEmail,editTextPhone,editTextAddress,editTextNote;
        Button btnTT,btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_tien);
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
                editTextName.setText(response.body().get(0).getName());
               editTextPhone.setText(response.body().get(0).getPhone());
               editTextEmail.setText(response.body().get(0).getEmail());
               editTextAddress.setText(response.body().get(0).getAddress());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
        btnTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= editTextName.getText().toString();
                String phone= editTextPhone.getText().toString();
                String email= editTextEmail.getText().toString();
                String address= editTextAddress.getText().toString();
                String note= editTextNote.getText().toString();
                GetDataCart service = CallAPI.getRetrofitInstance().create(GetDataCart.class);
                Call<ArrayList<ResultLogin>> call= service.putCheckOut(idKH,name,phone,email,address,note);
                call.enqueue(new Callback<ArrayList<ResultLogin>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ResultLogin>> call, Response<ArrayList<ResultLogin>> response) {
                        assert response.body() != null;
                        if(response.body().get(0).getResult()==1){
                            //Tạo đối tượng
                            AlertDialog.Builder b = new AlertDialog.Builder(ThanhTienActivity.this);
                            b.setTitle("Xác nhận");
                            b.setMessage("Bạn mua hàng thành công !!");
                            b.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(ThanhTienActivity.this,MainActivity.class);
                                    intent.putExtra("idKH",idKH);
                                    startActivity(intent);
                                }
                            });
                            //Tạo dialog
                            AlertDialog al = b.create();
                            al.show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayList<ResultLogin>> call, Throwable t) {

                    }
                });

            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ThanhTienActivity.this,GioHangActivity.class);
                intent.putExtra("idKH",idKH);
                startActivity(intent);
            }
        });

    }

    private void setControl() {
        editTextName=findViewById(R.id.editTextNme);
        editTextPhone=findViewById(R.id.editTextPhone);
        editTextAddress=findViewById(R.id.editTextAddress);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextNote=findViewById(R.id.editTextNote);
        btnThoat=findViewById(R.id.buttonThoat);
        btnTT=findViewById(R.id.buttonTT);
    }
}
