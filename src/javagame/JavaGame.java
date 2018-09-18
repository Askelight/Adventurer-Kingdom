/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

import Manager.Mouse;

import javax.swing.JFrame;

/**
 *
 * @author hour
 */
public class JavaGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Adventurer Kingdom");
        
        window.add(new GamePanel());
        window.setResizable(false);
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(3);
    }  
}
