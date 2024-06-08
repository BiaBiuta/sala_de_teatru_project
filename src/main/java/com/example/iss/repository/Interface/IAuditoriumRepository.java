package com.example.iss.repository.Interface;

import com.example.iss.model.Auditorium;

public interface IAuditoriumRepository {
    public Auditorium findAuditoriumById(Integer id) ;

    public Auditorium findAuditoriumByName(String name) ;

    public Auditorium createAuditorium(Auditorium auditorium) ;

    public Auditorium updateAuditorium(Auditorium auditorium) ;

    public Auditorium deleteAuditorium(Integer id) ;

    public Iterable<Auditorium> findAll();
}
