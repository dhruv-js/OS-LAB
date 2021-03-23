package com.example.os_algo.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.os_algo.MainActivity;
import com.example.os_algo.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.os_algo.R.font.aclonica;

public class MainAdapter extends BaseExpandableListAdapter {
    ArrayList<String> listTopic;
    HashMap<String,ArrayList<String>> listSubTopic;

    public MainAdapter(ArrayList<String> listTopic, HashMap<String,ArrayList<String>> listSubTopic) {
        this.listTopic = listTopic;
        this.listSubTopic = listSubTopic;
    }


    @Override
    public int getGroupCount() {
     return listTopic.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listSubTopic.get(listTopic.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listTopic.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listSubTopic.get(listTopic.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View v, ViewGroup viewGroup) {
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_expandable_list_item_1
                ,viewGroup,false);
        TextView textView = v.findViewById(android.R.id.text1);
        String sTopic = String.valueOf(getGroup(i));
        textView.setText(sTopic);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.BLACK);
        return v;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View v, ViewGroup viewGroup) {
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_selectable_list_item
                        ,viewGroup,false);
        TextView textView = v.findViewById(android.R.id.text1);
        String sSubTopic = String.valueOf(getGroup(i));
        textView.setText(sSubTopic);
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
