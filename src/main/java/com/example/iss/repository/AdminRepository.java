package com.example.iss.repository;

import com.example.iss.model.Admin;
import com.example.iss.model.Auditorium;
import com.example.iss.model.User;
import com.example.iss.repository.Interface.IAdminRepository;

import java.util.Properties;

public class AdminRepository implements IAdminRepository {
    private final JdbcUtils jdbcUtils;
    public AdminRepository(Properties properties){
        this.jdbcUtils=new JdbcUtils(properties);
    }
    @Override
    public Admin findUserById(Integer id) {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(Integer id) {
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Auditorium findAudithoriumById(Integer id) {
        return null;
    }

    @Override
    public Auditorium addPlaces(Integer id, Integer places) {
        return null;
    }

    @Override
    public Auditorium removePlaces(Integer id, Integer places) {
        return null;
    }

    @Override
    public Auditorium updateAuditorium(Auditorium auditorium) {
        return null;
    }

    @Override
    public Auditorium deleteAuditorium(Integer id) {
        return null;
    }
}
