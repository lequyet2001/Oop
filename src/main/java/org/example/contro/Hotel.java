package org.example.contro;

import org.example.entity.Room;
import org.example.entity.StandardRoom;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends abstractHotel {
    private  List<Room>  rooms;
    public Hotel(){
        rooms= new ArrayList<Room>();

    }

    // Thêm sửa xóa
    public boolean addRoom(Room room) {
        //Check if room already exists
        for (Room r : rooms) {
            if (r.getNumber() == room.getNumber()) {
                return false;
            }
        }
        rooms.add(room);
        return true;
    }
    public String removeRoom(int roomNumber){
        for(int i=0;i<rooms.size();i++){
            if(rooms.get(i).getNumber()==roomNumber){
                rooms.remove(i);
                return "Room " + roomNumber + " removed";
            }
        }
        return "Room " + roomNumber + " not found";
    }

    public String updateRoom(int roomNumber, Double area, Integer floor, String status ) {
        for (Room room : rooms) {
            if (room.getNumber() == roomNumber) {
                if (area != null) {
                    room.setArea(area);
                }
                if (floor != null) {
                    room.setFloor(floor);
                }
                if (status != null) {
                    room.setStatus(status);
                }

                return "Room " + roomNumber + " updated";
            }
        }
        return "Room " + roomNumber + " not found";
    }

    public List<Room> showAllRooms(){
        return rooms;
    }
    //Tìm phòng
    public List<Room> searchRoom(Double area, Integer floor, String roomType, String status) {
        List<Room> result = new ArrayList<Room>();
        for (Room room : rooms) {

            //check roomtype
            String currRoomType;
            if (room instanceof StandardRoom){
                currRoomType = "standard";
            } else {
                currRoomType = "premium";
            }

            if (area != null && room.getArea() != area) {
                continue;
            }
            if (floor != null && room.getFloor() != floor) {
                continue;
            }
            if (roomType != null && !currRoomType.equals(roomType)) {
                continue;
            }

            if (status != null && !room.getStatus().equals(status)) {
                continue;
            }
            result.add(room);
        }
        return result;
    }

    public List<Room> searchRoom(double maxPrice) {
        List<Room> result = new ArrayList<Room>();
        for (Room room : rooms) {
            if (room.rentalPrice() <= maxPrice) {
                result.add(room);
            }
        }
        return result;
    }

    //Doanh thu hotel
    public double currentRevenue() {
        double revenue = 0;
        for (Room room : rooms) {
            if (room.getStatus().equals("rented")) {
                revenue += room.rentalPrice();
            }
        }
        return revenue;
    }
}