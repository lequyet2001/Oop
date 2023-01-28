package org.example;



public class StandardRoom extends Room {

    public StandardRoom(int number, double area, int floor, String status) {
        super(number, area, floor, status);
    }

    @Override
    public String toString() {
        String a="[Room number]: " +this.getNumber()+"\n"+ "[Area]: "+ this.getArea()+"\n"+"[Floor]: "+this.getFloor()+"\n"+"[Room type]: standard"+"\n"+"[Status]: "+this.getStatus();
        return a;
    }

    @Override
    public double rentalPrice() {
        return this.getArea() * 100000;
    }
}