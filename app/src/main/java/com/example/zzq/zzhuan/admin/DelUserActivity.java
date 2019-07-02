package com.example.zzq.zzhuan.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zzq.zzhuan.DatabaseManager;
import com.example.zzq.zzhuan.R;
import com.example.zzq.zzhuan.User;

public class DelUserActivity extends AppCompatActivity {
    private EditText et_uid;
    private Button bt_deluser;
    private DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_user);

        et_uid=findViewById(R.id.et_uid);
        bt_deluser=findViewById(R.id.bt_deluser);

        bt_deluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(et_uid.getText().toString().trim())){
                    Toast.makeText(DelUserActivity.this,"用户id输入非法！",Toast.LENGTH_SHORT).show();
                }
                long uid=Long.parseLong(et_uid.getText().toString());
                databaseManager=new DatabaseManager(DelUserActivity.this);
                if(!databaseManager.isHave(uid)){
                    Toast.makeText(DelUserActivity.this,"没有这个id！",Toast.LENGTH_SHORT).show();
                }else{
                    databaseManager.delUser(uid);
                    Toast.makeText(DelUserActivity.this,"删除用户成功！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
