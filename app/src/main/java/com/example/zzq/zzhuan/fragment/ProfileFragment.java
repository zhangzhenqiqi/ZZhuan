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
import android.widget.Toast;

import com.example.zzq.zzhuan.LoginActivity;
import com.example.zzq.zzhuan.MainMenuActivity;
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
        }else if(USER_MODEL == 1){
            user =(User) bundle.getSerializable("USER_DATA");
            view = inflater.inflate(R.layout.fragment_profile_1,container,false);
        }else if(USER_MODEL ==2){
            view = inflater.inflate(R.layout.fragment_profile_2,container,false);
        }

        return view;
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
