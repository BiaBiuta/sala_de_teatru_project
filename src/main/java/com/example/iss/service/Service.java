package com.example.iss.service;

import com.example.iss.model.Auditorium;
import com.example.iss.model.Seat;
import com.example.iss.model.State;
import com.example.iss.model.User;
import com.example.iss.repository.Interface.IAdminRepository;
import com.example.iss.repository.Interface.IAuditoriumRepository;
import com.example.iss.repository.Interface.ISeatRepository;
import com.example.iss.repository.Interface.IUserRepository;

import java.util.List;

public class Service implements IService{
    private  IAuditoriumRepository auditoriumRepository;
    private ISeatRepository seatRepository;
    private IUserRepository userRepository;
    private IAdminRepository adminRepository;

    public Service(IAuditoriumRepository auditoriumRepository, ISeatRepository seatRepository, IUserRepository userRepository, IAdminRepository adminRepository) {
        this.auditoriumRepository = auditoriumRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public Iterable<Seat> viewAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Iterable<Seat> viewAllSeatsFilter(String status) {
        return seatRepository.findSeatByState(status);
    }

    @Override
    public synchronized Seat cancelSeat(Integer id) {
        Seat seat = seatRepository.findSeatByNumber(id);
        seat.setStateOfSeats(State.valueOf("AVAILABLE"));
        seatRepository.update(seat);
        return seatRepository.cancelSeat(id);
    }

    @Override
    public Seat reservedSeat(Integer id) {
        Seat seat = seatRepository.findSeatByNumber(id);
        seat.setStateOfSeats(State.valueOf("RESERVED"));
        seatRepository.update(seat);
        return seatRepository.reserveSeat(id);
    }

    @Override
    public Seat addSeat(Seat seat) {
        return null;
    }

    @Override
    public Seat updateSeat(Seat seat) {
        return seatRepository.update(seat);
    }

    @Override
    public Seat deleteSeat(Integer id) {
        return null;
    }

    @Override
    public Seat findSeatById(Integer id) {
        return seatRepository.findSeatByNumber(id);
    }

    @Override
    public Seat findSeatByNumber(Integer number) {
        return seatRepository.findSeatByNumber(number);
    }

    @Override
    public Iterable<Seat> findSeatByRow(Integer row) {
        return seatRepository.findSeatByRow(row);
    }

    @Override
    public Iterable<Seat> findSeatByColumn(Integer column) {
        return seatRepository.findSeatByColumn(column);
    }

    @Override
    public Seat findSeatByRowAndColumn(Integer column, Integer row) {
        return  seatRepository.findSeatByRowAndColumns(column,row);
    }

    @Override
    public Iterable<Seat> viewAllSeatsByPrice(Integer price) {
        return seatRepository.findSeatByPrice(price);
    }

    @Override
    public Iterable<Seat> viewAllSeatsByTypeSeat(String typeSeat) {
        return seatRepository.findSeatByTypeSeat(typeSeat);
    }

    @Override
    public Seat buySeat(Integer id) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public User deleteUser(Integer id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByPasswordAndUsername(String password, String username) {
        return userRepository.findUserByPasswordAndUsername(password,username);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email,password);
    }

    @Override
    public Auditorium addAuditorium(Auditorium auditorium) {
        return auditoriumRepository.createAuditorium(auditorium);
    }

    @Override
    public Auditorium updateAuditorium(Auditorium auditorium) {
        return auditoriumRepository.updateAuditorium(auditorium);
    }

    @Override
    public Auditorium deleteAuditorium(Integer id) {
        return auditoriumRepository.deleteAuditorium(id);
    }

    @Override
    public Auditorium findAuditoriumById(Integer id) {
        return auditoriumRepository.findAuditoriumById(id);
    }

    @Override
    public Auditorium findAuditoriumByName(String name) {
        return auditoriumRepository.findAuditoriumByName(name);
    }

    @Override
    public Iterable<Auditorium> findAllAuditoriums() {
        return auditoriumRepository.findAll();
    }

    @Override
    public Iterable<Seat> findSeatByUser(Integer id) {
        return seatRepository.findAllSeatByUser(id);
    }

}
