package com.example.iss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
//@Entity
//@Table(name = "users")
public class User extends Person  {

    private List<Seat> seats;
    private String email;
    private String cnp ;
    private String address;
    private String codIban;
    public User(String name, String username, String password) {
        super(name, username, password);
        this.seats=new ArrayList<>();
        this.cnp="";
        this.address="";
        this.codIban="";
        this.email="";
    }

    public User() {
        super();
    }


    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
//    @Column(name = "email")

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    @Column(name = "cnp")
    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
//    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
//    @Column(name = "cod_iban")
    public String getCodIban() {
        return codIban;
    }

    public void setCodIban(String codIban) {
        this.codIban = codIban;
    }

    public User(String name, String username, String password, String email, String cnp, String address, String codIban) {
        super(name, username, password);
        this.email = email;
        this.cnp = cnp;
        this.address = address;
        this.codIban = codIban;
        this.seats=new ArrayList<>();
    }

}
