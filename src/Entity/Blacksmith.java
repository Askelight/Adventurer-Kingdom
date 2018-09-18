/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Map.TileMap;

/**
 *
 * @author hour
 */
public class Blacksmith extends Building {

    public Blacksmith(TileMap tm) {
        super(tm);
        
        team = 1;
        hp = 900;
        maxhp = 900;
        level = 1;
        slot = 1;
        cost = 1500;
        name = "Blacksmith";
    
        this.width = this.height = 64;
        this.sprites = Manager.Content.BLACKSMITH[0];
        this.sprites2 = Manager.Content.BLACKSMITH[1];
        this.animation.setFrames(this.sprites);
    }
    
    
    
}
