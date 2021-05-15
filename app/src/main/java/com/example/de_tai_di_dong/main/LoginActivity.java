package com.example.de_tai_di_dong.main;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
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

public class LoginActivity extends AppCompatActivity {
        EditText etTenDN, etPass;
        Button btnDN,btnDK,btnThoat,btnT;
        int idKH=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        setEvent();

    }

    private void setEvent() {
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten= etTenDN.getText().toString();
                String pass=etPass.getText().toString();
                // bắt ngoại lệ chưa nhập thông tin
                if(ten.length() ==0){
                    etTenDN.setError("Chưa nhập tên tài khoản");
                    etTenDN.requestFocus();
                    return;
                }
                if(pass.length() ==0){
                    etPass.setError("Chưa nhập mật khẩu");
                    etPass.requestFocus();
                    return;
                }
                //Toast.makeText(getApplicationContext(), "OK vao dang nhap", Toast.LENGTH_LONG).show();

                GetDataUser service =CallAPI.getRetrofitInstance().create(GetDataUser.class);
                Call<ArrayList<ResultLogin>> call = service.getLogin(ten,pass);
                call.enqueue(new Callback<ArrayList<ResultLogin>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ResultLogin>> call, Response<ArrayList<ResultLogin>> response) {
                        assert response.body() != null;
                        idKH=response.body().get(0).getResult();
                        //GetDataUser service =  CallAPI.getRetrofitInstance().create(GetDataUser.class);

                        if(response.body().get(0).getResult()!= -1 && response.body().get(0).getResult()!= -2){

                            Call<ArrayList<User>> call_again = service.getInfoUser(idKH);
                            call_again.enqueue(new Callback<ArrayList<User>>() {
                                @Override
                                public void onResponse(Call<ArrayList<User>> call_again, Response<ArrayList<User>> response_again) {
                                    assert response_again.body() != null;
                                    //String info="Name:"+ response.body().get(0).getName()+"\nEmail:"+response.body().get(0).getEmail()+"\nPhone:"+response.body().get(0).getPhone()+"\nUserName:"+response.body().get(0).getUserName()+"\nAddress:"+response.body().get(0).getAddress()+"\n";
                                    //tvInfoUser.setText(info);
                                    int role = response_again.body().get(0).getRodeID();
                                    if(role==2){
                                        etTenDN.requestFocus();
                                        Toast.makeText(LoginActivity.this,"Ứng dụng này không dành cho admin",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(LoginActivity.this,"Đăng nhập thành công!!",Toast.LENGTH_LONG).show();
                                        Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                                        intent.putExtra("idKH",idKH);
                                        startActivity(intent);
                                        // setResult(Activity.RESULT_OK,intent);
                                        finish();
                                    }
                                }
                                @Override
                                public void onFailure(Call<ArrayList<User>> call_again, Throwable t) {

                                }
                            });

                        }else{
                            etTenDN.requestFocus();
                            Toast.makeText(LoginActivity.this,"Đăng nhập thất bại!!",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ResultLogin>> call, Throwable t) {

                    }
                });
                    }

        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLogin();
            }
        });
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                //intent.putExtra("idKH",idKH);
                startActivity(intent);
            }
        });
    }
    private void DialogLogin(){
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.dialog_dangki);
        dialog.setTitle("Đăng kí");
        dialog.setCanceledOnTouchOutside(false);
        //ánh xạ
        final EditText etName= dialog.findViewById(R.id.etName);
        final EditText etEmail= dialog.findViewById(R.id.etEmail);
        final EditText etPhone= dialog.findViewById(R.id.etPhone);
        final EditText etAddress= dialog.findViewById(R.id.etAddress);
        final EditText etUsername= dialog.findViewById(R.id.etUsername);
        final  EditText etPass= dialog.findViewById(R.id.etPass);
        final Button btnDK=dialog.findViewById(R.id.btnDangKi);
        final Button btnThoat= dialog.findViewById(R.id.btnThoat);

        // xét sự kiện
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etName.getText().toString().trim();
                String email=etEmail.getText().toString().trim();
                String phone=etPhone.getText().toString().trim();
                String address=etAddress.getText().toString().trim();
                String username=etUsername.getText().toString().trim();
                String pass=etPass.getText().toString();

                // bắt ngoại lệ
                if(name.length()==0){
                    etName.setError("Bạn chưa nhập tên .");
                    etName.requestFocus();
                    return;
                }
                if(email.length()==0){
                    etEmail.setError("Bạn chưa nhập email .");
                    etEmail.requestFocus();
                    return;
                }
                if(phone.length()==0){
                    etPhone.setError("Bạn chưa nhập phone .");
                    etPhone.requestFocus();
                    return;
                }
                if(address.length()==0){
                    etAddress.setError("Bạn chưa nhập address .");
                    etAddress.requestFocus();
                    return;
                }
                if(username.length()==0){
                    etUsername.setError("Bạn chưa nhập username .");
                    etUsername.requestFocus();
                    return;
                }
                if(pass.length()==0){
                    etPass.setError("Bạn chưa nhập Pass .");
                    etPass.requestFocus();
                    return;
                }
                //xét sự kiện đăng kí của khách hàng
                GetDataUser service =CallAPI.getRetrofitInstance().create(GetDataUser.class);
                Call<ArrayList<ResultLogin>> call = service.postBodySingUp(new User(1,username,pass,name,email,phone,address,""));
                call.enqueue(new Callback<ArrayList<ResultLogin>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ResultLogin>> call, Response<ArrayList<ResultLogin>> response) {
                        assert response.body() != null;
                        if(response.body().get(0).getResult()==-1){
                            Toast.makeText(LoginActivity.this,"Trùng username!! Xin nhập lại.",Toast.LENGTH_LONG).show();
                                return;
                        }
                        if(response.body().get(0).getResult()==-2){
                            Toast.makeText(LoginActivity.this,"Trùng email!! Xin nhập lại.",Toast.LENGTH_LONG).show();
                                return;
                        }
                        if(response.body().get(0).getResult()==-3){
                            Toast.makeText(LoginActivity.this,"Trùng phone!! Xin nhập lại.",Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(response.body().get(0).getResult()==1){
                            Toast.makeText(LoginActivity.this,"Đăng kí thành công!!",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                        dialog.dismiss();
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
               // dialog.cancel();
                //Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                //intent.putExtra("idKH",idKH);
                //startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void setControl() {
        etTenDN = findViewById(R.id.txtTenDN);
        etPass=findViewById(R.id.txtPass);
        btnDN=findViewById(R.id.btnDN);
        btnDK=findViewById(R.id.btnDK);
        btnThoat=findViewById(R.id.btnThoat);
        btnT = findViewById(R.id.btnT);
    }
}
