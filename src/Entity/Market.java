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
public class Market extends Building{

    public Market(TileMap tm) {
        super(tm);
        
        team = 1;
        hp = 800;
        maxhp = 800;
        level = 1;
        slot = 1;
        cost = 1500;
        name = "Market";
    
        this.width = this.height = 64;
        this.sprites = Manager.Content.MARKET[0];
        this.animation.setFrames(this.sprites);
    }
    
    
    
}
