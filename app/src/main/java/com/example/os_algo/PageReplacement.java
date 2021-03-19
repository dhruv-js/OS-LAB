package com.example.os_algo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.os_algo.Fragments.BarChart_PR;
import com.example.os_algo.Fragments.Description_PR;
import com.example.os_algo.Fragments.Home_PR;
import com.example.os_algo.Fragments.LineChart_PR;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class PageReplacement extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    BubbleNavigationLinearView bubbleNavigationLinearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_replacement);

        bubbleNavigationLinearView=findViewById(R.id.bubblenavigationbar);
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,new Description_PR());
        fragmentTransaction.commit();

        bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position)
                {
                    case 0:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new Description_PR());
                        fragmentTransaction.commit();
                        break;

                    case 1:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new Home_PR());
                        fragmentTransaction.commit();
                        break;

                    case 2:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new LineChart_PR());
                        fragmentTransaction.commit();
                        break;

                    case 3:

                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new BarChart_PR());
                        fragmentTransaction.commit();
                        break;

                }
            }
        });
    }
}