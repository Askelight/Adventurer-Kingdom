/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

import Manager.GameStateManager;
import Manager.Keys;
import Manager.Mouse;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author hour
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {
    
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    private Thread thread;
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;
    private GameStateManager gsm;
    
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (this.thread == null) {
          addKeyListener(this);
          this.thread = new Thread(this);
          this.thread.start();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60D;
        
        int ticks = 0;
        int frames = 0;
        
        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        
        init();
        while (this.running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldUpdate = true;
            
            while (delta >= 1) {
                delta -= 1;
                ticks++;
                shouldUpdate = true;
            }
            
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (shouldUpdate) {
                update();
                draw();
                drawToScreen();
                
                frames++;
            }
            
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println("Frame: "+frames+", Ticks: "+ticks);
                frames = 0;
                ticks = 0;
            }
        }
    }
    
    private void init() {
        this.running = true;
        this.image = new BufferedImage(WIDTH, HEIGHT, 1);
        this.g = ((Graphics2D)this.image.getGraphics());
        this.gsm = new GameStateManager();
        addMouseListener(new Mouse());
        addMouseMotionListener(new Mouse());
    }

    private void update() {
        this.gsm.update();
        Keys.update();
    }

    private void draw() {
        this.gsm.draw(this.g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(this.image, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }

    @Override
    public void keyTyped(KeyEvent key) {}

    @Override
    public void keyPressed(KeyEvent key) {
        Keys.keySet(key.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent key) {
        Keys.keySet(key.getKeyCode(), false);
    }
}
