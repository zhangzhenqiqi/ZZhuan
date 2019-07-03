package com.example.zzq.zzhuan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zzq.zzhuan.db.DatabaseManager;
import com.example.zzq.zzhuan.entity.User;

public class LoginActivity extends AppCompatActivity {
    private Button bt_login;
    private AutoCompleteTextView tv_account;
    private EditText et_password ;
    private Long uid;
    private String password;
    private int success=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_account = findViewById(R.id.account_id);
        et_password = findViewById(R.id.password);
        bt_login = findViewById(R.id.sign_in_button);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(0,intent);
                DatabaseManager databaseManager = new DatabaseManager(LoginActivity.this);
                if(tv_account.getText().toString().equals("")) uid=-1L;
                else uid = Long.parseLong(tv_account.getText().toString());
                password = et_password.getText().toString();
                if(databaseManager.isHave(uid)){
                    if(databaseManager.checkLogin(uid,password)){
                        intent.putExtra("successLog",1);
                        User user = new User();
                        user = databaseManager.findById(uid);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("USER_DATA",user);
                        intent.putExtras(bundle);
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"密码输入错误！",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"查无此账号！请核对后重新输入！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
