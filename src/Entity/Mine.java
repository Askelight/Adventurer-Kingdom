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
public class Mine extends Building {

    private int spownX;
    private int spownY;
    
    public Mine(TileMap tm) {
        super(tm);
        
        team = 1;
        hp = 2000;
        maxhp = 2000;
        level = 1;
        slot = 1;
        cost = 1000;
        name = "Dungeon";
    
        this.width = this.height = 96;
        this.sprites = Manager.Content.MINE[1];
        this.sprites2 = Manager.Content.MINE[0];
        this.animation.setFrames(this.sprites);
    }

    public int getSpownX() {
        return spownX;
    }

    public void setSpownX(int spownX) {
        this.spownX = spownX;
    }

    public int getSpownY() {
        return spownY;
    }

    public void setSpownY(int spownY) {
        this.spownY = spownY;
    }
    
    
}
