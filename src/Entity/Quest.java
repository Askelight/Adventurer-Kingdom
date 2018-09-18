/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Manager.Mouse;
import Map.TileMap;
import java.awt.image.BufferedImage;

/**
 *
 * @author hour
 */
public class Quest extends Entity {

    private Mouse m;
    BufferedImage[] sprites;
    
    public Quest(TileMap tm) {
        super(tm);
        
        team = 1;
        cost = 300;
        name = "Town Hall";

        this.width = this.height = 34;
        this.sprites = Manager.Content.QUEST[0];
        this.animation.setFrames(this.sprites);

    }
    
    public int getCol() {
        return xcol = (m.mouseX() / 32) + tileMap.getColX();
    }
    
    public int getRow() {
        return yrow = (m.mouseY() / 32) + tileMap.getRowY();
    }
    
    public void addQuest() {
        setTilePosition(getCol(), getRow());
    }
    
    public void update() {
        this.rowTile = (this.current_y / this.tileSize);
        this.colTile = (this.current_x / this.tileSize);
    }
}
