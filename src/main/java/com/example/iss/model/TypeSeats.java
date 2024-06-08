package com.example.iss.model;

import lombok.Getter;

@Getter
public enum TypeSeats {
    BOX("box")
    ,BALCONY("balcony"),
    DRESS_CIRCLE("dress_circle");
    private final String type;
    TypeSeats(String type) {
        this.type = type;
    }

    public static TypeSeats fromString(String type) {
        for (TypeSeats typeSeats : TypeSeats.values()) {
            if (typeSeats.getType().equalsIgnoreCase(type)) {
                return typeSeats;
            }
        }
        throw new IllegalArgumentException("Nu există un tip de scaun pentru: " + type);
    }
}
