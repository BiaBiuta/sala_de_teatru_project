package com.example.iss.model;

import lombok.Getter;

@Getter
public enum State {
    RESERVED("RESERVED"),
    AVAILABLE("AVAILABLE"),
    IN_PROCESS("IN_PROCESS"),
    CANCELED("CANCELED");
    private final String state;

    State(String state) {
        this.state = state;
    }
    public static State fromString(String state) {
        for (State state1 : State.values()) {
            if (state1.getState().equalsIgnoreCase(state)) {
                return state1;
            }
        }
        throw new IllegalArgumentException("Nu există un tip de scaun pentru: " + state);
    }
}
