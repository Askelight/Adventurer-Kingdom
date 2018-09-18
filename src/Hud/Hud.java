/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hud;

import Entity.Skeleton;
import Manager.Content;
import Manager.Gold;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author hour
 */
public class Hud {
    
    private final int width = 448;
    private final int height = 288;
    private final int mapWidth = 1600;
    private final int mapHeight = 900;
    private final int x = mapWidth - width;
    private final int y = mapHeight - height;
    private BufferedImage bg, townHall, guild, market, blacksmith, guardTower, advanturer_robe, advanturer_leather, advanturer_plate_armor, a1, a2, a3, s1, s2, s3, p1, p2, p3, remove, quest;
    
    private int armor = 0, sword = 0, potion = 0;
    
    private boolean tow;
    private boolean gui;
    private boolean mar;
    private boolean bla;
    private boolean gua;
    
    public Hud() {
        
        this.bg = Content.HUD[0][0];
        this.townHall = Content.TOWN_HALL_ICON[0][0];
        this.guild = Content.GUILD_ICON[0][0];
        this.market = Content.MARKET_ICON[0][0];
        this.blacksmith = Content.BLACKSMITH_ICON[0][0];
        this.guardTower = Content.GUARD_TOWER_ICON[0][0];
        
        this.advanturer_robe = Content.Advanturer_Robe_Walk[2][0];
        this.advanturer_leather = Content.Advanturer_Leather_Walk[2][0];
        this.advanturer_plate_armor = Content.Advanturer_Plate_Walk[2][0];
        
        this.a1 = Content.ARMOR1[0][0];
        this.a2 = Content.ARMOR2[0][0];
        this.a3 = Content.ARMOR3[0][0];
        this.s1 = Content.SWORD1[0][0];
        this.s2 = Content.SWORD2[0][0];
        this.s3 = Content.SWORD3[0][0];
        this.p1 = Content.POTION1[0][0];
        this.p2 = Content.POTION2[0][0];
        this.p3 = Content.POTION3[0][0];
        this.quest = Content.QUEST[0][0];
        this.remove = Content.REMOVE[0][0];
        
        tow = false;
        gui = false;
        mar = false;
        bla = false;
        tow = false;
        this.armor = 0;
        this.sword = 0;
        this.potion = 0;
    }

