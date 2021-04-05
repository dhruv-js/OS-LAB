package com.example.os_algo.model;

public class PR_Output {
    private int fault;
    private String[] checkFault;
    private int[][] result;

    public void setCheckFault(String[] checkFault) {
        this.checkFault = checkFault;
    }

    public void setFault(int fault) {
        this.fault = fault;
    }

    public void setResult(int[][] result) {
        this.result = result;
    }

    public String[] getCheckFault() {
        return checkFault;
    }

    public int getFault() {
        return fault;
    }

    public int[][] getResult() {
        return result;
    }
}
