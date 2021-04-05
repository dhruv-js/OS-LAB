package com.example.os_algo.model;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.os_algo.R;

import java.util.Arrays;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    String[] rcheck;
    int[][] roneRow;


    public MyAdapter(Context context, String[] rcheck,int[][] roneRow)
    {
        this.context = context;
        this.rcheck = rcheck;
        this.roneRow = roneRow;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_singlestep,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text1.setText(rcheck[position]);
        holder.text2.setText(Arrays.toString(roneRow[position]));
        TextView[] textArray = new TextView[rcheck.length];
        TableRow[] tr_head = new TableRow[rcheck.length];


    }

    @Override
    public int getItemCount() {
        return rcheck.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView text2;
        TableLayout singleTable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.hit);
            text2 =itemView.findViewById(R.id.faulsteps);
            singleTable = itemView.findViewById(R.id.prTable);

        }
    }
}

