package org.example;

import org.example.UI.HotelUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        HotelUI a=new HotelUI();

        JFrame frame = new JFrame("Ui");
        frame.setContentPane(new HotelUI().JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
