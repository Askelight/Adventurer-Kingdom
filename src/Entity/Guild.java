/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Map.TileMap;
import java.awt.Graphics2D;

/**
 *
 * @author hour
 */
public class Guild extends Building {

    public Guild(TileMap tm) {
        super(tm);
        
        team = 1;
        hp = 1000;
        maxhp = 1000;
        level = 1;
        slot = 1;
        cost = 1500;
        name = "Guild";
    
        this.width = this.height = 96;
        this.sprites = Manager.Content.GUILD[0];
        this.animation.setFrames(this.sprites);
    }
}
