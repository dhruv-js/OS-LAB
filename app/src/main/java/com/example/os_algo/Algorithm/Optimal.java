package com.example.os_algo.Algorithm;

import android.util.Log;

import com.example.os_algo.model.PR_Input;
import com.example.os_algo.model.PR_Output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Optimal {
    public PR_Output getOptimal(PR_Input in)
    {
        int frame = in.getFrame(), pointer = 0, hit = 0, fault = 0,total = in.getPage().length;
        int buffer[];
        PR_Output out = new PR_Output();
        int[] reference=in.getPage();
        int mem_layout[][];
        Boolean isFull=false;
        String[] checkF= new String[total];
        int indexF=0;
        mem_layout = new int[total][frame];
        buffer = new int[frame];
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
            if(search == -1)
            {
                if(isFull)
                {
                    int index[] = new int[frame];
                    boolean index_flag[] = new boolean[frame];
                    for(int j = i + 1; j < total; j++)
                    {
                        for(int k = 0; k < frame; k++)
                        {
                            if((reference[j] == buffer[k]) && (index_flag[k] == false))
                            {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }
                    int max = index[0];
                    pointer = 0;
                    if(max == 0)
                        max = 200;
                    for(int j = 0; j < frame; j++)
                    {
                        if(index[j] == 0)
                            index[j] = 200;
                        if(index[j] > max)
                        {
                            max = index[j];
                            pointer = j;
                        }
                    }
                }
                buffer[pointer] = reference[i];
                fault++;
                checkF[indexF]="Fault";
                indexF++;

                if(!isFull)
                {
                    pointer++;
                    if(pointer == frame)
                    {
                        pointer = 0;
                        isFull = true;
                    }
                }
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
