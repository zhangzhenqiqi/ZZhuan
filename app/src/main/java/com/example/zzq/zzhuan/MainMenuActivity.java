package com.example.zzq.zzhuan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zzq.zzhuan.fragment.ClassifyFragment;
import com.example.zzq.zzhuan.fragment.HomeFragment;
import com.example.zzq.zzhuan.fragment.ProfileFragment;
import com.example.zzq.zzhuan.fragment.SearchFragment;

public class MainMenuActivity extends AppCompatActivity {

    SharedPreferences preferences;

    private String KEY_USER_MODEL="USER_MODEL";//0-游客，1-登录用户，2-管理员
    private String KEY_USER_ID="USER_ID";
    private String KEY_USER_PWD="USER_PWD";

    private int USER_MODEL=0;
    private User user = new User();
    private TextView mTextMessage;
    private ProfileFragment profileFragment;
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private SearchFragment searchFragment;
    private Fragment[] fragments;
    private BottomNavigationView navigationView;
    private int lastfragment;//用于记录上次选择的Fragment
    private DatabaseManager databaseManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(lastfragment!=0){
                        switchFragment(lastfragment,0);
                        lastfragment = 0;
                    }
                    return true;
                case R.id.navigation_classify:
                    if(lastfragment!=1){
                        switchFragment(lastfragment,1);
                        lastfragment = 1;
                    }
                    return true;
                case R.id.navigation_plus:
                    //跳转到发布界面
                    return true;
                case R.id.navigation_search:
                    if(lastfragment!=2){
                        switchFragment(lastfragment,2);
                        lastfragment = 2;
                    }
                    return true;
                case R.id.navigation_profile:
                    if(lastfragment!=3){
                        switchFragment(lastfragment,3);
                        lastfragment = 3;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            if(requestCode==1){
                //Toast.makeText(MainMenuActivity.this,"login return",Toast.LENGTH_SHORT).show();
                /*
                当在Login页面点击登录按钮之后，finish()然后调用这里，在这里有可能产生对ProfileFragment的刷新布局的操作，
                * 百度不得解最后野路子尝试先detach在attach就成了，也许有bug，利用Bundle传USER_MODEL刷新布局
                * 后期还要记得添加用户个人信息给Bundle更新布局
                * */
                int successLog = data.getIntExtra("successLog",0);
                if(successLog==0)return;
                if(profileFragment==null) profileFragment = new ProfileFragment();
                user = (User) data.getExtras().getSerializable("USER_DATA");
                if (user!=null) USER_MODEL = user.getIsadmin()+1;
                Bundle bundle = new Bundle();
                bundle.putInt("USER_MODEL",USER_MODEL);
                bundle.putSerializable("USER_DATA",user);
                profileFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().detach(profileFragment).commit();
                getSupportFragmentManager().beginTransaction().attach(profileFragment).commit();
            }
            else if(requestCode==2){
                /*if(profileFragment==null) profileFragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("USER_MODEL",USER_MODEL);
                profileFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().detach(profileFragment).commit();
                getSupportFragmentManager().beginTransaction().attach(profileFragment).commit();*/
            }
            else if(requestCode==3){
                //Toast.makeText(MainMenuActivity.this,"requestcode-3",Toast.LENGTH_SHORT).show();
                databaseManager = new DatabaseManager(MainMenuActivity.this);
                user=databaseManager.findById(user.getUid());
                Bundle bundle = new Bundle();
                bundle.putInt("USER_MODEL",USER_MODEL);
                bundle.putSerializable("USER_DATA",user);
                profileFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().detach(profileFragment).commit();
                getSupportFragmentManager().beginTransaction().attach(profileFragment).commit();
            }
        }
    }

    public void init(){
        databaseManager=new DatabaseManager(MainMenuActivity.this);
        if(databaseManager.isHave(2019)==false){
            user=new User();
            user.setSex(1);
            user.setIsadmin(1);
            user.setUid(2019);
            user.setPassword("2019");
            user.setNickname("admin");
            user.setTel("18211607393");
            user.setAddress("111");
            databaseManager.addUser(user);
        }
        initBottom();
        initfragment();
    }
    public void initBottom(){
         navigationView = findViewById(R.id.navigation);
         navigationView.setLabelVisibilityMode(1);
         navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         /*调整中间icon的大小*/
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        final View iconView = menuView.getChildAt(2).findViewById(android.support.design.R.id.icon);
        final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics);
        layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, displayMetrics);
        iconView.setLayoutParams(layoutParams);
    }
    public void initfragment(){
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
        fragments = new Fragment[]{homeFragment,classifyFragment,searchFragment,profileFragment};
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,homeFragment).show(homeFragment).commit();

    }

    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.fl_container,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

}
