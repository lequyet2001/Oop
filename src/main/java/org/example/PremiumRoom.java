package org.example;



public class PremiumRoom extends Room {
    public String roomType;
    public PremiumRoom(int number, double area, int floor, String status,String type) {
        super(number, area, floor, status,type);
    }

    @Override
    public String toString() {
        return "[Room number]: " + this.getNumber()+"\n"+ "[Area]: "+ this.getArea()+"\n"+"[Floor]: "+ this.getFloor()+"\n"+"[Room type]: premium"+"\n"+"[Status]: "+ this.getStatus();
    }

    @Override
    public int rentalPrice() {
        return (int) (this.getArea() * 150000 + this.getFloor() * 500000);
    }
}