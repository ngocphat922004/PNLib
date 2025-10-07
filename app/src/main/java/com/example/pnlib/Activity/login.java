package com.example.pnlib.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.R;
import com.example.pnlib.SanPhamdao.SanPhamDao;

public class login extends AppCompatActivity {
    public String login ;

    SanPhamDao sanPhamDao = new SanPhamDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edtusername = findViewById(R.id.loginusername);
        EditText edtpassword = findViewById(R.id.loginPassword);
        Button btnsignin = findViewById(R.id.btnSignin);
        TextView tvaccount = findViewById(R.id.account);

        tvaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,Sign_up.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String Username = sharedPreferences.getString("username","");
        String Password = sharedPreferences.getString("password","");
        String checklogin = sharedPreferences.getString("login","");
        edtusername.setText(Username);
        edtpassword.setText(Password);

        if (checklogin.equalsIgnoreCase("Success")){
            startActivity(new Intent(login.this,MainActivity.class));
        } else if (checklogin == null) {

        }

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtusername.getText().toString();
                String password = edtpassword.getText().toString();
                String login = "Success";
                Dbhelper dbhelper = new Dbhelper(login.this);
                //Kiểm tra nhập liệu

                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(login.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (dbhelper.login(username,password) == 1){
                        Toast.makeText(login.this, "Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.putString("password",password);
                        editor.putString("login",login);
                        editor.apply();

                        String chucvu = sanPhamDao.Timkiemchucvu(username);

                        Intent intent = new Intent(login.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("chucvu" ,chucvu);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }else {
                        Toast.makeText(login.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private boolean checklogin(){
        if (login != null){
            return true;
        }else {
            return false;
        }
    }
}