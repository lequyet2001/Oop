package org.example.UI;

import org.example.contro.Hotel;
import org.example.entity.PremiumRoom;
import org.example.entity.Room;
import org.example.entity.StandardRoom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class HotelUI {
    private JTextField roomArea;
    private JTextField roomNumber;
    private JTextField roomFloor;
    private JComboBox roomType;
    private JComboBox roomStatus;
    private JButton addRoomButton;
    private JButton searchRoomButton;
    private JButton searchRoomByMaxPriceButton;
    private JTable listRoom;
    private JButton currentRevenueButton;
    private JLabel currentRevenue;
    public JPanel JPanel1;
    private JButton updateRoom;
    private JButton removeRoomButton;
    private JTextField search;
    Hotel hotel= new Hotel();

    private void addRoom(){
        int number = Integer.parseInt(roomNumber.getText());
        String type = (String) roomType.getSelectedItem();
        double area = Double.parseDouble(roomArea.getText());
        int floor = Integer.parseInt(roomFloor.getText());
        String status = (String) roomStatus.getSelectedItem();
        System.out.println(type);
        if(Objects.equals(type, "Standard")){
            Room  room = new StandardRoom(number,area,floor,status,type);
            System.out.println(room.getRoomType());
            hotel.addRoom(room);
        }
        else if (Objects.equals(type, "Premium")) {
            Room  room=new PremiumRoom(number,area,floor,status,type);
            System.out.println(room.getRoomType());
            hotel.addRoom(room);
        }
        showRooms();
    }
    private void updateRoom(){
        int number = Integer.parseInt(roomNumber.getText());
        double area = Double.parseDouble(roomArea.getText());
        int floor = Integer.parseInt(roomFloor.getText());
        String status = (String) roomStatus.getSelectedItem();
        hotel.updateRoom(number,area,floor,status);
        showRooms();

    }
    private void removeRoom(){
        int number = Integer.parseInt(roomNumber.getText());
        hotel.removeRoom(number);
        showRooms();
    }
    private void searchByArea() {
        double area = Double.parseDouble(roomArea.getText());
        String type = (String) roomType.getSelectedItem();
        int floor = Integer.parseInt(roomFloor.getText());
        String status = (String) roomStatus.getSelectedItem();
        DefaultTableModel model = new DefaultTableModel(new String[]{"STT","Room Number","Room Area", "Room Floor", "Room Status", "Room Type","Room Price"}, 0);
        List<Room> data = hotel.searchRoom(area, floor, type, status);
        int a=1;
        for (Room aData : data) {
            model.addRow(new Object[]{a,aData.getNumber(), aData.getArea(), aData.getFloor(), aData.getStatus(), aData.getRoomType()," "+String.valueOf(aData.rentalPrice())+" VND"});
            a++;
        }
        listRoom.setModel(model);
        TableColumnModel col=listRoom.getColumnModel();
        col.getColumn(0).setMaxWidth(200);
    }
    private void searchByPrice() {
        double maxPrice = Double.parseDouble(search.getText());
        DefaultTableModel model = new DefaultTableModel(new String[]{"STT","Room Number","Room Area", "Room Floor", "Room Status", "Room Type","Room Price"}, 0);
        List<Room> data = hotel.searchRoom(maxPrice);
        int a=1;
        for (Room aData : data) {
            model.addRow(new Object[]{a,aData.getNumber(), aData.getArea(), aData.getFloor(), aData.getStatus(), aData.getRoomType()," "+String.valueOf(aData.rentalPrice())+" VND"});
            a++;
        }
        listRoom.setModel(model);
        TableColumnModel col=listRoom.getColumnModel();
        col.getColumn(0).setMaxWidth(200);
    }
    private void showRooms() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"STT","Room Number","Room Area", "Room Floor", "Room Status", "Room Type","Room Price"}, 0);
        List<Room> data = hotel.showAllRooms();
        int a=1;
        for (Room aData : data) {
            model.addRow(new Object[]{a,aData.getNumber(), aData.getArea(), aData.getFloor(), aData.getStatus(),aData.getRoomType()," "+String.valueOf(aData.rentalPrice())+" VND"});
            a++;
        }
        listRoom.setModel(model);
        TableColumnModel col=listRoom.getColumnModel();
        col.getColumn(0).setMaxWidth(200);
    }
    private void reSetText(){
        roomNumber.setText("");
        roomArea.setText("");
        roomFloor.setText("");
        roomType.setSelectedIndex(0);
        roomStatus.setSelectedIndex(0);
        currentRevenue.setText("Current Revenue");
    }

    public HotelUI() {
        showRooms();
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addRoom();
                reSetText();
            }

        });
        updateRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateRoom();
                reSetText();
            }
        });
        removeRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeRoom();
                reSetText();
            }
        });

        searchRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchByArea();
            }

        });
        searchRoomByMaxPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchByPrice();
            }
        });
        currentRevenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double a= hotel.currentRevenue();
                currentRevenue.setText("Current revenue: "+String.valueOf(a)+" VND");
            }
        });
    }

}
