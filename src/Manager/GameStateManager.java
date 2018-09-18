/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import GameState.CreditState;
import GameState.GameOverState;
import GameState.GameState;
import GameState.IntroState;
import GameState.MenuState;
import GameState.PauseState;
import GameState.PlayState;
import java.awt.Graphics2D;

/**
 *
 * @author hour
 */
public class GameStateManager {
    
    private boolean paused;
    private PauseState pauseState;
    private GameState[] gameStates;
    private int currentState;
    private int previousState;
    public static final int NUM_STATES = 5;
    public static final int INTRO = 0;
    public static final int MENU = 1;
    public static final int PLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int CREDIT = 4;

    public GameStateManager() {
        Sound.init();
        
        this.paused = false;
        this.pauseState = new PauseState(this);

        this.gameStates = new GameState[5];
        setState(0);
    }

    public void setState(int i) {
        this.previousState = this.currentState;
        unloadState(this.previousState);
        this.currentState = i;
        if (i == 0) {
            this.gameStates[i] = new IntroState(this);
            this.gameStates[i].init();
        } else if (i == 1) {
            this.gameStates[i] = new MenuState(this);
            this.gameStates[i].init();
        } else if (i == 2) {
            this.gameStates[i] = new PlayState(this);
            this.gameStates[i].init();
        } else if (i == 3) {
            this.gameStates[i] = new GameOverState(this);
            this.gameStates[i].init();
        } else if (i == 4) {
            this.gameStates[i] = new CreditState(this);
            this.gameStates[i].init();
        }
    }

    public void unloadState(int i) {
        this.gameStates[i] = null;
    }

    public void setPaused(boolean b) {
        this.paused = b;
    }

    public void update() {
        if (this.paused) {
            this.pauseState.update();
        } else if (this.gameStates[this.currentState] != null) {
            this.gameStates[this.currentState].update();
        }
    }

    public void draw(Graphics2D g) {
        if (this.paused) {
            this.pauseState.draw(g);
        } else if (this.gameStates[this.currentState] != null) {
            this.gameStates[this.currentState].draw(g);
        }
    }
}
