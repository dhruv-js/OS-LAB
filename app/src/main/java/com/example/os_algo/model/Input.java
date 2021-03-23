package com.example.os_algo.model;

public class Input {
    private String pName;//Process Name
    private int priority;//Priority
    private int aTime;//Arrival Time
    private int bTime;//Burst Time

    //COPY CONSTRUCTOR
    public Input(Input in) {
        pName = in.pName;
        aTime = in.aTime;
        bTime = in.bTime;
        priority = in.priority;
    }

    //DEFAULT CONSTRUCTOR
    public Input() {

    }

    //COMPARE TWO Input Objects on the basis of arrival time
    public int compareTo(Input o) {
        return Integer.compare(aTime, o.aTime);
    }

    //GETTERS AND SETTERS
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getaTime() {
        return aTime;
    }

    public void setaTime(int aTime) {
        this.aTime = aTime;
    }

    public int getbTime() {
        return bTime;
    }

    public void setbTime(int bTime) {
        this.bTime = bTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
