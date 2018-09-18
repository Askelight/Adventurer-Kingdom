/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Hud.Hud;
import Map.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author hour
 */
public class Unit extends Entity{
    
    long lastTurn = System.currentTimeMillis();
    int count = 0;
    
    public Unit(TileMap tm) {
        super(tm);
        
        exp = 0;
        expUp = 100;
        this.patrol = true;
        this.fight = false;
        this.detect = false;
    }
    
    public void setAction() {
        if (!this.fight) {
            if (this.patrol) {
                if (System.currentTimeMillis() - lastTurn >= 300) {
                    int i = (int) (Math.random() * 4);
                    if(i == 0){
                        setUp();
                    }
                    if(i == 1){
                        setLeft();
                    }
                    if(i == 2){
                        setDown();
                    }
                    if(i == 3){
                        setRight();
                    }
                    if (hp < maxhp) {
                        hp++;
                    }
                    lastTurn = System.currentTimeMillis();
                }
            }
            detect();
        }
    }

    public void battle(Entity en) {
        this.fight = true;
        if (System.currentTimeMillis() - lastTurn >= 500) {
            en.hp -= this.attack;
            this.exp += 10;
            if (exp >= expUp + (100*(level-1))) {
                expUp += 100*(level-1);
                level++;
                maxhp = (int) (maxhp * ((level*modifier+1)-level));
                attack = (int) (attack * ((level*modifier+1)-level));
            }
            lastTurn = System.currentTimeMillis();
        }
        if (en.hp <= 0) {
            for (int y = en.rowTile-1; y <= en.rowTile+1; y++) {
                for (int x = en.colTile-1; x <= en.colTile+1; x++) {
                    en.tileMap.setTile(y, x, 0);
                }
            }
            this.fight = false;
            this.patrol = true;
        }
    }
    
    public void battleBuilding(Entity en) {
        if (count > 20) {
            en.hp -= attack;
            exp += 10;
            if (exp >= expUp + (100*(level-1))) {
                expUp += 100*(level-1);
                level++;
                maxhp = (int) (maxhp * ((level*modifier+1)-level));
                attack = (int) (attack * ((level*modifier+1)-level));
            }
            count = 0;
        }
        count++;
    }
    
    public void detect() {
        if (this.detect) {
            if (detectY > this.rowTile + 1) {
                setDown();
            }
            if (detectY < this.rowTile - 1) {
                setUp();
            }
            if (detectX > this.colTile + 1) {
                setRight();
            }
            if (detectX < this.colTile - 1) {
                setLeft();
            }
            if (!this.fight && (detectX == this.colTile + 1 || detectX == this.colTile - 1)) {
                if (detectX == this.colTile + 1) {
                    this.animation.setFrames(this.rightSlash);
                }
                if (detectX == this.colTile - 1) {
                    this.animation.setFrames(this.leftSlash);
                }
                if (detectY == this.rowTile + 1) {
                    this.animation.setFrames(this.downSlash);
                }
                if (detectY == this.rowTile - 1) {
                    this.animation.setFrames(this.upSlash);
                }
            }
            this.detect = false;
        }
        this.rowTile = (this.current_y / this.tileSize);
        this.colTile = (this.current_x / this.tileSize);
        for (int y = this.rowTile - this.detectTile; y < this.rowTile + this.detectTile; y++) {
            for (int x = this.colTile - this.detectTile; x < this.colTile + this.detectTile; x++) {
                if ((this.tileMap.getTileType(y, x) != 0) && (this.tileMap.getTileType(y, x) != 1) && (this.tileMap.getTileType(y, x) != this.team && !this.fight)) {
                    detectX = x;
                    detectY = y;
                    this.detect = true;
                    break;
                }
                if (this.detect == false) {
                    this.fight = false;
                    this.patrol = true;
                }
            }
        }
    }
    
    public void setLeft() {
        if (this.moving) {
            return;
        }
        this.left = true;
        this.moving = validateNextPosition();
        this.animation.setFrames(this.leftSprites);
    }

    public void setRight() {
        if (this.moving) {
            return;
        }
        this.right = true;
        this.moving = validateNextPosition();
        this.animation.setFrames(this.rightSprites);
    }

    public void setUp() {
        if (this.moving) {
            return;
        }
        this.up = true;
        this.moving = validateNextPosition();
        this.animation.setFrames(this.upSprites);
    }

    public void setDown() {
        if (this.moving) {
            return;
        }
        this.down = true;
        this.moving = validateNextPosition();
        this.animation.setFrames(this.downSprites);
    }
    
