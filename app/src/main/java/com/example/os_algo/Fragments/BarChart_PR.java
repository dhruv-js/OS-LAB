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
import com.example.os_algo.Algorithm.LIFO;
import com.example.os_algo.Algorithm.LRU;
import com.example.os_algo.Algorithm.Optimal;
import com.example.os_algo.Algorithm.Random;
import com.example.os_algo.PageReplacement;
import com.example.os_algo.R;
import com.example.os_algo.model.Input;
import com.example.os_algo.model.PR_Input;
import com.example.os_algo.model.PR_Output;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChart_PR extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        View view =  inflater.inflate(R.layout.fragment_bar_chart__p_r, container, false);
        BarChart barChart = view.findViewById(R.id.barchart);

        PR_Input input = ((PageReplacement)getActivity()).in;
        PR_Output output = ((PageReplacement)getActivity()).out;
        FIFO fifo = new FIFO();
        LIFO lifo = new LIFO();
        LRU lru = new LRU();
        Optimal optimal = new Optimal();
        Random  random = new Random();


        ArrayList<BarEntry> page = new ArrayList<>();

        output = fifo.getFIFO(input);
        int fault = output.getFault();
        page.add(new BarEntry(1,fault));

        output = lifo.getLIFO(input);
        fault = output.getFault();
        page.add(new BarEntry(2,fault));

        output = lru.getLRU(input);
        fault = output.getFault();
        page.add(new BarEntry(3,fault));

        output = optimal.getOptimal(input);
        fault = output.getFault();
        page.add(new BarEntry(4,fault));


        output = random.getRandom(input);
        fault = output.getFault();
        page.add(new BarEntry(5,fault));


        BarDataSet barDataSet = new BarDataSet(page,"Page Replacement");
        barDataSet.setColors(Color.WHITE);
        barDataSet.setValueTextSize(18f);
        barDataSet.setValueTextColor(Color.WHITE);

        barChart.setNoDataText("No Data Entered");
        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(false);
        barChart.setData(barData);


        barChart.setVisibleXRangeMinimum(0);
        barChart.setBackgroundColor(getResources().getColor(R.color.four));
        barChart.getDescription().setText("Page Rep");
        barChart.animateY(1000);

        return view;
    }
}