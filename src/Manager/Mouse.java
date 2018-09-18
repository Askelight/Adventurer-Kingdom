/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author hour
 */
public class Mouse implements MouseListener, MouseMotionListener{
    
    public static boolean mousePressed = false;
    public static boolean mouseReleased = false;
    public static boolean mouseEntered = false;
    public static boolean mouseExited = false;
    public static int mouseX;
    public static int mouseY;
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
        mouseX = e.getX();
        mouseY = e.getY();
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseReleased = true;
        mousePressed = false;
        mouseX = e.getX();
        mouseY = e.getY();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    public static boolean isPressed() {
        return mousePressed;
    }
    
    public static boolean isReleased() {
        return mouseReleased;
    }
    
    public static boolean isEntered() {
        return mouseEntered;
    }
    
    public static boolean isExited() {
        return mouseExited;
    }
    
    public static int mouseX() {
        return mouseX;
    }
    
    public static int mouseY() {
        return mouseY;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}