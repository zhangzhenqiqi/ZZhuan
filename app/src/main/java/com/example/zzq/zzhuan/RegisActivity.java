package com.example.zzq.zzhuan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zzq.zzhuan.db.DatabaseManager;
import com.example.zzq.zzhuan.entity.User;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class RegisActivity extends AppCompatActivity {

    private EditText et_uid,et_password1,et_password2,et_nickname,et_tel,et_address;
    RadioGroup rg_sex;
    RadioButton rb_female,rb_male;
    private Button bt_regis;

    private long uid;//学工（登陆账号）号
    private String password1,password2;//密码
    private String nickname;//昵称
    private int sex;//性别
    private String tel;//手机号
    private String address;//地址
    private int isadmin;

    private User user = new User();
    private DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void init(){
        et_uid = findViewById(R.id.et_uid);
        et_password1= findViewById(R.id.et_password1);
        et_password2= findViewById(R.id.et_password2);
        et_nickname = findViewById(R.id.et_nickname);
        et_tel= findViewById(R.id.et_tel);
        et_address = findViewById(R.id.et_address);
        rg_sex = findViewById(R.id.rg_sex);
        rb_female = findViewById(R.id.rb_female);
        rb_male = findViewById(R.id.rb_male);
        bt_regis = findViewById(R.id.bt_regis);

        bt_regis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               /* uid = Long.parseLong(et_uid.getText().toString());
                databaseManager = new DatabaseManager(RegisActivity.this);
                databaseManager.isHave(uid);*/
                if(judge()){
                    user.setSex(sex);
                    user.setUid(uid);
                    user.setAddress(address);
                    user.setIsadmin(isadmin);
                    user.setPassword(password1);
                    user.setNickname(nickname);
                    user.setTel(tel);
                    databaseManager = new DatabaseManager(RegisActivity.this);
                    if(databaseManager.isHave(uid)){
                        Toast.makeText(RegisActivity.this,"注册失败，此id已存在！",Toast.LENGTH_LONG).show();
                    }
                    else {
                        databaseManager.addUser(user);
                        Toast.makeText(RegisActivity.this,"注册成功！请去\"我的\"页面选择登录选项进行登录吧！",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                else{

                }
            }
        });
    }

    public boolean judge(){
        if(et_uid.getText().toString().length()!=12) {
            Toast.makeText(this,"学工号不符合规范！",Toast.LENGTH_SHORT).show();
            return false;
        }
        else uid= Long.parseLong(et_uid.getText().toString());
        password1 = et_password1.getText().toString();
        if(password1.length()<6){
            Toast.makeText(this,"密码格式不符合规范！",Toast.LENGTH_SHORT).show();
            return false;
        }
        for(int i=0;i<password1.length();i++){
            char c = password1.charAt(i);
            if(c=='.'||c=='*'||c=='?'||c=='@'||c=='_'||isDigit(c)||isLetter(c)){}
            else{
                Toast.makeText(this,"密码格式不符合规范！",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        password2 = et_password2.getText().toString();
        if(password2.equals(password1)==false){
            Toast.makeText(this,"密码前后不一致！",Toast.LENGTH_SHORT).show();
            return false;
        }


        nickname = et_nickname.getText().toString().trim();
        tel = et_tel.getText().toString().trim();
        address = et_address.getText().toString().trim();
        if(rb_male.isChecked())sex=1;
        else if(rb_female.isChecked())sex=0;
        else {Toast.makeText(this,"有内容未填完！",Toast.LENGTH_SHORT).show();return false;}
        if(nickname.equals("")||tel.equals("")||address.equals("")){
            Toast.makeText(this,"有内容未填完！",Toast.LENGTH_SHORT).show();
            return false;
        }
        isadmin=0;
        return true;
    }
}
