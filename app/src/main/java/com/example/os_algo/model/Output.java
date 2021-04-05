package com.example.os_algo.model;

public class Output {
    private int turnAround;//Turn around tim    e
    private int waiting;//Waiting time

    //GETTERS AND SETTERS
    public int getTurnAround() {
        return turnAround;
    }

    public void setTurnAround(int turnAround) {
        this.turnAround = turnAround;
    }

    public int getWaiting() {
        return waiting;
    }

    public void setWaiting(int waiting) {
        this.waiting = waiting;
    }

    //GET AVERAGE TURN AROUND TIME OF LIST PASSED
    public static float getAverageTurnAround(Output[] outputs) {
        float avg = 0;
        for (Output o : outputs) {
            avg += o.turnAround;
        }
        avg /= outputs.length;
        return Float.valueOf(String.format("%.2f", avg));
    }

    //GET AVERAGE WAITING TIME OF LIST PASSED
    public static float getAverageWaitingTime(Output[] outputs) {
        float avg = 0;
        for (Output o : outputs) {
            avg += o.waiting;
        }
        avg /= outputs.length;
        return Float.valueOf(String.format("%.2f", avg));
    }
}
