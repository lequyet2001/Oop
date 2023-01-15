package org.example;


import java.util.ArrayList;
import java.util.List;

class Hotel {
    private List<Room>  rooms;
    public Hotel(){
        this.rooms= new ArrayList<Room>();
    }



//    public void addRoom(Room room   ){
//
//        this.rooms.add(room);
//    }
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
        for(int i=0;i<this.rooms.size();i++){
            if(this.rooms.get(i).getNumber()==roomNumber){
                this.rooms.remove(i);
                return "Room " + roomNumber + " removed";
            }
        }
    return "Room " + roomNumber + " not found";
    }

    public String updateRoom(int roomNumber, Double area, Integer floor, String roomType, String status) {
        for (Room room : this.rooms) {
            if (room.getNumber() == roomNumber) {
                if (area != null) {
                    room.setArea(area);
                }
                if (floor != null) {
                    room.setFloor(floor);
                }
                if (roomType != null) {
                    room.setRoomType(roomType);
                }
                if (status != null) {
                    room.setStatus(status);
                }
                return "Room " + roomNumber + " updated";
            }
        }
        return "Room " + roomNumber + " not found";
    }

    public List<Room> searchRoom(Double area, Integer floor, String roomType, String status) {
        List<Room> result = new ArrayList<Room>();
        for (Room room : this.rooms) {
            if (area != null && room.getArea() != area) {
                continue;
            }
            if (floor != null && room.getFloor() != floor) {
                continue;
            }
            if (roomType != null && !room.getRoomType().equals(roomType)) {
                continue;
            }

            if (status != null && !room.getStatus().equals(status)) {
                continue;
                }
                result.add(room);
            }
            return result;
        }
    public List<Room> searchRoomByPrice(double maxPrice) {
        List<Room> result = new ArrayList<Room>();
        for (Room room : this.rooms) {
            if (room.rentalPrice() <= maxPrice) {
                result.add(room);
            }
        }
        return result;
    }
    public double currentRevenue() {
        double revenue = 0;
        for (Room room : this.rooms) {
            if (room.getStatus().equals("rented")) {
                revenue += room.rentalPrice();
            }
        }
        return revenue;
    }
}