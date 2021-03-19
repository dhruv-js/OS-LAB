package com.example.os_algo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.os_algo.PageReplacement;
import com.example.os_algo.R;

import java.util.ArrayList;
import java.util.Arrays;


public class Home_PR extends Fragment {
Spinner spinner;
View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home_p_r, container, false);
        spinner=view.findViewById(R.id.spinnerpr);

        String[] value={"FIFO","LIFO","LRU","Optimal","Random"};
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.style_s,arrayList);
        spinner.setAdapter(arrayAdapter);

        return view;
    }

}