    public void draw(Graphics2D g) {
        
        g.drawImage(this.bg, x, y, width, height, null);
        
        g.drawImage(this.townHall, mapWidth-46, mapHeight-height, null);
        g.drawString("TOWN HALL", mapWidth-140, mapHeight-272);
        g.drawString("COST: 2000g", mapWidth-140, mapHeight-256);

        g.drawImage(this.quest, mapWidth-width+16, mapHeight-34, null);
        g.drawImage(this.remove, mapWidth-width+16+34, mapHeight-34, null);
        
        if (this.tow) {
            g.drawImage(this.guild, mapWidth-46, mapHeight-height+38, null);
            g.drawString("GUILD", mapWidth-140, mapHeight-235);
            g.drawString("COST: 1500g", mapWidth-140, mapHeight-219);

            g.drawImage(this.market, mapWidth-46, mapHeight-height+38*2, null);
            g.drawString("MARKET", mapWidth-140, mapHeight-197);
            g.drawString("COST: 1500g", mapWidth-140, mapHeight-181);

            g.drawImage(this.blacksmith, mapWidth-46, mapHeight-height+38*3, null);
            g.drawString("BLACKSMITH", mapWidth-140, mapHeight-159);
            g.drawString("COST: 1500g", mapWidth-140, mapHeight-143);

            g.drawImage(this.guardTower, mapWidth-46, mapHeight-height+38*4, null);
            g.drawString("GUARD TOWER", mapWidth-140, mapHeight-121);
            g.drawString("COST: 1000g", mapWidth-140, mapHeight-105);
        }
        
        if (this.gui) {
            g.drawImage(this.advanturer_robe, mapWidth-width+16, mapHeight-height, null);
            g.drawString("SWORDMAN", mapWidth-width+48, mapHeight-height+20);
            g.drawString("100g", mapWidth-width+48, mapHeight-height+32);
            
            g.drawImage(this.advanturer_leather, mapWidth-width+16, mapHeight-height+48, null);
            g.drawString("SWORDMASTER", mapWidth-width+48, mapHeight-height+20+48);
            g.drawString("500g", mapWidth-width+48, mapHeight-height+32+48);
            
            g.drawImage(this.advanturer_plate_armor, mapWidth-width+16, mapHeight-height+96, null);
            g.drawString("GRANDMASTER", mapWidth-width+48, mapHeight-height+20+96);
            g.drawString("1000g", mapWidth-width+48, mapHeight-height+32+96);
        }
        
        if (this.bla) {
            switch (this.armor) {
                case 1:
                    g.drawImage(this.a1, mapWidth-width+195, mapHeight-height+5, null);
                    g.drawString("Tier 1 ARMOR: +10 MAXHP", mapWidth-width+48+110, mapHeight-height+50);
                    g.drawString("NEXT UPGRADE: 500g", mapWidth-width+48+110, mapHeight-height+62);
                    break;
                case 2:
                    g.drawImage(this.a2, mapWidth-width+195, mapHeight-height+5, null);
                    g.drawString("Tier 2 ARMOR: +20 MAXHP", mapWidth-width+48+110, mapHeight-height+50);
                    g.drawString("NEXT UPGRADE: 1000g", mapWidth-width+48+110, mapHeight-height+62);
                    break;
                case 3:
                    g.drawImage(this.a3, mapWidth-width+195, mapHeight-height+5, null);
                    g.drawString("Tier 3 ARMOR: +30 MAXHP", mapWidth-width+48+110, mapHeight-height+50);
                    g.drawString("MAX", mapWidth-width+48+110, mapHeight-height+62);
                    break;
                default:
                    break;
            }
            switch (this.sword) {
                case 1:
                    g.drawImage(this.s1, mapWidth-width+195, mapHeight-height+5+70, null);
                    g.drawString("Tier 1 SWORD: +1 ATK", mapWidth-width+48+110, mapHeight-height+50+70);
                    g.drawString("NEXT UPGRADE: 500g", mapWidth-width+48+110, mapHeight-height+62+70);
                    break;
                case 2:
                    g.drawImage(this.s2, mapWidth-width+195, mapHeight-height+5+70, null);
                    g.drawString("Tier 2 SWORD: +2 ATK", mapWidth-width+48+110, mapHeight-height+50+70);
                    g.drawString("NEXT UPGRADE: 1000g", mapWidth-width+48+110, mapHeight-height+62+70);
                    break;
                case 3:
                    g.drawImage(this.s3, mapWidth-width+195, mapHeight-height+5+70, null);
                    g.drawString("Tier 3 SWORD: +3 ATK", mapWidth-width+48+110, mapHeight-height+50+70);
                    g.drawString("MAX", mapWidth-width+48+110, mapHeight-height+62+70);
                    break;
                default:
                    break;
            }
        }
        if (this.mar) {
            switch (this.potion) {
                case 1:
                    g.drawImage(this.p1, mapWidth-width+195, mapHeight-height+5+140, null);
                    g.drawString("Tier 1 POTION: +1 HEAL", mapWidth-width+48+110, mapHeight-height+50+140);
                    g.drawString("NEXT UPGRADE: 500g", mapWidth-width+48+110, mapHeight-height+62+140);
                    break;
                case 2:
                    g.drawImage(this.p2, mapWidth-width+195, mapHeight-height+5+140, null);
                    g.drawString("Tier 2 POTION: +2 HEAL", mapWidth-width+48+110, mapHeight-height+50+140);
                    g.drawString("NEXT UPGRADE: 1000g", mapWidth-width+48+110, mapHeight-height+62+140);
                    break;
                case 3:
                    g.drawImage(this.p3, mapWidth-width+195, mapHeight-height+5+140, null);
                    g.drawString("Tier 3 POTION: +3 HEAL", mapWidth-width+48+110, mapHeight-height+50+140);
                    g.drawString("MAX", mapWidth-width+48+110, mapHeight-height+62+140);
                    break;
            }
        }
    }
    
    public void update() {
    }

    public boolean isTow() {
        return tow;
    }

    public void setTow(boolean tow) {
        this.tow = tow;
    }

    public boolean isGui() {
        return gui;
    }

    public void setGui(boolean gui) {
        this.gui = gui;
    }

    public boolean isMar() {
        return mar;
    }

    public void setMar(boolean mar) {
        this.mar = mar;
    }

    public boolean isBla() {
        return bla;
    }

    public void setBla(boolean bla) {
        this.bla = bla;
    }

    public boolean isGua() {
        return gua;
    }

    public void setGua(boolean gua) {
        this.gua = gua;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getSword() {
        return sword;
    }

    public void setSword(int sword) {
        this.sword = sword;
    }

    public int getPotion() {
        return potion;
    }

    public void setPotion(int potion) {
        this.potion = potion;
    }
    
    
}
