/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import javagame.GamePanel;
import Manager.GameStateManager;
import Manager.Keys;
import Manager.Mouse;
import static Manager.Mouse.mouseReleased;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author hour
 */
public class IntroState extends GameState{
    
    private BufferedImage logo;
    
    private final int logoX = (GamePanel.WIDTH/2)-(850/2);
    private int logoY = (GamePanel.HEIGHT/2)-(112/2);
    private int alpha;
    private int ticks;
    
    public IntroState(GameStateManager gsm) {
        super(gsm);
    }
    
    @Override
    public void init() {
        this.ticks = 0;
        try {
            this.logo = ImageIO.read(getClass().getResourceAsStream("/Logo/adventurer kingdom.png"));
        } catch (IOException ex) {
            Logger.getLogger(IntroState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update() {
       handleInput();
        this.ticks += 1;
        if (this.ticks < 60)
        {
          this.alpha = ((int)(255.0D - 255.0D * (1.0D * this.ticks / 60.0D)));
          if (this.alpha < 0) {
            this.alpha = 0;
          }
        }
        if (this.ticks > 100)
        {
          this.logoY = this.logoY - 5;
          if (this.logoY < 120) {
            this.logoY = 120;
          }
        }
        if (this.ticks > 180) {
          this.gsm.setState(1);
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.drawImage(this.logo, this.logoX, this.logoY, null);
        g.setColor(new Color(0, 0, 0, this.alpha));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }
    
    @Override
    public void handleInput() {
        if(Keys.isPressed(Keys.ENTER)) {
            this.gsm.setState(1);
        }
        if (Mouse.isReleased()) {
            this.gsm.setState(1);
            mouseReleased = false;
        }
    }
}
