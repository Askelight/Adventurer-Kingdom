/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import static Manager.Content.MAP;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author hour
 */
public class TileMap {
    
    public int x;
    public int y;
    public int cam_speed = 32;

    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;
    
    public int[][] tileType = new int[100][100];

    public TileMap() {
        this.tileSize = 32;
        this.width = 3200;
        this.height = 3200;
        this.numRows = height/tileSize;
        this.numCols = width/tileSize;
    }
    
    public void TileType() {
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                this.tileType[row][col] = 1;
            }
        }
        for (int row = 10; row < 90; row++) {
            for (int col = 10; col < 90; col++) {
                this.tileType[row][col] = 0;
            }
        }
    }
    
    public int getTileSize() {
        return this.tileSize;
    }
    
    public int getx() {
        return this.x;
    }
    
    public int gety() {
        return this.y;
    }
    
    public int getColX() {
        return Math.abs(this.x) / 32;
    }
    
    public int getRowY() {
        return Math.abs(this.y) / 32;
    }
    
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumCols() {
        return this.numCols;
    }
    
    public void setTile(int row, int col, int type) {
        if (row > 0 && row < 100) {
            if (col > 0 && col < 100) {
                this.tileType[row][col] = type;
            }
        }
    }
    
    public int getTileType(int row, int col) {
        return this.tileType[row][col];
    }
    
    public int[][] getTileType() {
        return this.tileType;
    }
    
    public void draw(Graphics2D g) {
        for (int row = getRowY(); row < getRowY()+29; row++) {
            for (int col = getColX(); col < getColX()+50; col++) {
                g.drawImage(MAP[row][col], x + 32*col, y + 32*row, null);
            }
        }
    }
    
    public void moveUp() {
        this.y += cam_speed;
        if (this.y > 0) {
            this.y = 0;
        }
    }
    
    public void moveDown() {
        this.y -= cam_speed;
        if (this.y < 900-3200) {
            this.y = 900-3200;
        }
    }
    
    public void moveLeft() {
        this.x += cam_speed;
        if (this.x > 0) {
            this.x = 0;
        }
    }
    
    public void moveRight() {
        this.x -= cam_speed;
        if (this.x < 1600-3200) {
            this.x = 1600-3200;
        }
    }
}
