package com.busstation.controller.socket;

public class Seat {
    private int id;
    private boolean available;

    public Seat(int id, boolean available) {
        this.id = id;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
