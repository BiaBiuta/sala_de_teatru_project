package com.example.iss.repository;

import com.example.iss.model.Auditorium;
import com.example.iss.repository.Interface.IAuditoriumRepository;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class AuditoriumRepository implements IAuditoriumRepository {
private final JdbcUtils jdbcUtils;
    public AuditoriumRepository(Properties properties){
        this.jdbcUtils=new JdbcUtils(properties);
    }

    public Auditorium findAuditoriumById(Integer id) {
        Connection con = jdbcUtils.getConnection();
        Auditorium auditorium = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from auditorium where `id-auditorium`=?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer numberOfEmptySeats = resultSet.getInt("numberOfEmptySeats");
                    String namePerformance = resultSet.getString("name_performance");
                    auditorium = new Auditorium(numberOfEmptySeats, namePerformance);
                    auditorium.setId(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return auditorium;
    }

    public Auditorium findAuditoriumByName(String name) {
        Connection con = jdbcUtils.getConnection();
        Auditorium auditorium = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from auditorium where name_performance=?")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id-auditorium");
                    Integer numberOfEmptySeats = resultSet.getInt("numberOfEmptySeats");
                    auditorium = new Auditorium(numberOfEmptySeats, name);
                    auditorium.setId(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return auditorium;
    }

    public Auditorium createAuditorium(Auditorium auditorium) {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("insert into auditorium (numberOfEmptySeats, name_performance) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, auditorium.getNumberOfSeats());
            preparedStatement.setString(2, auditorium.getNamePerformance());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    auditorium.setId(generatedKeys.getInt(1));
                    return auditorium;
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return null;
    }

    public Auditorium updateAuditorium(Auditorium auditorium) {

        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("update auditorium set numberOfEmptySeats=?, name_performance=? where 'id-auditorium'=?")) {
            preparedStatement.setInt(1, auditorium.getNumberOfSeats());
            preparedStatement.setString(2, auditorium.getNamePerformance());
            preparedStatement.setInt(3, auditorium.getId());
            preparedStatement.executeUpdate();
            return auditorium;
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
    }

    public Auditorium deleteAuditorium(Integer id) {
        Connection con = jdbcUtils.getConnection();
        Auditorium auditorium = findAuditoriumById(id);
        if (auditorium == null) {
            return null;
        }
        try (PreparedStatement preparedStatement = con.prepareStatement("delete from auditorium where 'id-auditorium'=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return auditorium;
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
    }

    public Iterable<Auditorium> findAll() {
        Connection con =jdbcUtils.getConnection();
        List<Auditorium> auditoriums = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("select * from auditorium")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id-auditorium");
                    Integer numberOfEmptySeats = resultSet.getInt("numberOfEmptySeats");
                    String namePerformance = resultSet.getString("name_performance");
                    Auditorium auditorium = new Auditorium(numberOfEmptySeats, namePerformance);
                    auditorium.setId(id);
                    auditoriums.add(auditorium);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return auditoriums;
    }

}
