package main;

import javax.swing.*;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;
//I AM A COMMUNIST
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("TECH & US IN RUINS");

        GamePanel Basement = new GamePanel();
        window.add(Basement);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Basement.setGame();
        Basement.startGameThread();

        // alters state of static variable in anther class
        Requirements.staticMethod();
        Requirements.returnExecutionCount();
        Requirements requirements = new Requirements();
        // print object
        System.out.println(requirements);
        System.out.print(Requirements.isOdd());
        Requirements2 a = new Requirements2();
        System.out.println(Requirements.returnOther(a));
    }
}
