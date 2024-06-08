package com.example.iss.service;

import com.example.iss.model.Auditorium;
import com.example.iss.model.Seat;
import com.example.iss.model.User;

import java.util.List;

public interface IService {
     Iterable<Seat>viewAllSeats();
    Iterable<Seat>viewAllSeatsFilter(String status);
    Seat cancelSeat(Integer id);
    Seat reservedSeat(Integer id);
    Seat addSeat(Seat seat);
    Seat updateSeat(Seat seat);
    Seat deleteSeat(Integer id);
    Seat findSeatById(Integer id);
    Seat findSeatByNumber(Integer number);
    Iterable<Seat> findSeatByRow(Integer row);
    Iterable<Seat> findSeatByColumn(Integer column);
    Seat findSeatByRowAndColumn(Integer column,Integer row);
    Iterable<Seat> viewAllSeatsByPrice(Integer price);
    Iterable<Seat> viewAllSeatsByTypeSeat(String typeSeat);
    Seat buySeat(Integer id);
    User createUser(User user);
    User updateUser(User user);
    User deleteUser(Integer id);
    List<User> findAllUsers();
    User findUserById(Integer id);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    User findUserByPasswordAndUsername(String password, String username);
    User findUserByEmailAndPassword(String email, String password);
    Auditorium addAuditorium(Auditorium auditorium);
    Auditorium updateAuditorium(Auditorium auditorium);
    Auditorium deleteAuditorium(Integer id);
    Auditorium findAuditoriumById(Integer id);
    Auditorium findAuditoriumByName(String name);
    Iterable<Auditorium> findAllAuditoriums();
    Iterable<Seat>findSeatByUser(Integer user);






}
