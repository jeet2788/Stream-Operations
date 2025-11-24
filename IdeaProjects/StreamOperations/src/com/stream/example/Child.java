package com.stream.example;

import java.util.List;

class Child {
    private int id;
    private String name;

    public List<GrandChild> getChildren() {
        return children;
    }

    public void setChildren(List<GrandChild> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<GrandChild> children;
}
