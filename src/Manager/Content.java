/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;

/**
 *
 * @author hour
 */

public class Content {
    
    //Hud
    public static BufferedImage[][] HUD = load("/Hud/hud.png", 448, 288);
    public static BufferedImage[][] ARMOR1 = load("/Hud/A1.png", 34, 34);
    public static BufferedImage[][] ARMOR2 = load("/Hud/A2.png", 34, 34);
    public static BufferedImage[][] ARMOR3 = load("/Hud/A3.png", 34, 34);
    public static BufferedImage[][] SWORD1 = load("/Hud/S1.png", 34, 34);
    public static BufferedImage[][] SWORD2 = load("/Hud/S2.png", 34, 34);
    public static BufferedImage[][] SWORD3 = load("/Hud/S3.png", 34, 34);
    public static BufferedImage[][] POTION1 = load("/Hud/P1.png", 34, 34);
    public static BufferedImage[][] POTION2 = load("/Hud/P2.png", 34, 34);
    public static BufferedImage[][] POTION3 = load("/Hud/P3.png", 34, 34);
    public static BufferedImage[][] QUEST = load("/Hud/QUEST.png", 34, 34);
    public static BufferedImage[][] REMOVE = load("/Hud/REMOVE.png", 34, 34);
    //Menu
    public static BufferedImage[][] LOGO = load("/Logo/adventurer kingdom.png", 850, 112);
    public static BufferedImage[][] MENUPOINT = load("/Hud/S_Dagger03.png", 34, 34);
    public static BufferedImage[][] BG = load("/Hud/bg.png", 1600, 928);
    //Font
    public static BufferedImage[][] FONT = load("/Hud/kromasky_16x16.png", 16, 16);
    public static BufferedImage[][] FONT2 = load("/Hud/kromasky2_16x16.png", 16, 16);
    public static BufferedImage[][] FONT3 = load("/Hud/kromagrad_16x16.png", 16, 16);
    //Map
    public static BufferedImage[][] TILE = load("/Map/terrain_atlas.png", 32, 32);
    public static BufferedImage[][] MAP = load("/Map/map.png", 32, 32);
    //Unit
    public static BufferedImage[][] Skeleton1_Walk = load("/Entity/unit/walkcycle/skeleton1.png", 32, 32);
    public static BufferedImage[][] Skeleton2_Walk = load("/Entity/unit/walkcycle/skeleton2.png", 32, 32);
    public static BufferedImage[][] Skeleton3_Walk = load("/Entity/unit/walkcycle/skeleton3.png", 32, 32);
    public static BufferedImage[][] Advanturer_Robe_Walk = load("/Entity/unit/walkcycle/advanturer_robe.png", 32, 32);
    public static BufferedImage[][] Advanturer_Plate_Walk = load("/Entity/unit/walkcycle/advanturer_plate_armor.png", 32, 32);
    public static BufferedImage[][] Advanturer_Leather_Walk = load("/Entity/unit/walkcycle/advanturer_leather.png", 32, 32);
    
    public static BufferedImage[][] Skeleton1_Slash = load("/Entity/unit/slash/skeleton1.png", 32, 32);
    public static BufferedImage[][] Skeleton2_Slash = load("/Entity/unit/slash/skeleton2.png", 32, 32);
    public static BufferedImage[][] Skeleton3_Slash = load("/Entity/unit/slash/skeleton3.png", 32, 32);
    public static BufferedImage[][] Advanturer_Robe_Slash = load("/Entity/unit/slash/advanturer_robe.png", 32, 32);
    public static BufferedImage[][] Advanturer_Plate_Slash = load("/Entity/unit/slash/advanturer_plate_armor.png", 32, 32);
    public static BufferedImage[][] Advanturer_Leather_Slash = load("/Entity/unit/slash/advanturer_leather.png", 32, 32);
    
    //Building
    public static BufferedImage[][] TOWN_HALL = load("/Entity/building/Town_Hall.png", 128, 128);
    public static BufferedImage[][] TOWN_HALL_ICON = load("/Entity/building/TOWN_HALL_ICON.png", 46, 38);
    public static BufferedImage[][] GUILD = load("/Entity/building/GUILD.png", 96, 96);
    public static BufferedImage[][] GUILD_ICON = load("/Entity/building/GUILD_ICON.png", 46, 38);
    public static BufferedImage[][] MARKET = load("/Entity/building/MARKET.png", 64, 64);
    public static BufferedImage[][] MARKET_ICON = load("/Entity/building/MARKET_ICON.png", 46, 38);
    public static BufferedImage[][] BLACKSMITH = load("/Entity/building/BLACKSMITH.png", 64, 64);
    public static BufferedImage[][] BLACKSMITH_ICON = load("/Entity/building/blacksmith_icon.png", 46, 38);
    public static BufferedImage[][] GUARD_TOWER = load("/Entity/building/GUARD_TOWER.png", 64, 64);
    public static BufferedImage[][] GUARD_TOWER_ICON = load("/Entity/building/GUARD_TOWER_ICON.png", 46, 38);
    public static BufferedImage[][] MINE = load("/Entity/building/gold_mine.png", 96, 96);
    
    public static BufferedImage[][] load(String s, int w, int h) {
        try {
        BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(s));
        int width = spritesheet.getWidth() / w;
        int height = spritesheet.getHeight() / h;
        BufferedImage[][] ret = new BufferedImage[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
                }
            }
            return ret;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error.");
            System.exit(0);
        }
        return null;
    }
    
    public static void drawString(Graphics2D g, String s, int x, int y) {
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            int Index = 0;
            char c = s.charAt(i);
            if (c == '.') {
                Index = 224;
            }
            if (c == '-') {
                Index = 224-16;
            }
            if (c == ',') {
                Index = 224-32;
            }
            if ((c >= 'A') && (c <= 'Z')) {
                Index = ((c-65)*16+528);
            }
            if ((c >= '0') && (c <= '9')) {
                Index = ((c-48)*16+256);
            }
            int col = Index / 16;
            g.drawImage(FONT[0][col], x + i * 16, y, null);
        }
    }
    
    public static void drawString2(Graphics2D g, String s, int x, int y) {
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            int Index = 0;
            char c = s.charAt(i);
            if ((c >= 'A') && (c <= 'Z')) {
                Index = ((c-65)*16+528);
            }
            if ((c >= '0') && (c <= '9')) {
                Index = ((c-48)*16+256);
            }
            int col = Index / 16;
            g.drawImage(FONT2[0][col],x + i * 16, y, null);
        }
    }
    
    public static void drawTile(Graphics2D g, int yTile, int xTile, int x, int y) {
        g.drawImage(TILE[yTile][xTile], x, y, null);
    }
    
    public static void drawMap(Graphics2D g, int x, int y) {
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                g.drawImage(MAP[row][col], x+ 32*col, y+ 32*row, null);
            }
        }
    }
    
    public static void drawTileTypeNum(Graphics2D g, int[][] i, int x, int y) {
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                int index = i[row][col];
                
                if ((index >= 0) && (index <= 9)) {
                    index = (index*16+256);
                }
                int font_col = index / 16;
                g.drawImage(FONT[0][font_col], x+32*col, y+32*row, null);
            }
        }
    }
}
