package com.example.zzq.zzhuan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zzq.zzhuan.R;
import com.example.zzq.zzhuan.db.DatabaseManager;
import com.example.zzq.zzhuan.entity.Goods;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private DatabaseManager databaseManager = new DatabaseManager(getActivity());
    List<Goods> Mylist = new ArrayList<>();
    private ListView listView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        databaseManager=new DatabaseManager(getActivity());
        Mylist=databaseManager.queryAllGoods();
        listView=view.findViewById(R.id.lv_1);
        listView.setAdapter(new MylistAdapter(Mylist,getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
