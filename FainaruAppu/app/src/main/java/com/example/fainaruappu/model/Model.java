package com.example.fainaruappu.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    List<Integer> count;


    public Model(){
        count = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            count.add(0);
        }
    }

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List count) {
        this.count = count;
    }

}

