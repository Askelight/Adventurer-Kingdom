/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;


import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author hour
 */
public class GameOverState extends GameState {
    private String gameOver = "GameOver";
    private String cont = "press ESCAPE to return";

    public GameOverState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
    }

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        Content.drawString(g, gameOver, 800-gameOver.length()/2*16, 450);
        Content.drawString(g, cont, 800-cont.length()/2*16, 500);
    }

    public void handleInput()
    {
        if (Keys.isPressed(Keys.ESCAPE)) {
            this.gsm.setState(1);
        }
    }
}
