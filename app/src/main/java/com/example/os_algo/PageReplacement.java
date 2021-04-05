package com.example.os_algo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.os_algo.Fragments.BarChart_PR;
import com.example.os_algo.Fragments.Description_PR;
import com.example.os_algo.Fragments.Home_PR;
import com.example.os_algo.Fragments.LineChart_PR;
import com.example.os_algo.model.PR_Input;
import com.example.os_algo.model.PR_Output;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
final
public class PageReplacement extends AppCompatActivity {
    public boolean comeagain=false;
    FragmentTransaction fragmentTransaction;
    BubbleNavigationLinearView bubbleNavigationLinearView;
    public PR_Output out = new PR_Output();
    public PR_Input in = new PR_Input();
    public int algorithm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_replacement);



        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.first));
        }

            bubbleNavigationLinearView=findViewById(R.id.bubblenavigationbar);
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new Description_PR());
        fragmentTransaction.commit();

        bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {


                switch (position)
                {
                    case 0:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.fragment_container,new Description_PR());
                        fragmentTransaction.commit();
                        break;

                    case 1:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.fragment_container,new Home_PR());
                        fragmentTransaction.commit();
                        break;

                    case 2:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.fragment_container,new LineChart_PR());
                        fragmentTransaction.commit();
                        break;

                    case 3:

                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.fragment_container,new BarChart_PR());
                        fragmentTransaction.commit();
                        break;

                }
            }
        });
    }
}