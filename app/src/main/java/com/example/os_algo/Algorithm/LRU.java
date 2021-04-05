package com.example.os_algo.Algorithm;

import android.util.Log;

import com.example.os_algo.model.PR_Input;
import com.example.os_algo.model.PR_Output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LRU {
    public PR_Output getLRU(PR_Input in)
    {
        int frame = in.getFrame(), pointer = 0, hit = 0, fault = 0,total = in.getPage().length;
        int buffer[];
        Boolean isFull = false;
        PR_Output out = new PR_Output();
        int[] reference=in.getPage();
        int mem_layout[][];
        String[] checkF= new String[total];
        int indexF=0;
        mem_layout = new int[total][frame];
        buffer = new int[frame];
        for(int j = 0; j < frame; j++)
            buffer[j] = -1;


        ArrayList<Integer> stack = new ArrayList<Integer>();

        for(int i = 0; i < total; i++)
        {
            if(stack.contains(reference[i]))
            {
                stack.remove(stack.indexOf(reference[i]));
            }
            stack.add(reference[i]);
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
                    int min_loc = total;
                    for(int j = 0; j < frame; j++)
                    {
                        if(stack.contains(buffer[j]))
                        {
                            int temp = stack.indexOf(buffer[j]);
                            if(temp < min_loc)
                            {
                                min_loc = temp;
                                pointer = j;
                            }
                        }
                    }
                }
                buffer[pointer] = reference[i];
                fault++;
                pointer++;
                checkF[indexF]="Fault";
                indexF++;

                if(pointer == frame)
                {
                    pointer = 0;
                    isFull = true;
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
