/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import Map.TileMap;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author hour
 */
public abstract class Entity {
    
    protected BufferedImage[] downSprites, downSprites1, downSprites2, downSprites3;
    protected BufferedImage[] leftSprites, leftSprites1, leftSprites2, leftSprites3;
    protected BufferedImage[] rightSprites, rightSprites1, rightSprites2, rightSprites3;
    protected BufferedImage[] upSprites, upSprites1, upSprites2, upSprites3;
    
    protected BufferedImage[] downSlash, downSlash1, downSlash2, downSlash3;
    protected BufferedImage[] leftSlash, leftSlash1, leftSlash2, leftSlash3;
    protected BufferedImage[] rightSlash, rightSlash1, rightSlash2, rightSlash3;
    protected BufferedImage[] upSlash, upSlash1, upSlash2, upSlash3;
    
    public int team;
    public String name;
    public int level;
    public int hp;
    public int maxhp;
    public int attack;
    public double exp;
    public double expUp;
    public int slot;
    public int cost;
    public double modifier;
    public double xp_modifier;
    public final int detectTile = 5;
    
    protected int xcol;
    protected int yrow;
    protected boolean empty;
    protected int width;
    protected int height;
    protected int current_x;
    protected int current_y;
    protected int xdest;
    protected int ydest;
    protected int lastx;
    protected int lasty;
    public int rowTile;
    public int colTile;
    protected int detectY;
    protected int detectX;
    
    protected boolean moving;
    protected boolean standby;
    public boolean fight;
    public boolean patrol;
    public boolean detect;
    
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected final int moveSpeed = 1;
    
    protected TileMap tileMap;
    protected int tileSize;
    protected int xmap;
    protected int ymap;
    protected Animation animation;
    protected int currentAnimation;
    
    protected ArrayList<int[]> tileChanges;

    public Entity(TileMap tm) {
        this.tileMap = tm;
        this.tileSize = this.tileMap.getTileSize();
        this.animation = new Animation();
        this.tileChanges = new ArrayList();
    }
    
    public void addChange(int[] i) {
        this.tileChanges.add(i);
    }
    
    public ArrayList<int[]> getChanges() {
        return this.tileChanges;
    }
    
    public int getx() {
        return this.current_x;
    }

    public int gety() {
        return this.current_y;
    }

    public int getRow() {
        return this.rowTile;
    }

    public int getCol() {
        return this.colTile;
    }
    
    public boolean intersects(Entity en) {
        return getBounds().intersects(en.getBounds());
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.current_x - 1, this.current_y - 1, this.width + 2, this.height + 2);
    }
    
    public Rectangle getDetect() {
        return new Rectangle(this.current_x - 200, this.current_y - 200, this.width + 400, this.height + 400);
    }

    public void setPosition(int num1, int num2) {
        this.current_x = num1;
        this.current_y = num2;
        this.xdest = this.current_x;
        this.ydest = this.current_y;
    }
    
    public void setMapPosition() {
        this.xmap = this.tileMap.getx();
        this.ymap = this.tileMap.gety();
    }
    
    public void setTilePosition(int num1, int num2) {
        this.current_x = (num1 * this.tileSize + this.tileSize / 2);
        this.current_y = (num2 * this.tileSize + this.tileSize / 2);
        this.xdest = this.current_x;
        this.ydest = this.current_y;
    }
    
    public void draw(Graphics2D g) {
        setMapPosition();
        g.drawImage(
        this.animation.getImage(), 
        this.current_x + this.xmap - this.width / 2, 
        this.current_y + this.ymap - this.height / 2, 
        null);
    }
}
