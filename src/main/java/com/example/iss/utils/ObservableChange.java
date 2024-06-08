package com.example.iss.utils;

import com.example.iss.model.Seat;
import com.example.iss.model.State;

import java.util.ArrayList;
import java.util.List;

public class ObservableChange implements IChange,Observable<SeatEvent>{
    private List<Observer<SeatEvent>> observers=new ArrayList<>();
    private SeatEvent event;

    @Override
    public void reserved(Seat seat ) {
        event=new SeatEvent(SeatEventType.RESERVED,seat);
        notifyObservers(event);
    }

    @Override
    public void unreserved(Seat seat) {
        event=new SeatEvent(SeatEventType.UNRESERVED,seat);
        notifyObservers(event);
    }

    @Override
    public void addObserver(Observer<SeatEvent> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<SeatEvent> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(SeatEvent event) {
        for(Observer<SeatEvent> o:observers){
            o.update(event);
        }
    }
}
