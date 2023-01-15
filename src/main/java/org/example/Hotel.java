package org.example;


import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room>  rooms;
    public Hotel(){
        this.rooms= new ArrayList<Room>();
    }


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

    public List<Room> showAllRooms(){
        List<Room> result=new ArrayList<Room>();
        this.rooms.forEach(e-> result.add(e));
        return result;
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
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Room room1 = new Room(1, 20, 2, "Single", "available");
        Room room2 = new Room(2, 25, 3, "Double", "rented");
        Room room3 = new Room(3, 30, 4, "Suite", "available");

        // Adding rooms
        System.out.println(hotel.addRoom(room1)); // prints "true"
        System.out.println(hotel.addRoom(room2)); // prints "true"
        System.out.println(hotel.addRoom(room3)); // prints "true"

        // Attempting to add a duplicate room
        Room duplicateRoom = new Room(1, 20, 2, "Single", "available");
        System.out.println(hotel.addRoom(duplicateRoom)); // prints "false"

        // Removing a room
        System.out.println(hotel.removeRoom(2)); // prints "Room 2 removed"
        // Attempting to remove a non-existent room
        System.out.println(hotel.removeRoom(4)); // prints "Room 4 not found"

        // Updating a room
        System.out.println(hotel.updateRoom(1, 22.0, 2, "Double", "rented")); // prints "Room 1 updated"
        // Attempting to update a non-existent room
        System.out.println(hotel.updateRoom(4, 22.0, 2, "Double", "rented")); // prints "Room 4 not found"

        // Showing all rooms
        List<Room> allRooms = hotel.showAllRooms();
        for (Room room : allRooms) {
            System.out.println(room);
        }

        // Searching for rooms by area, floor, room type, and status
        List<Room> searchResults = hotel.searchRoom(22.0, 2, "Double", "rented");
        for (Room room : searchResults) {
            System.out.println(room);
        }

        //Searching for rooms by rental price
        List<Room> searchResultsByPrice = hotel.searchRoomByPrice(30);
        for (Room room : searchResultsByPrice) {
            System.out.println(room);
        }
        //Calculating current revenue
        System.out.println("Current Revenue: " + hotel.currentRevenue());
    }

        }