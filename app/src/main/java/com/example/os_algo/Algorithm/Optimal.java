package com.example.os_algo.Algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Optimal {
    int mem_layout[][];
    public int pageFaults(Integer[] pages, int total, int frame) {

        int frames = 0, pointer = 0, hit = 0, fault = 0,strng_size;
        boolean isFull = false;
        int buffer[];
        int ref[];
        int mem_layout[][];
        ref = new int[total];
        mem_layout = new int[total][frame];
        buffer = new int[frame];
        for(int j = 0; j < frame; j++)
            buffer[j] = -1;


        for(int i = 0; i < total; i++) {
            int search = -1;
            for (int j = 0; j < frame; j++) {
                if (buffer[j] == ref[i]) {
                    search = j;
                    hit++;
                    break;
                }
            }
// code to update the stack checking its capacity
            if (search == -1) {
                if (isFull) {
                    int index[] = new int[frame];
                    boolean index_flag[] = new boolean[frame];
                    for (int j = i + 1; j < total; j++) {
                        for (int k = 0; k < frame; k++) {
                            if ((ref[j] == buffer[k]) && (index_flag[k] == false)) {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }

//updating pointer to the correct memory location after checking capacity
                    buffer[pointer] = ref[i];
                    fault++;
                    if (!isFull) {
                        pointer++;
                        if (pointer == frames) {
                            pointer = 0;
                            isFull = true;
                        }
                    }
                }
                for (int j = 0; j < frame; j++)
                    mem_layout[i][j] = buffer[j];
            }

// code to display the number strings
           // for (int q = 0; q < frame; q++) {
             //   for (int j = 0; j < total; j++)
               //     System.out.printf("%3d ", mem_layout[j][q]);
                //System.out.println();
           // }
        }

        return fault;
    }
}
  //  public int[][] res()
    //{

      //  return mem_layout;
   // }





