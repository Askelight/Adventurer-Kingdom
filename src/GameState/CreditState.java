/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;
import Manager.Sound;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author hour
 */
public class CreditState extends GameState {
    
    private ArrayList<String> s;
    private long lastTurn = System.currentTimeMillis();
    private BufferedImage background;
    private int ticks;
    
    public CreditState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        
        Sound.load("/Sound/click.wav", "click");
        Sound.load("/Sound/Fantasy Choir 3.wav", "bgCredit");
        Sound.setVolume("bgCredit", -10.0F);
        Sound.loop("bgCredit", 1000, 1000, Sound.getFrames("bgCredit") - 1000);
        
        this.s = new ArrayList();
        s.add("Sea Kimhour");
        s.add("Azizah Zakiah, S.Kom., M.T.");
        s.add("Yan Puspitarani, S.T., M.T.");
        s.add("Endang Amalia, S.T., M.M.");
        s.add("ForeignGuyMike");
        s.add("ZiNGOT");
        s.add("Henrique Lazarini");
        s.add("Jinn");
        s.add("wulax");
        s.add("Casper Nilsson");
        s.add("Daniel Eddeland");
        s.add("Johann CHARLOT");
        s.add("Skyler Robert Colladay");
        s.add("Lanea Zimmerman");
        s.add("Stephen Challener");
        s.add("Charles Sanchez");
        s.add("Manuel Riecke");
        s.add("Daniel Armstrong");
        s.add("Game Design Novice, gamedesign.wikidot.com");
        s.add("Giant Bomb, giantbomb.com");
        s.add("M. Richard");
        s.add("S. Harshit, P. Amit, P. Vineet");
        s.add("W. Alex");
        s.add("TIOBE, tiobe.com");
        s.add("W. Mick");
        s.add("Z. Yancan");
        s.add("R. Hiscott");
        s.add("F. Fischer");
        s.add("R. Wenderlich");
        s.add("textfx.co");
        s.add("opengameart.org");
        s.add("Wikipedia: The Free Encyclopedia, wikipedia.org");
        
        this.background = Content.BG[0][0];
    }

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        
        g.drawImage(this.background, 0, 0, null);
        
        this.ticks++;
        int i = 0;
        while (i < s.size()) {
            Content.drawString(g, s.get(i), 800-s.get(i).length()*16/2, 900-this.ticks+i*100);
            i++;
        }
        if (this.ticks > 900+i*100) {
            Sound.stop("bgCredit");
            this.gsm.setState(1);
        }
    }

    public void handleInput()
    {
        if (Keys.isPressed(Keys.ESCAPE)) {
            Sound.stop("bgCredit");
            Sound.play("click");
            this.gsm.setState(1);
        }
    }
    
}
