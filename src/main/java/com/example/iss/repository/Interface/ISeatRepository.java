package com.example.iss.repository.Interface;

import com.example.iss.model.Seat;

public interface ISeatRepository {
    public Seat findSeatByNumber(Integer number);
    public Iterable<Seat> findSeatByRow(Integer row);
    public Iterable<Seat> findSeatByColumn(Integer column);
    public Seat findSeatByRowAndColumns(Integer column,Integer row);

    public Iterable<Seat> findSeatByState(String state);
    public Iterable<Seat> findSeatByPrice(Integer price);
    public Iterable<Seat> findSeatByTypeSeat(String typeSeat);
    Seat reserveSeat(Integer number);
    Seat cancelSeat(Integer number);
    Seat update (Seat seat);
    Seat buySeat(Integer number);
    Iterable<Seat> findAll();

    Iterable<Seat> findAllSeatByUser(Integer id);
}
