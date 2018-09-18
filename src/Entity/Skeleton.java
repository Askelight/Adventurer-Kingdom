/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Map.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author hour
 */
public class Skeleton extends Unit {

    public Skeleton(TileMap tm) {
        super(tm);
        
        team = 3;
        name = "Skeleton";
        level = 1;
        hp = 50;
        maxhp = 50;
        attack = 4;
        cost = 30;
        modifier = 1.03;
        this.width = this.height = 32;
        
        this.upSprites = Manager.Content.Skeleton1_Walk[0];
        this.leftSprites = Manager.Content.Skeleton1_Walk[1];
        this.downSprites = Manager.Content.Skeleton1_Walk[2];
        this.rightSprites = Manager.Content.Skeleton1_Walk[3];

        this.upSprites2 = Manager.Content.Skeleton2_Walk[0];
        this.leftSprites2 = Manager.Content.Skeleton2_Walk[1];
        this.downSprites2 = Manager.Content.Skeleton2_Walk[2];
        this.rightSprites2 = Manager.Content.Skeleton2_Walk[3];

        this.upSprites3 = Manager.Content.Skeleton3_Walk[0];
        this.leftSprites3 = Manager.Content.Skeleton3_Walk[1];
        this.downSprites3 = Manager.Content.Skeleton3_Walk[2];
        this.rightSprites3 = Manager.Content.Skeleton3_Walk[3];
        
        this.upSlash = Manager.Content.Skeleton1_Slash[0];
        this.leftSlash = Manager.Content.Skeleton1_Slash[1];
        this.downSlash = Manager.Content.Skeleton1_Slash[2];
        this.rightSlash = Manager.Content.Skeleton1_Slash[3];
        
        this.upSlash2 = Manager.Content.Skeleton2_Slash[0];
        this.leftSlash2 = Manager.Content.Skeleton2_Slash[1];
        this.downSlash2 = Manager.Content.Skeleton2_Slash[2];
        this.rightSlash2 = Manager.Content.Skeleton2_Slash[3];
        
        this.upSlash3 = Manager.Content.Skeleton3_Slash[0];
        this.leftSlash3 = Manager.Content.Skeleton3_Slash[1];
        this.downSlash3 = Manager.Content.Skeleton3_Slash[2];
        this.rightSlash3 = Manager.Content.Skeleton3_Slash[3];
        
        this.animation.setFrames(this.downSprites);
        this.animation.setDelay(3);
    }
}
