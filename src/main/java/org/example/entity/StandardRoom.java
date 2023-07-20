package org.example.entity;



public class StandardRoom extends Room {

        public StandardRoom(int number, double area, int floor, String status,String type) {
        super(number, area, floor, status,type);
    }
    @Override
    public String toString() {
        return "[Room number]: " + this.getNumber()+"\n"+ "[Area]: "+ this.getArea()+"\n"+"[Floor]: "+ this.getFloor()+"\n"+"[Room type]: standard"+"\n"+"[Status]: "+ this.getStatus();
    }

    @Override
    public int rentalPrice() {
        return (int) (this.getArea() * 100000);
    }
}