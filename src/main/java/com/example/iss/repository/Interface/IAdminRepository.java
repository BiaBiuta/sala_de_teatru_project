package com.example.iss.repository.Interface;

import com.example.iss.model.Admin;
import com.example.iss.model.Auditorium;
import com.example.iss.model.User;

public interface IAdminRepository {
    public Admin findUserById(Integer id);
    public User findUserByUsername(String username);
    public User findUserByEmail(String email);
    User createUser(User user);
    User updateUser(User user);
    User deleteUser(Integer id);
    Iterable<User>  findAll();
    Auditorium findAudithoriumById(Integer id);
    Auditorium addPlaces(Integer id, Integer places);
    Auditorium removePlaces(Integer id, Integer places);
    Auditorium updateAuditorium(Auditorium auditorium);
    Auditorium deleteAuditorium(Integer id);
}
