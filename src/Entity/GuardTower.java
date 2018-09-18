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
public class GuardTower extends Building {

    public GuardTower(TileMap tm) {
        super(tm);
        
        team = 1;
        hp = 300;
        attack = 3;
        maxhp = 300;
        level = 1;
        slot = 1;
        cost = 1000;
        name = "Guard Tower";
    
        this.width = this.height = 64;
        this.sprites = Manager.Content.GUARD_TOWER[0];
        this.animation.setFrames(this.sprites);
    }
    
    
}
