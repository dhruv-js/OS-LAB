package com.example.os_algo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.os_algo.R;


public class Description_PR extends Fragment {
View view;
ExpandableListView expandableListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_description__p_r, container, false);
    expandableListView=view.findViewById(R.id.expandable_list_pr);

    return view;
    }
}