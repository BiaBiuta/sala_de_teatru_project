package com.example.iss.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ManyToAny;
//@Entity
//@Table(name = "seats")

//@Getter
public class Seat extends GenericEntity<Integer> {

    //    @Embedded
    //    @AttributeOverrides({
    //            @AttributeOverride(name = "first", column = @Column(name = "first")),
    //            @AttributeOverride(name = "second", column = @Column(name = "second"))
    //    })
    //    public Pair getPosition() {
    //        return position;
    //    }
    //@Column(name = "first")
    //private Pair position;
//    @Getter
    private Integer first;
    private Integer second;
    //    public void setPosition(Pair position) {
    //        this.position = position;
    //    }
    //    @Column(name = "number")
    private Integer number;

    //    @Enumerated(EnumType.STRING)
    //    @Column(name = "type_seat")
    private TypeSeats type;


    //    @Enumerated(EnumType.STRING)
    //    @Column(name = "state")
    private State stateOfSeats;
    //    @Column(name = "price")
    private Double price;

    private User user;
    //    @ManyToOne
    //    @JoinColumn(name = "id_auditorium")
    private Auditorium auditorium;

    public Seat(Integer first ,Integer second, Integer number, TypeSeats type, State stateOfSeats, Double price, User user , Auditorium auditorium) {
        this.first = first;
        this.second = second;
        this.number = number;
        this.type = type;
        this.stateOfSeats = stateOfSeats;
        this.price = price;
        this.user=user;
        this.auditorium=auditorium;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }
//    @Column(name = "second")

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Seat() {

    }

//    @ManyToOne
//    @JoinColumn(name = "id_user")

    public void setUser(User user) {
        this.user = user;
    }

    //    @Column(name = "first")
//    public Integer getFirst() {
//        return position.getFirst();
//    }
//    public void setFirst(Integer first) {
//        position.setFirst(first);
//    }
//    @Column(name = "second")
//    public Integer getSecond() {
//        return position.getSecond();
  //  }
    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setType(TypeSeats type) {
        this.type = type;
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }

    public Integer getNumber() {
        return number;
    }

    public TypeSeats getType() {
        return type;
    }

    public State getStateOfSeats() {
        return stateOfSeats;
    }

    public Double getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setStateOfSeats(State stateOfSeats) {
        this.stateOfSeats = stateOfSeats;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