    public boolean validateNextPosition() {
        if (this.moving) {
            return true;
        }
        this.rowTile = (this.current_y / this.tileSize);
        this.colTile = (this.current_x / this.tileSize);
        if (this.left) {
            if (this.tileMap.getTileType(this.rowTile, this.colTile - 1) != 0) {
                return false;
            }
            //Set New Posistion
            this.xdest = (this.current_x - this.tileSize);
            this.tileMap.setTile(this.rowTile, this.xdest / this.tileSize, this.team);
            this.tileMap.setTile(this.rowTile, (this.xdest + this.tileSize) / this.tileSize, 0);
        }
        if (this.right) {
            if (this.tileMap.getTileType(this.rowTile, this.colTile + 1) != 0) {
                return false;
            }
            this.xdest = (this.current_x + this.tileSize);
            this.tileMap.setTile(this.rowTile, this.xdest / this.tileSize, this.team);
            this.tileMap.setTile(this.rowTile, (this.xdest - this.tileSize) / this.tileSize, 0);
        }
        if (this.up) {
            if (this.tileMap.getTileType(this.rowTile - 1, this.colTile) != 0) {
                return false;
            }
            this.ydest = (this.current_y - this.tileSize);
            this.tileMap.setTile(this.ydest / this.tileSize, this.colTile, this.team);
            this.tileMap.setTile((this.ydest + this.tileSize) / this.tileSize, this.colTile, 0);
        }
        if (this.down) {
            if (this.tileMap.getTileType(this.rowTile + 1, this.colTile) != 0) {
                return false;
            }
            this.ydest = (this.current_y + this.tileSize);
            this.tileMap.setTile(this.ydest / this.tileSize, this.colTile, this.team);
            this.tileMap.setTile((this.ydest - this.tileSize) / this.tileSize, this.colTile, 0);
        }
        return true;
    }
    
    public void getNextPosition() {
        //left
        if ((this.left) && (this.current_x > this.xdest)) {
            this.current_x -= this.moveSpeed;
        } else {
            this.left = false;
        }
        if ((this.left) && (this.current_x < this.xdest)) {
            this.current_x = this.xdest;
        }
        //right
        if ((this.right) && (this.current_x < this.xdest)) {
            this.current_x += this.moveSpeed;
        } else {
            this.right = false;
        }
        if ((this.right) && (this.current_x > this.xdest)) {
            this.current_x = this.xdest;
        }
        //up
        if ((this.up) && (this.current_y > this.ydest)) {
            this.current_y -= this.moveSpeed;
        } else {
            this.up = false;
        }
        if ((this.up) && (this.current_y < this.ydest)) {
            this.current_y = this.ydest;
        }
        //down
        if ((this.down) && (this.current_y < this.ydest)) {
            this.current_y += this.moveSpeed;
        } else {
            this.down = false;
        }
        if ((this.down) && (this.current_y > this.ydest)) {
            this.current_y = this.ydest;
        }
    }
    public void setImg() {
        if (level >= 4 && level < 7) {
            this.upSprites = upSprites2;
            this.leftSprites = leftSprites2;
            this.downSprites = downSprites2;
            this.rightSprites = rightSprites2;
            this.upSlash = upSlash2;
            this.leftSlash = leftSlash2;
            this.downSlash = downSlash2;
            this.rightSlash = rightSlash2;
        } else if (level >= 7) {
            this.upSprites = upSprites3;
            this.leftSprites = leftSprites3;
            this.downSprites = downSprites3;
            this.rightSprites = rightSprites3;
            this.upSlash = upSlash3;
            this.leftSlash = leftSlash3;
            this.downSlash = downSlash3;
            this.rightSlash = rightSlash3;
        }
    }
    public void update() {
        setImg();
        setAction();
        
        if (this.moving) {
            getNextPosition();
        }
        if ((this.current_x == this.xdest) && (this.current_y == this.ydest)) {
            this.left = (this.right = this.up = this.down = this.moving = false);
        }
        this.animation.update();
    }

    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.BLACK);
        g.drawString(name, 
        this.current_x + this.xmap - this.width / 2, 
        this.current_y + this.ymap - this.height / 2);
        g.drawString("("+level+")", 
        this.current_x + this.xmap - this.width / 2 - 16, 
        this.current_y + this.ymap - this.height / 2);
        g.drawString(this.hp + "/" + this.maxhp, 
        this.current_x + this.xmap - this.width / 2, 
        this.current_y + this.ymap - this.height / 2 + 48);
    }
}
