package com.example.pnlib.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.R;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText edtUsername = findViewById(R.id.edtusername);
        EditText edtEmail = findViewById(R.id.edtemail);
        EditText edtPassword = findViewById(R.id.edtpassword);
        EditText edtchucvu = findViewById(R.id.edtchucvu);
        EditText edtconfirmPassword = findViewById(R.id.edtConfirmPassword);
        Button btnSignup = findViewById(R.id.btnsignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String email = edtEmail.getText().toString();
                String chucvu = edtchucvu.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPass = edtconfirmPassword.getText().toString();

                Dbhelper dbhelper = new Dbhelper(Sign_up.this);
                //Kiểm tra người dùng bỏ trống
                if (username.isEmpty() || email.isEmpty()|| chucvu.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                    Toast.makeText(Sign_up.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (confirmPass.compareTo(password) == 0){
                        if (ivVaid(password)){
                            dbhelper.register(username,chucvu,password,email);
                            Toast.makeText(Sign_up.this, "Record inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Sign_up.this,login.class));
                        }else {
                            Toast.makeText(Sign_up.this, "Password must least 8 characters, having letter, digit and symbol", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(Sign_up.this, "ConfirmPassword and password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }

            }


        });
    }
    private static boolean ivVaid(String password) {
        int flag1 = 0, flag2 = 0, flag3 = 0;
        if (password.length()<0){
            return false;
        }else {
            for (int p =0 ;p< password.length(); p++){
                //kiểm tra ký tự hiện tại có phải là 1 chữ cái hay không
                if (Character.isLetter(password.charAt(p))){
                    flag1 = 1;
                }
            }
            for (int r = 0 ; r<password.length() ;r++){
                //Kiểm tra ký tự hiện tại phải là 1 số hay không
                if (Character.isDigit(password.charAt(r))){
                    flag2 =1;
                }
            }
            for (int s = 0; s < password.length();s++){
                //lấy ký tự hiện tại
                char c = password.charAt(s);
                // và kiểm tra có thuộc các ký tự đặt biệt như chấm câu
                //(33-46) hoặc ký tự '@'(64) hay không
                if (c >= 33 && c <= 46 || c ==64){
                    flag3 = 1;
                }
            }
            if (flag1 == 1 && flag2 ==1 && flag3 == 1 ){
                return true;
            }else {
                return false;
            }
        }
    }
}