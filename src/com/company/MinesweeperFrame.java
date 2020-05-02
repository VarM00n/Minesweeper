package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MinesweeperFrame extends JFrame  implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
      }

    public MinesweeperFrame() {
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(340, 435);
        this.setResizable(false);

    }
}