package com.example.iss.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
//@MappedSuperclass
public class GenericEntity<ID extends Serializable> {
    protected ID id;
//@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//@Column(name = "id", nullable = false)
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(!(obj instanceof GenericEntity)) return false;
        GenericEntity<?> entity=(GenericEntity<?>)obj;
        return getId().equals(entity.getId());
    }
}
