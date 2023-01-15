package org.example;

class Room {
    private int number;
    private double area;
    private int floor;
    private String roomType;
    private String status;

    public Room(int number, double area, int floor, String roomType, String status) {
        this.number = number;
        this.area = area;
        this.floor = floor;
        this.roomType = roomType;
        this.status = status;
    }
    public String toString() {

        String a="[Room number]: " +this.number+"\n"+ "[Area]: "+ this.area+"\n"+"[Floor]: "+this.floor+"\n"+"[Room type]: "+this.roomType+"\n"+"[Status]: "+this.status;
        return a;
    }
    public double rentalPrice() {
        if (this.roomType.equals("standard")) {
            return this.area * 100000;
        } else if (this.roomType.equals("premium")) {
            return this.area * 150000 + this.floor * 500000;
        }
        return 0;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public int getNumber() {
        return number;
    }

    public double getArea() {
        return area;
    }

    public int getFloor() {
        return floor;
    }

    public String getRoomType() {
        return roomType;
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

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

