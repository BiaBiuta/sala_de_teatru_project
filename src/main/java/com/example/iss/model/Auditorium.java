package com.example.iss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
//@Entity
//@Table(name = "auditorium")
public class Auditorium  extends GenericEntity<Integer> {
    public Auditorium() {
    }

    Integer numberOfSeats;
    String namePerformance;
//    @Getter
    List<Seat> seats;
//    @Column(name = "numberOfEmptySeats")
    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
//    @Column(name = "name_performance")
    public String getNamePerformance() {
        return namePerformance;
    }

    public void setNamePerformance(String namePerformance) {
        this.namePerformance = namePerformance;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Auditorium(Integer numberOfSeats, String namePerformance) {
        this.numberOfSeats = numberOfSeats;
        this.namePerformance = namePerformance;
        this.seats=new ArrayList<>();
    }
}
