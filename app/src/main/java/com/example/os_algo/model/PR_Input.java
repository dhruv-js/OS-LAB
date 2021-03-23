package com.example.os_algo.model;

public class PR_Input {
    private int frame;
    private int[] page;

    public PR_Input(PR_Input pr_input)
    {frame = pr_input.frame;
     page = pr_input.page;
    }
    public PR_Input() {

    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public void setPage(int[] page) {
        this.page = page;
    }

    public int getFrame() {
        return frame;
    }

    public int[] getPage() {
        return page;
    }
}
