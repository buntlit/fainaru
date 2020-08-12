package com.example.fainaruappu.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    List<Integer> count;
    List<String> url;


    public Model(){
        count = new ArrayList<>();
        url = new ArrayList<>();
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<Integer> getCount() {
        return count;
    }

}

