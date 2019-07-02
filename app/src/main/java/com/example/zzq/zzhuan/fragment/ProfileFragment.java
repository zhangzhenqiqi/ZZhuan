package com.example.zzq.zzhuan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zzq.zzhuan.LoginActivity;
import com.example.zzq.zzhuan.MyProfileAlterActivity;
import com.example.zzq.zzhuan.R;
import com.example.zzq.zzhuan.RegisActivity;
import com.example.zzq.zzhuan.admin.DelUserActivity;
import com.example.zzq.zzhuan.admin.ModifyUserDataActivity;
import com.example.zzq.zzhuan.admin.ResetPasswdActivity;
import com.example.zzq.zzhuan.User;
/*
* requestcode:
* 1：登录
* 2：注册
* 3：修改个人信息
* */
public class ProfileFragment extends Fragment {

    private final String Tag = "ProfileFragment";
    private int USER_MODEL=0;
    User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if(bundle!=null)USER_MODEL = bundle.getInt("USER_MODEL",USER_MODEL);
        View view=null;
        if(USER_MODEL == 0 ){
            view = inflater.inflate(R.layout.fragment_profile_0,container,false);
            init_0(view);
        }else if(USER_MODEL == 1){
            user =(User) bundle.getSerializable("USER_DATA");
            view = inflater.inflate(R.layout.fragment_profile_1,container,false);
            init_1(view);
        }else if(USER_MODEL ==2){
            user =(User) bundle.getSerializable("USER_DATA");
            view = inflater.inflate(R.layout.fragment_profile_2,container,false);
            init_2(view);
        }

        return view;
    }
    public void init_0(View view){
        view.findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                getActivity().startActivityForResult(intent,1);
            }
        });
        view.findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),RegisActivity.class);
                getActivity().startActivityForResult(intent,2);
            }
        });
    }
    public void init_1(View view){
        TextView tv_nickname,tv_id,tv_model;
        tv_id=view.findViewById(R.id.tv_id);
        tv_id.setText(String.valueOf(user.getUid()));
        tv_model=view.findViewById(R.id.tv_model);
        if(user.getIsadmin()==0){
            tv_model.setText("普通用户");
        }else{
            tv_model.setText("管理员");
        }
        tv_nickname=view.findViewById(R.id.tv_nickname);
        tv_nickname.setText(user.getNickname());

        LinearLayout ll_myprofile=view.findViewById(R.id.ll_myprofile);
        ll_myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyProfileAlterActivity.class);
                intent.putExtra("USER_DATA",user);
                getActivity().startActivityForResult(intent,3);
            }
        });
    }

    public void init_2(View view){
        TextView tv_nickname,tv_id,tv_model;
        tv_id=view.findViewById(R.id.tv_id);
        tv_id.setText(String.valueOf(user.getUid()));
        tv_model=view.findViewById(R.id.tv_model);
        if(user.getIsadmin()==0){
            tv_model.setText("普通用户");
        }else{
            tv_model.setText("管理员");
        }
        tv_nickname=view.findViewById(R.id.tv_nickname);
        tv_nickname.setText(user.getNickname());

        Button bt_rpwd,bt_deluser,bt_mdata;
        bt_rpwd=view.findViewById(R.id.bt_mpwd);
        bt_rpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ResetPasswdActivity.class);
                getActivity().startActivity(intent);
            }
        });
        bt_deluser=view.findViewById(R.id.bt_deluser);
        bt_deluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DelUserActivity.class);
                getActivity().startActivity(intent);
            }
        });
        bt_mdata=view.findViewById(R.id.bt_mdata);
        bt_mdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ModifyUserDataActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
