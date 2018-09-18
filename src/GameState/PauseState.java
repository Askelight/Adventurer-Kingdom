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

/**
 *
 * @author hour
 */
public class PauseState extends GameState {
    
    private String pauseString = "GAME PAUSE";
    private String exit = "return to MANU press F1 and ESCAPE to unpause";
    
    public PauseState(GameStateManager gsm) {
        super(gsm);
    }
    
    public void init() {}
    
    public void update() {
        handleInput();
    }
    
    //Draw something when pause
    public void draw(Graphics2D g) {
        Content.drawString(g, pauseString, 800-pauseString.length()/2*16, 500);
        Content.drawString(g, exit, 800-exit.length()/2*16, 600);
    }
    
    public void handleInput() {
        if (Keys.isPressed(Keys.ESCAPE)) {
            Sound.resumeLoop("bgPlay");
            this.gsm.setPaused(false);
        }
        if (Keys.isPressed(Keys.F1)) {
            this.gsm.setPaused(false);
            this.gsm.setState(1);
        }
    }
}
