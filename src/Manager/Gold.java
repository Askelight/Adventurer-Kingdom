/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entity.Entity;

/**
 *
 * @author hour
 */
public class Gold {
    
    private int currentGold, prevGold;
    private String goldString = "";
    long lastTax = System.currentTimeMillis();

    public Gold() {
        this.currentGold = this.prevGold = 500000;
        this.goldString = this.currentGold + "";
    }
    
    public String getGoldString() {
        return this.goldString;
    }
    
    public int getGold() {
        return this.currentGold;
    }
    
    public void spenGold(int i) {
        this.currentGold = this.currentGold - i;
    }
    
    public void lootGold(int i) {
        this.currentGold = this.currentGold + i;
    }
    
    public void update() {
        if (System.currentTimeMillis() - lastTax >= 1000) {
            this.currentGold += 25;
            
            lastTax = System.currentTimeMillis();
        }
        if (currentGold != prevGold) {
            this.goldString = this.currentGold + "";
            this.prevGold = this.currentGold;
        }
    }
}
