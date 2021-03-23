package com.example.os_algo.Algorithm;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO {
Integer[][] result;
    int[][] fresult;
    public int pageFaults(Integer[] pages, int total, int frame)
    {
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not

        HashSet<Integer> s = new HashSet<>(frame);
        result = new Integer[pages.length][frame];
        fresult = new int[pages.length][frame];

        // To store the pages in FIFO manner
        Queue<Integer> indexes = new LinkedList<>() ;
        // Start from initial page
        int page_faults = 0;
        for (int i=0; i<total; i++)
        {
            // Check if the set can hold more pages
            if (s.size() < frame)
            {
                // Insert it into set if not present
                // already which represents page fault
                if (!s.contains(pages[i]))
                {
                    s.add(pages[i]);

                    // increment page fault
                    page_faults++;

                    // Push the current page into the queue
                    indexes.add(pages[i]);

                }
            }

            // If the set is full then need to perform FIFO
            // i.e. remove the first page of the queue from
            // set and queue both and insert the current page
            else
            {
                // Check if current page is not already
                // present in the set
                if (!s.contains(pages[i]))
                {
                    //Pop the first page from the queue
                    int val = indexes.peek();

                    indexes.poll();

                    // Remove the indexes page
                    s.remove(val);

                    // insert the current page
                    s.add(pages[i]);

                    // push the current page into
                    // the queue
                    indexes.add(pages[i]);

                    // Increment page faults
                    page_faults++;
                }

            }
           result[i] = s.toArray(result[i]);

            for(int q=0;q<frame;q++)
            {
                for(int j=0;j<pages.length;j++)
                {
                  if(result[j][q]==null)
                    result[j][q]=-1;
                    fresult[j][q] = result[j][q].intValue();
                }

            }

        }
        return page_faults;
    }
public int[][] res()
{

return fresult;
}
}