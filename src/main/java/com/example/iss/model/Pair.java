package com.example.iss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

//@Embeddable

public class Pair {
    Integer first;
    Integer second;
    public Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {

    }

    @Column(name = "first")
    public Integer getFirst() {
        return first;
    }
    public void setFirst(Integer first) {
        this.first = first;
    }
    @Column(name = "second")
    public Integer getSecond() {
        return second;
    }
    public void setSecond(Integer second) {
        this.second = second;
    }

}
