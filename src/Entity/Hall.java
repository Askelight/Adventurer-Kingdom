/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Map.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author hour
 */
public class Hall extends Building{
    
    public Hall(TileMap tm) {
        super(tm);
        
        team = 1;
        hp = 2000;
        maxhp = 2000;
        level = 1;
        slot = 1;
        cost = 2000;
        name = "Town Hall";
    
        this.width = this.height = 96;
        this.sprites = Manager.Content.TOWN_HALL[0];
        this.sprites2 = Manager.Content.TOWN_HALL[1];
        this.animation.setFrames(this.sprites);
    }
}
