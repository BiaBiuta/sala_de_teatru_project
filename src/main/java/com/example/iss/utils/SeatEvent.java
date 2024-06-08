package com.example.iss.utils;


import com.example.iss.model.Seat;
import com.example.iss.utils.SeatEventType;
import lombok.Getter;

@Getter
public class SeatEvent implements Event {
    private SeatEventType type;
    private Seat Seat;
    public SeatEvent(SeatEventType type, Seat Seat) {
        this.Seat=Seat;
        this.type=type;
    }

    public void setSeat(Seat Seat) {
        this.Seat = Seat;
    }

    public void setType(SeatEventType type) {
        this.type = type;
    }
}
