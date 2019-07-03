package com.example.zzq.zzhuan.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zzq.zzhuan.db.DatabaseManager;
import com.example.zzq.zzhuan.R;
import com.example.zzq.zzhuan.entity.User;

public class ResetPasswdActivity extends AppCompatActivity{

    private EditText et_uid,et_password;
    private Button bt_reset;
    private DatabaseManager databaseManager;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_passwd);

        et_uid=findViewById(R.id.et_uid);
        et_password=findViewById(R.id.et_password);
        bt_reset=findViewById(R.id.bt_resetpwd);

        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(et_uid.getText().toString().trim())){
                    Toast.makeText(ResetPasswdActivity.this,"用户id输入非法！",Toast.LENGTH_SHORT).show();
                }
                long uid=Long.parseLong(et_uid.getText().toString());
                String password = et_password.getText().toString();
                databaseManager=new DatabaseManager(ResetPasswdActivity.this);
                if(!databaseManager.isHave(uid)){
                    Toast.makeText(ResetPasswdActivity.this,"没有这个id！",Toast.LENGTH_SHORT).show();
                }else{
                    user=databaseManager.findById(uid);
                    user.setPassword(password);
                    databaseManager.updateUser(user);
                    Toast.makeText(ResetPasswdActivity.this,"重置密码成功！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
