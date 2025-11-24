package com.stream.example;

class Root {
    private int id;
    private String name;
    private Child child1;
    private Child child2;

    public Child getChild3() {
        return child3;
    }

    public void setChild3(Child child3) {
        this.child3 = child3;
    }

    public Child getChild2() {
        return child2;
    }

    public void setChild2(Child child2) {
        this.child2 = child2;
    }

    public Child getChild1() {
        return child1;
    }

    public void setChild1(Child child1) {
        this.child1 = child1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Child child3;
}
