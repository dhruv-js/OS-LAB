package com.example.os_algo.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import com.example.os_algo.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Description_PR extends Fragment {
View view;
ExpandableListView expandableListView;
ArrayList<String> listGroup= new ArrayList<>();
HashMap<String, ArrayList<String>> listChild = new HashMap<>();
MainAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_description__p_r, container, false);
  //  expandableListView=view.findViewById(R.id.expandable_list_pr);

        for(int g=0;g<=10;g++)
        {
            listGroup.add("Group "+g);

            ArrayList<String> arrayList = new ArrayList<>();

            for(int c=0;c<=5;c++)
            {arrayList.add("Item "+c);
            }
            listChild.put(listGroup.get(g),arrayList);
        }


    //    adapter = new MainAdapter(listGroup,listChild);
       //     expandableListView.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.first));
        }

        return view;
    }
}