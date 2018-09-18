/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Manager.Mouse;
import Map.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 *
 * @author hour
 */
public class Building extends Entity {

    private int count;

    private Mouse m;
    BufferedImage[] sprites, sprites2;
    
    public Building(TileMap tm) {
        super(tm);
        
    }
    
    public int getBuildCol() {
        return xcol = (m.mouseX() / 32) + tileMap.getColX();
    }
    
    public int getBuildRow() {
        return yrow = (m.mouseY() / 32) + tileMap.getRowY();
    }
    
    public void build() {
        for (int i = 0; i < this.width/32; i++) {
            for (int j = 0; j < this.height/32; j++) {
                setTilePosition(getBuildCol(), getBuildRow());
                addChange(new int[] {(getBuildRow()-1) + j, (getBuildCol()-1) + i, this.team});
            }
        }
    }
    
    public void buildMine(Mine m) {
        for (int i = 0; i < this.width/32; i++) {
            for (int j = 0; j < this.height/32; j++) {
                setTilePosition(m.getSpownX(), m.getSpownY());
                addChange(new int[] {(m.getSpownY()-1) + j, 
                    (m.getSpownX()-1) + i, this.team});
            }
        }
    }
    
    public boolean scanSite() {
        for (int i = 0; i < this.width/32; i++) {
            for (int j = 0; j < this.height/32; j++) {
                if (tileMap.tileType[getBuildRow()-1 + j][getBuildCol()-1 + i] != 0) {
                    System.out.println("You Can't Build Here");
                    return this.empty = false;
                } else {
                    this.empty = true;
                }
            }
        }
        return this.empty;
    }
    
    public void battleGuard(Entity en) {
        if (count > 20) {
            en.hp -= attack;
            
            count = 0;
        }
        if (en.hp <= 0) {
            for (int y = en.rowTile-1; y <= en.rowTile+1; y++) {
                for (int x = en.colTile-1; x <= en.colTile+1; x++) {
                    en.tileMap.setTile(y, x, 0);
                }
            }
        }
        count++;
    }
    
    public void draw(Graphics2D g) {
        super.draw(g);
        g.drawString(name, 
        this.current_x + this.xmap - this.width / 2, 
        this.current_y + this.ymap - this.height / 2);
        g.drawString(this.hp + "/" + this.maxhp, 
        this.current_x + this.xmap - this.width / 2, 
        this.current_y + this.ymap + this.height / 2 + 32);
    }
}
