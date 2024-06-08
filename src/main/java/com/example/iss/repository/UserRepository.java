package com.example.iss.repository;

import com.example.iss.model.User;
import com.example.iss.repository.Interface.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepository  implements IUserRepository {

    private final JdbcUtils jdbcUtils;
    public UserRepository(Properties props){
        this.jdbcUtils = new JdbcUtils(props);
    }
    @Override
    public User findUserById(Integer id) {
        Connection con = jdbcUtils.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from users where id_user=?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String cnp = resultSet.getString("cnp");
                    String codIban = resultSet.getString("cod_iban");
                    String address = resultSet.getString("address");
                    String email = resultSet.getString("email");
                    user = new User(name, username, password, email, cnp, address, codIban);
                    user.setId(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        Connection con = jdbcUtils.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from users where username=?")) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id_user");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String cnp = resultSet.getString("cnp");
                    String codIban = resultSet.getString("cod_iban");
                    String address = resultSet.getString("address");
                    String email = resultSet.getString("email");
                    user = new User(name, username, password, email, cnp, address, codIban);
                    user.setId(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        Connection con = jdbcUtils.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from users where email=?")) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id_user");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String cnp = resultSet.getString("cnp");
                    String codIban = resultSet.getString("cod_iban");
                    String address = resultSet.getString("address");
                    user = new User(name, username, password, email, cnp, address, codIban);
                    user.setId(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return user;
    }

    @Override
    public User findUserByPasswordAndUsername(String password, String username) {
        Connection con = jdbcUtils.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from users where password=? and username=?")) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id_user");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String cnp = resultSet.getString("cnp");
                    String codIban = resultSet.getString("cod_iban");
                    String address = resultSet.getString("address");
                    user = new User(name, username, password, email, cnp, address, codIban);
                    user.setId(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return user;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        Connection con = jdbcUtils.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from users where email=? and password=?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id_user");
                    String username = resultSet.getString("username");
                    String name = resultSet.getString("name");
                    String cnp = resultSet.getString("cnp");
                    String codIban = resultSet.getString("cod_iban");
                    String address = resultSet.getString("address");
                    user = new User(name, username, password, email, cnp, address, codIban);
                    user.setId(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("insert into users(username, password, name) values(?,?,?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            int rowAffected=preparedStatement.executeUpdate();
            if(rowAffected==1){
                try (Statement statement = con.createStatement()) {
                    try (ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid()")) {
                        if (resultSet.next()) {
                            int id = resultSet.getInt(1);
                            user.setId(id);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("update users set username=?, password=?, name=?,cnp=?,cod_iban=?,address=?,email=? where id_user=?")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getCnp());
            preparedStatement.setString(5, user.getCodIban());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setInt(8, user.getId());
            int rowAffected=preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return user;
    }

    @Override
    public User deleteUser(Integer id) {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("delete from users where id_user=?")) {
            preparedStatement.setInt(1, id);
            int rowAffected=preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        Connection con = jdbcUtils.getConnection();
        List<User> users = new ArrayList<>();
        try (Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select * from users")) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id_user");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String cnp = resultSet.getString("cnp");
                    String codIban = resultSet.getString("cod_iban");
                    String address = resultSet.getString("address");
                    String email = resultSet.getString("email");
                    User user = new User(name, username, password, email, cnp, address, codIban);
                    user.setId(id);
                    users.add(user);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
        }
        return users;

    }
}
