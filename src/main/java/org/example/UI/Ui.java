package org.example.UI;

import org.example.Room;
import org.example.Hotel;
import org.testng.annotations.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;


public class Ui {
    JTextField roomArea;
    JTextField roomNumber;
    JTextField roomFloor;
    JComboBox roomType;
    JComboBox roomStatus;
    private JButton updateRoomButton;
    JButton addRoomButton;
    private JButton removeRoomButton;
    private JTextField search;
    private JButton searchingForRoomsByButton2;
    private JPanel jPanel1;
    private JTable table1;
    private JButton searchingForRoomsByButton;
    private JButton currentRevenue;
    Hotel hotel= new Hotel();

    private void addRoom(){
        int number = Integer.parseInt(roomNumber.getText());
        String type = (String) roomType.getSelectedItem();
        double area = Double.parseDouble(roomArea.getText());
        int floor = Integer.parseInt(roomFloor.getText());
        String status = (String) roomStatus.getSelectedItem();
        Room room = new Room(number,area,floor,type,status);

        hotel.addRoom(room);
        showRooms();
    }
    private void removeRoom(){
        int number = Integer.parseInt(roomNumber.getText());
        hotel.removeRoom(number);
        showRooms();
    }
    private void updateRomo(){
        int number = Integer.parseInt(roomNumber.getText());
        String type = (String) roomType.getSelectedItem();
        double area = Double.parseDouble(roomArea.getText());
        int floor = Integer.parseInt(roomFloor.getText());
        String status = (String) roomStatus.getSelectedItem();
        Room room = new Room(number,area,floor,type,status);
        hotel.updateRoom(number,area,floor,type,status);
        showRooms();

    }
    private void reSetText(){
        roomNumber.setText("");
        roomArea.setText("");
        roomFloor.setText("");
        roomType.setSelectedIndex(0);
        roomStatus.setSelectedIndex(0);
        currentRevenue.setText("Current Revenue");
    }

    private void showRooms() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"STT","Room Number","Room Area", "Room Floor", "Room Status", "Room Type"}, 0);
        List<Room> data = hotel.showAllRooms();
        int a=1;
        for (Room aData : data) {
            model.addRow(new Object[]{a,aData.getNumber(), aData.getArea(), aData.getFloor(), aData.getStatus(), aData.getRoomType()});
            a++;
        }
        table1.setModel(model);
        TableColumnModel col=table1.getColumnModel();
        col.getColumn(0).setMaxWidth(200);
    }

    private void searchByArea() {
        double area = Double.parseDouble(search.getText());
        DefaultTableModel model = new DefaultTableModel(new String[]{"STT","Room Number","Room Area", "Room Floor", "Room Status", "Room Type"}, 0);
        List<Room> data = hotel.searchRoom(area, null, null, null);
        int a=1;
        for (Room aData : data) {
            model.addRow(new Object[]{a,aData.getNumber(), aData.getArea(), aData.getFloor(), aData.getStatus(), aData.getRoomType()});
            a++;
        }
        table1.setModel(model);
        TableColumnModel col=table1.getColumnModel();
        col.getColumn(0).setMaxWidth(200);
    }

    private void searchByPrice() {
        double maxPrice = Double.parseDouble(search.getText());
        DefaultTableModel model = new DefaultTableModel(new String[]{"STT","Room Number","Room Area", "Room Floor", "Room Status", "Room Type"}, 0);
        List<Room> data = hotel.searchRoomByPrice(maxPrice);
        int a=1;
        for (Room aData : data) {
            model.addRow(new Object[]{a,aData.getNumber(), aData.getArea(), aData.getFloor(), aData.getStatus(), aData.getRoomType()});
            a++;
        }
        table1.setModel(model);
        TableColumnModel col=table1.getColumnModel();
        col.getColumn(0).setMaxWidth(200);
    }
    public Ui() {
        showRooms();
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoom();
                reSetText();
            }
        });
        removeRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeRoom();
                reSetText();
            }
        });
        updateRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRomo();
                reSetText();
            }
        });
        searchingForRoomsByButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByPrice();
            }
        });
        searchingForRoomsByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByArea();
            }
        });
        currentRevenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a= String.valueOf(hotel.currentRevenue());
                currentRevenue.setText(a);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ui");
        frame.setContentPane(new Ui().jPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
