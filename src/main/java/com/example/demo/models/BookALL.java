package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class BookALL {
    private String name;
    private List<MapBook> list = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MapBook> getList() {
        return list;
    }

    public void setList(List<MapBook> list) {
        this.list = list;
    }
}
