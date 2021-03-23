package com.example.os_algo.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.os_algo.Algorithm.FIFO;
import com.example.os_algo.Algorithm.LIFO;
import com.example.os_algo.Algorithm.LRU;
import com.example.os_algo.Algorithm.Optimal;
import com.example.os_algo.R;

import java.util.ArrayList;
import java.util.Arrays;


public class Home_PR extends Fragment {
View view;
Spinner spinner;
EditText editTextFrame ;
EditText editTextPage ;
TextView showPage ;
Button go;
Button add;
int frame;
Button clear;
int index=0;
int algo;
TextView faults ;
TextView hits ;
ArrayList<Integer> page = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home_p_r, container, false);


        spinner=view.findViewById(R.id.spinnerpr);
        add=view.findViewById(R.id.addButton);
        go=view.findViewById(R.id.goButton);
        showPage=view.findViewById(R.id.showPage);
        editTextFrame = view.findViewById(R.id.editTextFrame);
        editTextPage = view.findViewById(R.id.editTextPage);
        clear = view.findViewById(R.id.clear);
        faults = view.findViewById(R.id.showPageFault);
        hits = view.findViewById(R.id.showPageHit);

        String[] value={"Select Algorithm","FIFO","LIFO","LRU","Optimal","Random"};
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.style_s,arrayList);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        algo=0;
                        break;
                    case 1:
                        algo=1;
                        break;
                    case 2:
                        algo=2;
                        break;
                    case 3:
                        algo=3;
                        break;
                    case 4:
                        algo=4;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.second));
        }

        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        page.add(index, Integer.parseInt(editTextPage.getText().toString() ));
                        index++;
                        showPage.setVisibility(View.VISIBLE);
                        clear.setVisibility(View.VISIBLE);
                        clear.setClickable(true);
                        showPage.setText(page.toString());
                        editTextPage.setText("");
                    }
                }
        );

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page.clear();
                index=0;
                showPage.setText(page.toString());
            }
        });


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame = Integer.parseInt(editTextFrame.getText().toString());
                Integer[] arr = new Integer[page.size()];
                int[][] result;
                int fault=0;
                arr = page.toArray(arr);
                if(algo == 0)

                if (algo == 1)
                {
                    FIFO fifo = new FIFO();
                    fault = fifo.pageFaults(arr, page.size(), frame);
                   result = fifo.res();
                    for (int i = 0; i < page.size(); i++)
                    {       Log.v("Answer", Arrays.toString(result[i]) + "");
                    }
                 }


                    int hit = page.size() - fault;
                    faults.setVisibility(View.VISIBLE);
                    faults.setText("Page Fault:" + fault);
                    hits.setVisibility(View.VISIBLE);
                    hits.setText("Page Hit:" + hit);

            }
        });
        return view;
    }

}