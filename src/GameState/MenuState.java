package GameState;

/**
 *
 * @author hour
 */

import Manager.Content;
import Manager.GameStateManager;
import Manager.Mouse;
import Manager.Sound;
import java.awt.Color;
import javagame.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MenuState extends GameState{
    
    private Mouse m;
    
    private int btnStart = 0, btnCredit = 0, btnExit = 0;
    private BufferedImage logo, pointer , background;
    private String[] options = {"START", "CREDIT", "EXIT" };
    
    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        this.logo = Content.LOGO[0][0];
        this.pointer = Content.MENUPOINT[0][0];
        this.background = Content.BG[0][0];
        
        Sound.load("/Sound/click.wav", "click");
        Sound.load("/Sound/Fantasy Choir 2.wav", "bgMenu");
        Sound.setVolume("bgMenu", -10.0F);
        Sound.loop("bgMenu", 1000, 1000, Sound.getFrames("bgMenu") - 1000);
    }

    @Override
    public void update() {
        handleInput();
    }

    @Override
    public void draw(Graphics2D g) {
        
        g.drawImage(background, 0, 0, null);
        
        g.drawImage(this.logo, (GamePanel.WIDTH/2)-(850/2), 120, null);
        
        if (btnStart == 0) {
            Content.drawString(g, this.options[0], (GamePanel.WIDTH/2)-(this.options[0].length()*16/2), 400);
        } else if (btnStart == 1) {
            Content.drawString2(g, this.options[0], (GamePanel.WIDTH/2)-(this.options[0].length()*16/2), 400);
            g.drawImage(this.pointer, (GamePanel.WIDTH/2)-(this.options[0].length()*16/2)-34, 400-7, null);
        } else if (btnStart == 2) {
            Content.drawString(g, this.options[0], (GamePanel.WIDTH/2)-(this.options[0].length()*16/2), 400);
            g.drawImage(this.pointer, (GamePanel.WIDTH/2)-(this.options[0].length()*16/2)-40, 400-7, null);
        }
        if (btnCredit == 0) {
            Content.drawString(g, this.options[1], (GamePanel.WIDTH/2)-(this.options[1].length()*16/2), 500);
        } else if (btnCredit == 1) {
            Content.drawString2(g, this.options[1], (GamePanel.WIDTH/2)-(this.options[1].length()*16/2), 500);
            g.drawImage(this.pointer, (GamePanel.WIDTH/2)-(this.options[1].length()*16/2)-34, 500-7, null);
        } else if (btnCredit == 2) {
            Content.drawString(g, this.options[1], (GamePanel.WIDTH/2)-(this.options[1].length()*16/2), 500);
            g.drawImage(this.pointer, (GamePanel.WIDTH/2)-(this.options[1].length()*16/2)-40, 500-7, null);
        }
        if (btnExit == 0) {
            Content.drawString(g, this.options[2], (GamePanel.WIDTH/2)-(this.options[2].length()*16/2), 600);
        } else if (btnExit == 1) {
            Content.drawString2(g, this.options[2], (GamePanel.WIDTH/2)-(this.options[2].length()*16/2), 600);
            g.drawImage(this.pointer, (GamePanel.WIDTH/2)-(this.options[2].length()*16/2)-34, 600-7, null);
        } else if (btnExit == 2) {
            Content.drawString(g, this.options[2], (GamePanel.WIDTH/2)-(this.options[2].length()*16/2), 600);
            g.drawImage(this.pointer, (GamePanel.WIDTH/2)-(this.options[2].length()*16/2)-40, 600-7, null);
        }
    }

    @Override
    public void handleInput() {
        // Mouse
        if (m.mouseX() > (GamePanel.WIDTH/2)-(120/2) && (m.mouseX() < (GamePanel.WIDTH/2)-(120/2)+120)) {
            if ((m.mouseY() > 400) && (m.mouseY() < 400+50)) {
                btnStart = 2;
                if (m.isPressed()) {
                    btnStart = 1;
                }
                if (m.isReleased()) {
                    btnStart = 0;
                    Sound.stop("bgMenu");
                    Sound.play("click");
                    this.gsm.setState(2);
                    System.out.println("Start Game");
                    m.mouseReleased = false;
                }
            } else {
                btnStart = 0;
            }
        }
        if (m.mouseX() > (GamePanel.WIDTH/2)-(120/2) && (m.mouseX() < (GamePanel.WIDTH/2)-(120/2)+120)) {
            if ((m.mouseY() > 500) && (m.mouseY() < 500+50)) {
                btnCredit = 2;
                if (m.isPressed()) {
                    btnCredit = 1;
                }
                if (m.isReleased()) {
                    btnCredit = 0;
                    Sound.stop("bgMenu");
                    Sound.play("click");
                    this.gsm.setState(4);
                    System.out.println("Credit Play");
                    m.mouseReleased = false;
                }
            } else {
                btnCredit = 0;
            }
        }
        if (m.mouseX() > (GamePanel.WIDTH/2)-(120/2) && (m.mouseX() < (GamePanel.WIDTH/2)-(120/2)+120)) {
            if ((m.mouseY() > 600) && (m.mouseY() < 600+50)) {
                btnExit = 2;
                if (m.isPressed()) {
                    btnExit = 1;
                }
                if (m.isReleased()) {
                    btnExit = 0;
                    Sound.stop("bgMenu");
                    Sound.play("click");
                    System.out.println("Exit Game");
                    System.exit(0);
                    m.mouseReleased = false;
                }
            } else {
                btnExit = 0;
            }
        }
    }
}
