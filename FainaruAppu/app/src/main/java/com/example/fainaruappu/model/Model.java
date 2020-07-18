package com.example.fainaruappu.model;

import java.util.Arrays;
import java.util.List;

public class Model {


    private List<Integer> count = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List count) {
        this.count = count;
    }
}

