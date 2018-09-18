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
public class Adventurer extends Unit {
    
    public Adventurer(TileMap tm) {
        super(tm);
        
        this.team = 2;
        this.name = "Advanture";
        level = 1;
        hp = 55;
        maxhp = 55;
        attack = 6;
        modifier = 1.032;
        this.width = this.height = 32;

        this.upSprites = Manager.Content.Advanturer_Robe_Walk[0];
        this.leftSprites = Manager.Content.Advanturer_Robe_Walk[1];
        this.downSprites = Manager.Content.Advanturer_Robe_Walk[2];
        this.rightSprites = Manager.Content.Advanturer_Robe_Walk[3];

        this.upSprites2 = Manager.Content.Advanturer_Leather_Walk[0];
        this.leftSprites2 = Manager.Content.Advanturer_Leather_Walk[1];
        this.downSprites2 = Manager.Content.Advanturer_Leather_Walk[2];
        this.rightSprites2 = Manager.Content.Advanturer_Leather_Walk[3];

        this.upSprites3 = Manager.Content.Advanturer_Plate_Walk[0];
        this.leftSprites3 = Manager.Content.Advanturer_Plate_Walk[1];
        this.downSprites3 = Manager.Content.Advanturer_Plate_Walk[2];
        this.rightSprites3 = Manager.Content.Advanturer_Plate_Walk[3];

        this.upSlash = Manager.Content.Advanturer_Robe_Slash[0];
        this.leftSlash = Manager.Content.Advanturer_Robe_Slash[1];
        this.downSlash = Manager.Content.Advanturer_Robe_Slash[2];
        this.rightSlash = Manager.Content.Advanturer_Robe_Slash[3];
        
        this.upSlash2 = Manager.Content.Advanturer_Leather_Slash[0];
        this.leftSlash2 = Manager.Content.Advanturer_Leather_Slash[1];
        this.downSlash2 = Manager.Content.Advanturer_Leather_Slash[2];
        this.rightSlash2 = Manager.Content.Advanturer_Leather_Slash[3];

        this.upSlash3 = Manager.Content.Advanturer_Plate_Slash[0];
        this.leftSlash3 = Manager.Content.Advanturer_Plate_Slash[1];
        this.downSlash3 = Manager.Content.Advanturer_Plate_Slash[2];
        this.rightSlash3 = Manager.Content.Advanturer_Plate_Slash[3];
        
        this.animation.setFrames(this.downSprites);
        this.animation.setDelay(3);
    }
}
