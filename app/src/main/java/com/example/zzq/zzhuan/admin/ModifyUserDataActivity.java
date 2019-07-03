package com.example.zzq.zzhuan.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zzq.zzhuan.db.DatabaseManager;
import com.example.zzq.zzhuan.MyProfileAlterActivity;
import com.example.zzq.zzhuan.R;
import com.example.zzq.zzhuan.entity.User;

public class ModifyUserDataActivity extends AppCompatActivity {

    EditText et_uid;
    Button bt_mdata;
    User user;
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_data);

        et_uid = findViewById(R.id.et_uid);
        bt_mdata = findViewById(R.id.bt_mdata);

        bt_mdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(et_uid.getText().toString().trim())){
                    Toast.makeText(ModifyUserDataActivity.this,"用户id输入非法！",Toast.LENGTH_SHORT).show();
                }
                long uid=Long.parseLong(et_uid.getText().toString());
                databaseManager = new DatabaseManager(ModifyUserDataActivity.this);
                if(!databaseManager.isHave(uid)){
                    Toast.makeText(ModifyUserDataActivity.this,"没有这个id！",Toast.LENGTH_SHORT).show();
                }
                else{
                    user = databaseManager.findById(uid);
                Intent intent = new Intent(ModifyUserDataActivity.this,MyProfileAlterActivity.class);
                intent.putExtra("USER_DATA",user);
                startActivity(intent);
                }

            }
        });
    }
}
