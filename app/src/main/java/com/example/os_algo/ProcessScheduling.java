package com.example.os_algo;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.os_algo.algo_psa.FCFS;
import com.example.os_algo.algo_psa.PriorityBased;
import com.example.os_algo.algo_psa.RoundRobin;
import com.example.os_algo.algo_psa.SJF;
import com.example.os_algo.include.CpuQueueView;
import com.example.os_algo.model.Input;
import com.example.os_algo.model.Output;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

public class ProcessScheduling extends AppCompatActivity {

    private static final String TAG = "psa";
    String[] algoritms;
    Spinner algoClass;
    RadioGroup algoSubclass;
    RadioButton emptive;
    RadioButton nonEmptive;
    EditText quantumTime;
    TableLayout processTable;
    TableLayout summaryTable;
    CpuQueueView cpuQueueView;
    ConstraintLayout outputContainer;
    TextView avgTurnAround;
    TextView avgWaiting;
    TextView priorityInfo;
    Button add;
    Button remove;
    Button go;
    ScrollView scrollView;

    List<TableRow> rows;
    Input[] input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_scheduling);

        initViews();

        rows = new ArrayList<>();
        algoritms = new String[]{"Select algorithm", "FCFS", "SJF", "Priority", "Round robin"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_textview, algoritms);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        algoClass.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                TableRow newRow = (TableRow) LayoutInflater.from(com.example.os_algo.ProcessScheduling.this).inflate(R.layout.process_row, null);
                processTable.addView(newRow);
                rows.add(newRow);
                ((TextView) (newRow.findViewById(R.id.pid))).setText("p" + (processTable.getChildCount() - 1));
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = processTable.getChildCount();
                if (len > 2)
                    processTable.removeViewAt(len - 1);
                else
                    Toast.makeText(com.example.os_algo.ProcessScheduling.this, "At least one row required", Toast.LENGTH_LONG).show();
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                getOuput();
            }
        });

        algoClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setUpBlank();
                    case 1:
                        setUpFCFS();
                        break;
                    case 4:
                        setUpRoundRobin();
                        break;
                    case 3:
                        setUpPriority();
                        break;
                    case 2:
                        setUpSJF();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void setUpFCFS() {
        algoSubclass.setVisibility(View.GONE);
        quantumTime.setVisibility(View.GONE);
        priorityInfo.setVisibility(VISIBLE);
    }

    void setUpPriority() {
        algoSubclass.setVisibility(VISIBLE);
        quantumTime.setVisibility(View.GONE);
        priorityInfo.setVisibility(View.GONE);
    }

    void setUpSJF() {
        algoSubclass.setVisibility(VISIBLE);
        quantumTime.setVisibility(View.GONE);
        priorityInfo.setVisibility(VISIBLE);
    }

    void setUpRoundRobin() {
        algoSubclass.setVisibility(View.GONE);
        priorityInfo.setVisibility(VISIBLE);
        quantumTime.setVisibility(VISIBLE);
    }

    void setUpBlank() {
        algoSubclass.setVisibility(View.GONE);
        quantumTime.setVisibility(View.GONE);
        priorityInfo.setVisibility(View.GONE);
    }

    private void initViews() {
        algoClass = findViewById(R.id.algo_class);
        algoSubclass = findViewById(R.id.algo_subclass);
        emptive = findViewById(R.id.emptive);
        nonEmptive = findViewById(R.id.non_emptive);
        quantumTime = findViewById(R.id.quantum);
        processTable = findViewById(R.id.processTable);
        summaryTable = findViewById(R.id.summaryTable);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        go = findViewById(R.id.go);
        cpuQueueView = findViewById(R.id.cpu_queue);
        avgTurnAround = findViewById(R.id.avgTurnAround);
        avgWaiting = findViewById(R.id.avgWaiting);
        outputContainer = findViewById(R.id.outputContainer);
        priorityInfo = findViewById(R.id.textView6);
        scrollView = findViewById(R.id.scrollView2);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    void getOuput() {
        int type = algoClass.getSelectedItemPosition();
        if (type == 0) {
            Toast.makeText(this, "Please select algorithm type", Toast.LENGTH_LONG).show();
        } else {
            int len = processTable.getChildCount();
            input = new Input[len - 1];
            for (int i = 1; i < len; i++) {
                TableRow row = (TableRow) processTable.getChildAt(i);
                Input in = new Input();
                String pname = ((EditText) row.findViewById(R.id.pid)).getText().toString();
                if (pname.compareTo("") == 0) {
                    Toast.makeText(this, "Please enter process name", Toast.LENGTH_LONG).show();
                    return;
                }
                in.setpName(pname);
                try {
                    in.setaTime(Integer.parseInt(((EditText) row.findViewById(R.id.atime)).getText().toString()));
                } catch (NumberFormatException e) {
                    row.findViewById(R.id.atime).requestFocus();
                    Toast.makeText(this, "Please enter an integer", Toast.LENGTH_LONG).show();
                    outputContainer.setVisibility(View.GONE);
                    return;
                }
                try {
                    in.setbTime(Integer.parseInt(((EditText) row.findViewById(R.id.btime)).getText().toString()));
                } catch (NumberFormatException e) {

                    row.findViewById(R.id.btime).requestFocus();
                    Toast.makeText(this, "Please enter an integer", Toast.LENGTH_LONG).show();
                    outputContainer.setVisibility(View.GONE);
                    return;
                }
                try {
                    in.setPriority(Integer.parseInt(((EditText) row.findViewById(R.id.priority)).getText().toString()));
                } catch (NumberFormatException e) {
                     if (type == 3) {
                        row.findViewById(R.id.priority).requestFocus();
                        Toast.makeText(this, "Please enter an integer", Toast.LENGTH_LONG).show();
                        outputContainer.setVisibility(View.GONE);
                        return;
                    }
                }
                input[i - 1] = in;
            }
            Output[] output = null;
            List<Integer> cpuQueue = null;
            int choice;
            switch (type) {
                case 1:
                    FCFS fcfs = new FCFS();
                    output = fcfs.getOutput(input);
                    cpuQueue = fcfs.getCpuQueue();
                    break;
                case 2:
                    SJF sjf = new SJF();
                    choice = algoSubclass.getCheckedRadioButtonId();
                    if (choice == R.id.non_emptive)
                        output = sjf.getNonPreemptive(input);
                    else
                        output = sjf.getPreemptive(input);
                    cpuQueue = sjf.getCpuQueue();
                    break;
                case 3:
                    PriorityBased priority = new PriorityBased();
                    choice = algoSubclass.getCheckedRadioButtonId();
                    if (choice == R.id.non_emptive)
                        output = priority.getNonPreemptive(input);
                    else
                        output = priority.getPreemptive(input);
                    cpuQueue = priority.getCpuQueue();
                    break;
                case 4:
                    RoundRobin roundRobin = new RoundRobin();
                    try {
                        output = roundRobin.getOutput(input, Integer.parseInt(quantumTime.getText().toString()));
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Please enter quantum time in integer", Toast.LENGTH_LONG).show();
                        return;
                    }
                    cpuQueue = roundRobin.getCpuQueue();
                    break;
            }
            outputContainer.setVisibility(VISIBLE);
            if (type == 3) {
                summaryTable.getChildAt(0).findViewById(R.id.priority).setVisibility(VISIBLE);
            } else
                summaryTable.getChildAt(0).findViewById(R.id.priority).setVisibility(View.GONE);
            for (int i = 0; i < len - 1; i++) {
                TableRow row = (TableRow) summaryTable.getChildAt(i + 1);
                if (row == null) {
                    row = (TableRow) LayoutInflater.from(this).inflate(R.layout.summary_row, null);
                    summaryTable.addView(row);
                }
                if (type == 3) {
                    row.findViewById(R.id.priority).setVisibility(VISIBLE);
                    ((TextView) (row.findViewById(R.id.priority))).setText(String.valueOf(input[i].getPriority()));
                } else
                    row.findViewById(R.id.priority).setVisibility(View.GONE);
                ((TextView) (row.findViewById(R.id.pid))).setText(input[i].getpName());
                ((TextView) (row.findViewById(R.id.atime))).setText(String.valueOf(input[i].getaTime()));
                ((TextView) (row.findViewById(R.id.btime))).setText(String.valueOf(input[i].getbTime()));
                ((TextView) (row.findViewById(R.id.turnaround))).setText(String.valueOf(output[i].getTurnAround()));
                ((TextView) (row.findViewById(R.id.waiting))).setText(String.valueOf(output[i].getWaiting()));
            }

            int len2 = summaryTable.getChildCount();
            if (len2 > len) {
                for (int i = len; i < len2; i++) {
                    summaryTable.removeViewAt(len);
                }
            }
            cpuQueueView.setUp(cpuQueue, input);
            avgWaiting.setText("Average waiting time : " + Output.getAverageWaitingTime(output));
            avgTurnAround.setText("Average turn around time : " + Output.getAverageTurnAround(output));


            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
            scrollView.smoothScrollTo(0,summaryTable.getTop());
        }
    }

}