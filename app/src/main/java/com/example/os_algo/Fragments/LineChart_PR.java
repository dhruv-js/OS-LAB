package com.example.os_algo.Fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.os_algo.Algorithm.FIFO;
import com.example.os_algo.PageReplacement;
import com.example.os_algo.R;
import com.example.os_algo.model.Input;
import com.example.os_algo.model.PR_Input;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class LineChart_PR extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.third));
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_line_chart__p_r, container, false);
        LineChart lineChart = view.findViewById(R.id.linechart);

        int algorithm = ((PageReplacement)getActivity()).algorithm;
        PR_Input input = ((PageReplacement)getActivity()).in;
        int[] fault = new int[7];
        if(algorithm == 1)
        {
            FIFO fifo = new FIFO();
            fault[0] = fifo.getFIFO(input).getFault();
            input.setFrame(1);
            fault[1] = fifo.getFIFO(input).getFault();
            input.setFrame(2);
            fault[2] = fifo.getFIFO(input).getFault();
            input.setFrame(3);
            fault[3] = fifo.getFIFO(input).getFault();
            input.setFrame(4);
            fault[4] = fifo.getFIFO(input).getFault();
            input.setFrame(5);
            fault[5] = fifo.getFIFO(input).getFault();
            input.setFrame(6);
            fault[6] = fifo.getFIFO(input).getFault();
            input.setFrame(7);


        }

        List<Entry> lineEntries;
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1f, fault[0]));
        lineEntries.add(new Entry(2f, fault[1]));
        lineEntries.add(new Entry(3f, fault[2]));
        lineEntries.add(new Entry(4f, fault[3]));
        lineEntries.add(new Entry(5f, fault[4]));
        lineEntries.add(new Entry(6f, fault[5]));
        lineEntries.add(new Entry(7f, fault[6]));

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "");
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        lineDataSet.setColors(Color.BLACK);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineChart.setBackgroundColor(getResources().getColor(R.color.third));
        lineDataSet.setValueTextSize(18f);
        lineChart.animateX(1000);
        return view;
    }
}