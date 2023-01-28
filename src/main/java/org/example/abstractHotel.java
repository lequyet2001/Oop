package org.example;

import java.util.List;

public abstract class abstractHotel {
    abstract boolean addRoom(Room room);
    abstract String removeRoom(int roomNumber);
    abstract String updateRoom(int roomNumber, Double area, Integer floor, String status);
    abstract List<Room> searchRoom(Double area, Integer floor, String roomType, String status);
    abstract List<Room> searchRoom(double maxPrice);
    abstract double currentRevenue();
}
