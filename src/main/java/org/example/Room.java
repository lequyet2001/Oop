package org.example;

public class Room {
    private int number;
    private double area;
    private int floor;
    private String status;
    private String roomType;
    public Room(int number, double area, int floor, String status,String roomType) {
        this.number = number;
        this.area = area;
        this.floor = floor;
        this.status = status;
        this.roomType=roomType;
    }

    @Override
    public String toString() {

        return "[Room number]: " + this.number+"\n"+ "[Area]: "+ this.area+"\n"+"[Floor]: "+ this.floor+"\n"+"[Status]: "+ this.status;
    }

    public String getRoomType() {
        return roomType;
    }



    public int rentalPrice() {
        return 0;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public int getNumber() {
        return number;
    }

    public double getArea() {
        return  area;
    }

    public int getFloor() {
        return floor;
    }

    public String getStatus() {
        return status;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}