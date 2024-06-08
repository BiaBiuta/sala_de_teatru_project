package com.example.iss.repository;

import com.example.iss.model.*;
import com.example.iss.repository.Interface.ISeatRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SeatRepository implements ISeatRepository {
    private final JdbcUtils jdbcUtils;
    private final AuditoriumRepository auditoriumRepository;
    private final UserRepository userRepository;
    //private static final Logger logger= LogManager.getLogger();
    public SeatRepository(Properties properties, AuditoriumRepository auditoriumRepository, UserRepository userRepository){
        this.jdbcUtils=new JdbcUtils(properties);
        this.auditoriumRepository=auditoriumRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Seat findSeatByNumber(Integer number) {
        Connection con = jdbcUtils.getConnection();
        Seat seat=null;
        try(PreparedStatement preparedStatement=con.prepareStatement("select * from seats where number=?")){
            preparedStatement.setInt(1,number);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                if(resultSet.next()){
                    Integer id=resultSet.getInt("id");
                    Integer first=resultSet.getInt("first");
                    Integer second=resultSet.getInt("second");
                    Integer number1=resultSet.getInt("number");
                    String type_Seat=resultSet.getString("type_seat");
                    Double price=resultSet.getDouble("price");
                    String state=resultSet.getString("state");
                    Integer id_user=resultSet.getInt("id_user");
                    Integer id_auditorium=resultSet.getInt("id_auditorium");
                    User user=userRepository.findUserById(id_user);
                    Auditorium auditorium=auditoriumRepository.findAuditoriumById(id_auditorium);
                    seat=new Seat(first,second,number1, TypeSeats.fromString(type_Seat),State.valueOf(state),price,user,auditorium);
                    seat.setId(id);
                }
            }
        }catch (Exception e){
            System.out.println("Error DB "+e);
            return null;
        }
        return seat;
    }

    @Override
    public Iterable<Seat> findSeatByRow(Integer row) {
        Connection connection = jdbcUtils.getConnection();
        List<Seat> seats = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from seats where 'first'=?")) {
            preparedStatement.setInt(1, row);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer first = resultSet.getInt("first");
                    Integer second = resultSet.getInt("second");
                    Integer number = resultSet.getInt("number");
                    String type_Seat = resultSet.getString("type_seat");
                    Double price = resultSet.getDouble("price");
                    String state = resultSet.getString("state");
                    Integer id_user = resultSet.getInt("id_user");
                    Integer id_auditorium = resultSet.getInt("id_auditorium");
                    User user = userRepository.findUserById(id_user);
                    Auditorium auditorium = auditoriumRepository.findAuditoriumById(id_auditorium);
                    Seat seat = new Seat(first, second, number, TypeSeats.fromString(type_Seat), State.valueOf(state), price, user, auditorium);
                    seat.setId(id);
                    seats.add(seat);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return seats;
    }


    @Override
    public Iterable<Seat> findSeatByColumn(Integer column) {
        Connection connection = jdbcUtils.getConnection();
        List<Seat> seats = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from seats where 'second'=?")) {
            preparedStatement.setInt(1, column);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer first = resultSet.getInt("first");
                    Integer second = resultSet.getInt("second");
                    Integer number = resultSet.getInt("number");
                    String type_Seat = resultSet.getString("type_seat");
                    Double price = resultSet.getDouble("price");
                    String state = resultSet.getString("state");
                    Integer id_user = resultSet.getInt("id_user");
                    Integer id_auditorium = resultSet.getInt("id_auditorium");
                    User user = userRepository.findUserById(id_user);
                    Auditorium auditorium = auditoriumRepository.findAuditoriumById(id_auditorium);
                    Seat seat = new Seat(first, second, number, TypeSeats.fromString(type_Seat), State.valueOf(state), price, user, auditorium);
                    seat.setId(id);
                    seats.add(seat);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return seats;
    }

    @Override
    public Seat findSeatByRowAndColumns(Integer column, Integer row) {
        Connection connection = jdbcUtils.getConnection();
        Seat seat = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from seats where 'first'=? and 'second'=?")) {
            preparedStatement.setInt(1, row);
            preparedStatement.setInt(2, column);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer first = resultSet.getInt("first");
                    Integer second = resultSet.getInt("second");
                    Integer number = resultSet.getInt("number");
                    String type_Seat = resultSet.getString("type_seat");
                    Double price = resultSet.getDouble("price");
                    String state = resultSet.getString("state");
                    Integer id_user = resultSet.getInt("id_user");
                    Integer id_auditorium = resultSet.getInt("id_auditorium");
                    User user = userRepository.findUserById(id_user);
                    Auditorium auditorium = auditoriumRepository.findAuditoriumById(id_auditorium);
                    seat = new Seat(first, second, number, TypeSeats.fromString(type_Seat), State.valueOf(state), price, user, auditorium);
                    seat.setId(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error DB " + e);
            return null;
        }
        return seat;
    }


    @Override
    public Iterable<Seat> findSeatByState(String state) {
        Connection connection=jdbcUtils.getConnection();
        List<Seat> seats=new ArrayList<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement("select * from seats where state=?")){
            preparedStatement.setString(1,state);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Integer id=resultSet.getInt("id");
                    Integer first=resultSet.getInt("first");
                    Integer second=resultSet.getInt("second");
                    Integer number=resultSet.getInt("number");
                    String type_Seat=resultSet.getString("type_seat");
                    Double price=resultSet.getDouble("price");
                    String state1=resultSet.getString("state");
                    Integer id_user=resultSet.getInt("id_user");
                    Integer id_auditorium=resultSet.getInt("id_auditorium");
                    User user=userRepository.findUserById(id_user);
                    Auditorium auditorium=auditoriumRepository.findAuditoriumById(id_auditorium);
                    Seat seat=new Seat(first,second,number,TypeSeats.fromString(type_Seat),State.valueOf(state1),price,user,auditorium);
                    seat.setId(id);
                    seats.add(seat);
                }
            }
        }catch (Exception e){
            System.out.println("Error DB "+e);
            return null;
        }
        return seats;
    }

    @Override
    public Iterable<Seat> findSeatByPrice(Integer price) {
        Connection connection=jdbcUtils.getConnection();
        List<Seat> seats=new ArrayList<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement("select * from seats where price=?")){
            preparedStatement.setInt(1,price);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Integer id=resultSet.getInt("id");
                    Integer first=resultSet.getInt("first");
                    Integer second=resultSet.getInt("second");
                    Integer number=resultSet.getInt("number");
                    String type_Seat=resultSet.getString("type_seat");
                    Double price1=resultSet.getDouble("price");
                    String state=resultSet.getString("state");
                    Integer id_user=resultSet.getInt("id_user");
                    Integer id_auditorium=resultSet.getInt("id_auditorium");
                    User user=userRepository.findUserById(id_user);
                    Auditorium auditorium=auditoriumRepository.findAuditoriumById(id_auditorium);
                    Seat seat=new Seat(first,second,number,TypeSeats.fromString(type_Seat),State.valueOf(state),price1,user,auditorium);
                    seat.setId(id);
                    seats.add(seat);
                }
            }
        }catch (Exception e){
            System.out.println("Error DB "+e);
            return null;
        }
        return seats;
    }

    @Override
    public Iterable<Seat> findSeatByTypeSeat(String typeSeat) {
        Connection connection=jdbcUtils.getConnection();
        List<Seat> seats=new ArrayList<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement("select * from seats where type_seat=?")){
            preparedStatement.setString(1,typeSeat);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Integer id=resultSet.getInt("id");
                    Integer first=resultSet.getInt("first");
                    Integer second=resultSet.getInt("second");
                    Integer number=resultSet.getInt("number");
                    String type_Seat=resultSet.getString("type_seat");
                    Double price=resultSet.getDouble("price");
                    String state=resultSet.getString("state");
                    Integer id_user=resultSet.getInt("id_user");
                    Integer id_auditorium=resultSet.getInt("id_auditorium");
                    User user=userRepository.findUserById(id_user);
                    Auditorium auditorium=auditoriumRepository.findAuditoriumById(id_auditorium);
                    Seat seat=new Seat(first,second,number,TypeSeats.fromString(type_Seat),State.valueOf(state),price,user,auditorium);
                    seat.setId(id);
                    seats.add(seat);
                }
            }
        }catch (Exception e){
            System.out.println("Error DB "+e);
            return null;
        }
        return seats;
    }

    @Override
    public Seat reserveSeat(Integer number) {
        Seat seat=findSeatByNumber(number);
        if(seat==null){
            return null;
        }
        if(seat.getStateOfSeats().equals(State.AVAILABLE)){
            Connection connection=jdbcUtils.getConnection();
            try(PreparedStatement preparedStatement=connection.prepareStatement("update seats set state='RESERVED' where number=?")){
                preparedStatement.setInt(1,number);
                int result=preparedStatement.executeUpdate();
                if(result==0){
                    return null;
                }
            }catch (Exception e){
                System.out.println("Error DB "+e);
                return null;
            }
            return seat;
        }
        return seat;
    }

    @Override
    public Seat cancelSeat(Integer number) {
        //ma uit sa vad daca scaunul e rezervat de catre acel user care incearca sa il faca unavailable
        Seat seat=findSeatByNumber(number);
        if(seat==null){
            return null;
        }
        if(seat.getStateOfSeats().equals(State.RESERVED)){
            Connection connection=jdbcUtils.getConnection();
            try(PreparedStatement preparedStatement=connection.prepareStatement("update seats set state='AVAILABLE' where number=?")){
                preparedStatement.setInt(1,number);
                int result=preparedStatement.executeUpdate();
                if(result==0){
                    return null;
                }
            }catch (Exception e){
                System.out.println("Error DB "+e);
                return null;
            }
            return seat;
        }
        return seat;
    }

    @Override
    public Seat update(Seat seat) {
        Connection connection=jdbcUtils.getConnection();
        try(PreparedStatement preparedStatement=connection.prepareStatement("update seats set first=?,second=?,number=?,type_seat=?,price=?,state=?,id_user=?,id_auditorium=? where id=?")){
            preparedStatement.setInt(1,seat.getFirst());
            preparedStatement.setInt(2,seat.getSecond());
            preparedStatement.setInt(3,seat.getNumber());
            preparedStatement.setString(4,seat.getType().getType());
            preparedStatement.setDouble(5,seat.getPrice());
            preparedStatement.setString(6,seat.getStateOfSeats().getState());
            preparedStatement.setInt(7,seat.getUser().getId());
            preparedStatement.setInt(8,seat.getAuditorium().getId());
            preparedStatement.setInt(9,seat.getId());
            int result=preparedStatement.executeUpdate();
            if(result==0){
                return null;
            }
        }catch (Exception e){
            System.out.println("Error DB "+e);
            return null;
        }
        return seat;
    }

    @Override
    public Seat buySeat(Integer number) {
        return null;
    }

    @Override
    public Iterable<Seat> findAll() {
        Connection connection=jdbcUtils.getConnection();
        List<Seat> seats=new ArrayList<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement("select * from seats")){
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Integer id=resultSet.getInt("id");
                    Integer first=resultSet.getInt("first");
                    Integer second=resultSet.getInt("second");
                    Integer number=resultSet.getInt("number");
                    String type_Seat=resultSet.getString("type_seat");
                    Double price=resultSet.getDouble("price");
                    String state=resultSet.getString("state");
                    Integer id_user=resultSet.getInt("id_user");
                    Integer id_auditorium=resultSet.getInt("id_auditorium");
                    User user=userRepository.findUserById(id_user);
                    Auditorium auditorium=auditoriumRepository.findAuditoriumById(id_auditorium);
                    Seat seat=new Seat(first,second,number,TypeSeats.fromString(type_Seat),State.valueOf(state),price,user,auditorium);
                    seat.setId(id);
                    seats.add(seat);
                }
            }
        }catch (Exception e){
            System.out.println("Error DB "+e);
            return null;
        }
        return seats;
    }

    @Override
    public Iterable<Seat> findAllSeatByUser(Integer id) {
        Connection connection=jdbcUtils.getConnection();
        List<Seat> seats=new ArrayList<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement("select * from seats where id_user=?")){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Integer id1=resultSet.getInt("id");
                    Integer first=resultSet.getInt("first");
                    Integer second=resultSet.getInt("second");
                    Integer number=resultSet.getInt("number");
                    String type_Seat=resultSet.getString("type_seat");
                    Double price=resultSet.getDouble("price");
                    String state=resultSet.getString("state");
                    Integer id_user=resultSet.getInt("id_user");
                    Integer id_auditorium=resultSet.getInt("id_auditorium");
                    User user=userRepository.findUserById(id_user);
                    Auditorium auditorium=auditoriumRepository.findAuditoriumById(id_auditorium);
                    Seat seat=new Seat(first,second,number,TypeSeats.fromString(type_Seat),State.valueOf(state),price,user,auditorium);
                    seat.setId(id1);
                    seats.add(seat);
                }
            }
        }catch (Exception e){
            System.out.println("Error DB "+e);
            return null;
        }
        return seats;
    }

}
