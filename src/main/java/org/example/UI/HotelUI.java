package org.example.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelUI {
    private JTextField roomArea;
    private JTextField roomNumber;
    private JTextField roomFloor;
    private JComboBox roomType;
    private JComboBox roomStatus;
    private JButton addRoomButton;
    private JButton searchRoomButton;
    private JButton searchRoomByMaxButton;
    private JTable listRoom;
    private JButton currentRevenueButton;
    private JTextField currentRevenue;
    private JPanel JPanel1;

    public HotelUI() {
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }

        });
        searchRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }

        });
        searchRoomByMaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }

        });
        currentRevenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }

        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ui");
        frame.setContentPane(new HotelUI().JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
