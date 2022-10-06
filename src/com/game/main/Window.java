package com.game.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    Window(int WIDTH, int HEIGHT, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Game\\src\\com\\game\\main\\icon.png");
        frame.setIconImage(icon);
        frame.setVisible(true);
        frame.add(game);
        game.start();
    }

}
