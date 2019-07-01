package com.example.zzq.zzhuan.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.zzq.zzhuan.LoginActivity;
import com.example.zzq.zzhuan.MainMenuActivity;
import com.example.zzq.zzhuan.MyProfileAlterActivity;
import com.example.zzq.zzhuan.R;
import com.example.zzq.zzhuan.RegisActivity;
import com.example.zzq.zzhuan.User;

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
            view = inflater.inflate(R.layout.fragment_profile_2,container,false);
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
