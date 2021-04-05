package com.example.os_algo.Algorithm;

import android.util.Log;

import com.example.os_algo.model.PR_Input;
import com.example.os_algo.model.PR_Output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LIFO {
    public PR_Output getLIFO(PR_Input in)
    {
        int frame = in.getFrame(), pointer = 0, hit = 0, fault = 0,total = in.getPage().length;
        int buffer[];
        PR_Output out = new PR_Output();
        int[] reference=in.getPage();
        int mem_layout[][];
        String[] checkF= new String[total];
        int indexF=0;
        mem_layout = new int[total][frame];
        buffer = new int[frame];
        int lifopointer=frame-1;
        for(int j = 0; j < frame; j++)
            buffer[j] = -1;


        for(int i = 0; i < total; i++)
        {
            int search = -1;
            for(int j = 0; j < frame; j++)
            {
                if(buffer[j] == reference[i])
                {
                    search = j;
                    hit++;
                    checkF[indexF]="Hit";
                    indexF++;
                    break;
                }
            }
            if(search == -1 && i<frame)
            {
                buffer[pointer] = reference[i];
                fault++;
                pointer++;
                checkF[indexF]="Fault";
                indexF++;
                if(pointer == frame)
                    pointer = 0;
            }
            if(search == -1 && i>=frame)
            {
                buffer[lifopointer] = reference[i];
                fault++;
                lifopointer--;
                checkF[indexF]="Fault";
                indexF++;
                if(lifopointer == -1)
                    lifopointer = frame-1;
            }


            for(int j = 0; j < frame; j++)
                mem_layout[i][j] = buffer[j];
        }
        out.setCheckFault(checkF);
        out.setFault(fault);
        out.setResult(mem_layout);
        return out;
    }

}