package com.example.os_algo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.os_algo.Algorithm.FIFO;
import com.example.os_algo.Algorithm.LIFO;
import com.example.os_algo.Algorithm.LRU;
import com.example.os_algo.Algorithm.Optimal;
import com.example.os_algo.Algorithm.Random;
import com.example.os_algo.PageReplacement;
import com.example.os_algo.R;
import com.example.os_algo.model.MyAdapter;
import com.example.os_algo.model.PR_Input;
import com.example.os_algo.model.PR_Output;

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
RecyclerView recyclerView;
TextView faults ;
TextView hits ;
String[] checkF;
int[][] result;
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
        recyclerView = view.findViewById(R.id.prRecycleView);
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
                    case 5:
                        algo=5;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(((PageReplacement) getActivity()).comeagain)
        {
            editTextPage.setText(((PageReplacement) getActivity()).in.getFrame()+"");
            showPage.setText(Arrays.toString(((PageReplacement) getActivity()).in.getPage()));
        }
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
                        if(editTextPage.getText().toString() !=null)
                        {page.add(index, Integer.parseInt(editTextPage.getText().toString() ));
                        index++;
                        showPage.setVisibility(View.VISIBLE);
                        clear.setVisibility(View.VISIBLE);
                        clear.setClickable(true);
                        showPage.setText(page.toString());
                        editTextPage.setText("");
                    }

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

                PR_Output output = null;
                PR_Input input = new PR_Input();
                if (algo == 0) {
                    Toast.makeText(getContext(), "Choose an Algorithm!", Toast.LENGTH_SHORT).show();
                } else {
                    if(editTextFrame.getText()!=null)
                    frame = Integer.parseInt(editTextFrame.getText().toString());
                    Integer[] arr = new Integer[page.size()];
                    int fault = 0;
                    int[] pageArray = new int[page.size()];
                    for (int i = 0; i < pageArray.length; i++) {
                        pageArray[i] = page.get(i).intValue();
                    }
                    if(frame==0 || pageArray==null )
                    {
                        Toast.makeText(getContext(), "Enter valid values!", Toast.LENGTH_SHORT).show();
                    }
                    else if (algo == 1) {
                        input.setFrame(frame);
                        input.setPage(pageArray);
                        FIFO fifo = new FIFO();
                        output = fifo.getFIFO(input);
                        fault = output.getFault();
                        checkF = output.getCheckFault();
                        result = output.getResult();

                    }
                    else if (algo == 2) {
                        input.setFrame(frame);
                        input.setPage(pageArray);
                        LIFO lifo = new LIFO();
                        output = lifo.getLIFO(input);
                        fault = output.getFault();
                        checkF = output.getCheckFault();
                        result = output.getResult();

                    }

                    else if (algo == 3) {

                        input.setFrame(frame);
                        input.setPage(pageArray);
                        LRU lru = new LRU();
                        output = lru.getLRU(input);
                        fault = output.getFault();
                        checkF = output.getCheckFault();
                        result = output.getResult();
                    }
                    else if (algo == 4) {

                        input.setFrame(frame);
                        input.setPage(pageArray);
                        Optimal optimal = new Optimal();
                        output = optimal.getOptimal(input);
                        fault = output.getFault();
                        checkF = output.getCheckFault();
                        result = output.getResult();
                    }
                    else if (algo == 5) {

                        input.setFrame(frame);
                        input.setPage(pageArray);
                        Random random = new Random();
                        output = random.getRandom(input);
                        fault = output.getFault();
                        checkF = output.getCheckFault();
                        result = output.getResult();
                    }

                    ((PageReplacement) getActivity()).out = output;
                    ((PageReplacement) getActivity()).in = input;
                    ((PageReplacement)getActivity()).algorithm = algo;
                    ((PageReplacement) getActivity()).comeagain = true;



                    LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);

                    MyAdapter adapter = new MyAdapter(view.getContext(), checkF, result);
                    recyclerView.setAdapter(adapter);


                    int hit = page.size() - fault;
                    faults.setVisibility(View.VISIBLE);
                    faults.setText("Page Fault:" + fault);
                    hits.setVisibility(View.VISIBLE);
                    hits.setText("Page Hit:" + hit);

                }
            }
        });
        return view;
    }

}