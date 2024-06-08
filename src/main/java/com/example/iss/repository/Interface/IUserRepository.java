package com.example.iss.repository.Interface;

import com.example.iss.model.User;

public interface IUserRepository {
    public User findUserById(Integer id);
    public User findUserByUsername(String username);
    public User findUserByEmail(String email);
    public User findUserByPasswordAndUsername(String password, String username);
    User findUserByEmailAndPassword(String email, String password);
    User createUser(User user);
    User updateUser(User user);
    User deleteUser(Integer id);
    Iterable<User>  findAll();
}
