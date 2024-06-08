package com.example.iss.utils;

import com.example.iss.model.Seat;

public interface IChange {
    void reserved(Seat seat);
    void unreserved(Seat seat);
}
