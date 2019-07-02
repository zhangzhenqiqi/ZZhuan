package com.example.zzq.zzhuan;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zzq.zzhuan.NavigationBarActivity;
public class MyProfileAlterActivity extends AppCompatActivity {

    private NavigationBarActivity navigationBarActivity;
    DatabaseManager databaseManager;
    EditText et_nickname,et_tel,et_address;
    RadioGroup rg_sex;
    RadioButton rb_male,rb_female;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_alter);
        Intent intent=getIntent();
        user=(User)intent.getSerializableExtra("USER_DATA");
        initView();
    }
    public void initView()
    {
        et_nickname=findViewById(R.id.et_nickname);
        et_tel=findViewById(R.id.et_tel);
        et_address=findViewById(R.id.et_address);
        rg_sex=findViewById(R.id.rg_sex);
        rb_female=findViewById(R.id.rb_female);
        rb_male=findViewById(R.id.rb_male);

        et_nickname.setText(user.getNickname());
        et_tel.setText(user.getTel());
        et_address.setText(user.getAddress());
        if(user.getSex()==0){
            rb_female.setChecked(true);
        }else{
            rb_male.setChecked(true);
        }

        navigationBarActivity = (NavigationBarActivity) super.findViewById(R.id.navi_main);
        navigationBarActivity.setTitle("设置个人信息");
        navigationBarActivity.setClickCallback(new NavigationBarActivity.ClickCallback() {

            /*** * 返回按钮 */
            @Override
            public void onBackClick() {
                Intent intent = new Intent();
                intent.putExtra("MyProfileAlterActivityReturn",3);
                setResult(3,intent);
                finish();
            }

            /*** * 右侧按钮 */
            @Override
            public void onRightClick() {
                user.setNickname(et_nickname.getText().toString());
                user.setAddress(et_address.getText().toString());
                user.setTel(et_tel.getText().toString());
                if(rb_female.isChecked()){
                    user.setSex(0);
                }else{
                    user.setSex(1);
                }
                databaseManager=new DatabaseManager(MyProfileAlterActivity.this);
                databaseManager.updateUser(user);
                Toast.makeText(MyProfileAlterActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
