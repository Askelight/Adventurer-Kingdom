/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;


import Entity.Adventurer;
import Entity.Blacksmith;
import Entity.GuardTower;
import Entity.Guild;
import Entity.Hall;
import Entity.Market;
import Entity.Mine;
import Entity.Quest;
import Entity.Skeleton;
import Hud.Hud;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Gold;
import Manager.Keys;
import Manager.Mouse;
import Manager.Sound;
import Map.TileMap;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class PlayState extends GameState {
    
    private Mouse m;
    
    private ArrayList<Adventurer> adventurer;
    private ArrayList<Skeleton> skeleton;
    
    private ArrayList<Hall> hall;
    private ArrayList<Guild> guild;
    private ArrayList<Market> market;
    private ArrayList<Blacksmith> blacksmith;
    private ArrayList<GuardTower> guardtower;
    private ArrayList<Mine> mine;
    private ArrayList<Quest> quest;
    
    private TileMap tileMap;
    private Hud hud;
    public Gold gold;
    
    private int spownX;
    private int spownY;
    private int spownTime = 0;
    private int healTime = 0;
    
    private int mouseState;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        
        Sound.load("/Sound/click.wav", "click");
        Sound.load("/Sound/rumble.wav", "rumble");
        Sound.setVolume("bgPlay", -20.0F);
        Sound.load("/Sound/Fantasy Choir 1.wav", "bgPlay");
        Sound.setVolume("bgPlay", -10.0F);
        Sound.loop("bgPlay", 1000, 1000, Sound.getFrames("bgPlay") - 1000);
        
        this.hall = new ArrayList();
        this.guild = new ArrayList();
        this.market = new ArrayList();
        this.blacksmith = new ArrayList();
        this.guardtower = new ArrayList();
        this.mine = new ArrayList();
        this.quest = new ArrayList();
        
        tileMap = new TileMap();
        hud = new Hud();
        tileMap.TileType();
        gold = new Gold();

        this.skeleton = new ArrayList();
        this.adventurer = new ArrayList();
        
        Adventurer adv = new Adventurer(this.tileMap);
        adv.setTilePosition(70, 20);
        this.adventurer.add(adv);
                            
        spownMine();
        
        this.mouseState = 0;
    }

    @Override
    public void update() {
        handleInput();
        
        this.hud.update();
        
        if (this.quest.size() > 0) {
            Quest q = (Quest)this.quest.get(0);
            q.update();
        }
        
        for (int i = 0; i < this.adventurer.size(); i++) {
            Adventurer adv = (Adventurer)this.adventurer.get(i);
            adv.update();
        }
        for (int i = 0; i < this.skeleton.size(); i++) {
            Skeleton ske = (Skeleton)this.skeleton.get(i);
            ske.update();
        }
        
        for (int i = 0; i < this.hall.size(); i++) {
            Hall h = (Hall)this.hall.get(i);
            ArrayList<int[]> ali = h.getChanges();
            ali.forEach((j) -> {
                this.tileMap.setTile(j[0], j[1], j[2]);
            });
        }
        
        for (int i = 0; i < this.guild.size(); i++) {
            Guild gui = (Guild)this.guild.get(i);
            ArrayList<int[]> ali = gui.getChanges();
            ali.forEach((j) -> {
                this.tileMap.setTile(j[0], j[1], j[2]);
            });
        }
        
        for (int i = 0; i < this.market.size(); i++) {
            Market mar = (Market)this.market.get(i);
            ArrayList<int[]> ali = mar.getChanges();
            ali.forEach((j) -> {
                this.tileMap.setTile(j[0], j[1], j[2]);
            });
        }
        
        for (int i = 0; i < this.blacksmith.size(); i++) {
            Blacksmith b = (Blacksmith)this.blacksmith.get(i);
            ArrayList<int[]> ali = b.getChanges();
            ali.forEach((j) -> {
                this.tileMap.setTile(j[0], j[1], j[2]);
            });
        }
        
        for (int i = 0; i < this.guardtower.size(); i++) {
            GuardTower gua = (GuardTower)this.guardtower.get(i);
            ArrayList<int[]> ali = gua.getChanges();
            ali.forEach((j) -> {
                this.tileMap.setTile(j[0], j[1], j[2]);
            });
        }
        
        if (this.quest.size() > 0) {
            Adventurer adv = (Adventurer)this.adventurer.get(this.adventurer.size()-1);
            Quest que = (Quest)this.quest.get(this.quest.size()-1);
            int i = 1;
            while (i > this.adventurer.size() && adv.fight) {
                adv = (Adventurer)this.adventurer.get(this.adventurer.size()-1-i);
                i++;
            }
            if (!adv.detect) {
                adv.patrol = false;
                if (que.rowTile > adv.rowTile && tileMap.getTileType(adv.rowTile+1,adv.colTile) == 0) {
                    adv.setDown();
                }
                if (que.rowTile < adv.rowTile && tileMap.getTileType(adv.rowTile-1,adv.colTile) == 0) {
                    adv.setUp();
                }
                if (que.colTile > adv.colTile && tileMap.getTileType(adv.rowTile,adv.colTile+1) == 0) {
                    adv.setRight();
                }
                if (que.colTile < adv.colTile && tileMap.getTileType(adv.rowTile,adv.colTile-1) == 0) {
                    adv.setLeft();
                }
            }
        }
        
        for (int i = 0; i < this.mine.size(); i++) {
            Mine min = (Mine)this.mine.get(i);
            if (spownTime > 700) {
                Skeleton ske = new Skeleton(this.tileMap);
                ske.setTilePosition(min.getSpownX(), min.getSpownY()+2);
                this.skeleton.add(ske);
                spownTime = 0;
            }
            spownTime++;
            ArrayList<int[]> ali = min.getChanges();
            ali.forEach((j) -> {
                this.tileMap.setTile(j[0], j[1], j[2]);
            });
        }
        
        for (int i = 0; i < this.skeleton.size(); i++) {
            for (int j = 0; j < this.adventurer.size(); j++) {
                Skeleton ske = (Skeleton)this.skeleton.get(i);
                Adventurer adv = (Adventurer)this.adventurer.get(j);
                if (ske.intersects(adv)) {
                    ske.battle(adv);

                    if (adv.hp <= 0) {
                        this.adventurer.remove(j);
                        j--;

                        ArrayList<int[]> ali = adv.getChanges();
                        ali.forEach((k) -> {
                            this.tileMap.setTile(k[0], k[1], 0);
                        });
                    }
                }
            }
        }
        
        for (int i = 0; i < this.adventurer.size(); i++) {
            for (int j = 0; j < this.skeleton.size(); j++) {
                Skeleton ske = (Skeleton)this.skeleton.get(j);
                Adventurer adv = (Adventurer)this.adventurer.get(i);
                if (adv.intersects(ske)) {
                    adv.battle(ske);
                    if (healTime > 500) {
                        adv.hp += hud.getPotion();
                        healTime = 0;
                    }
                    healTime++;
                    if (ske.hp <= 0) {
                        gold.lootGold((int) (ske.cost * ((ske.level*ske.modifier+1)-ske.level)));
                        System.out.println("Recieve " + ske.cost * ((ske.level*ske.modifier+1)-ske.level) + "Gold");
                        this.skeleton.remove(j);
                        j--;

                        ArrayList<int[]> ali = ske.getChanges();
                        ali.forEach((k) -> {
                            this.tileMap.setTile(k[0], k[1], 0);
                        });
                    }
                }
            }
        }

        for (int i = 0; i < this.skeleton.size(); i++) {
            for (int j = 0; j < this.guardtower.size(); j++) {
                Skeleton ske = (Skeleton)this.skeleton.get(i);
                GuardTower gua = (GuardTower)this.guardtower.get(j);
                if (ske.getDetect().intersects(gua.getDetect())) {
                    ske.battleBuilding(gua);
                    if (gua.hp <= 0) {
                        this.guardtower.remove(j);
                        j--;

                        ArrayList<int[]> ali = gua.getChanges();
                        for (int[] k : ali) {
                            this.tileMap.setTile(k[0], k[1], 0);
                        }
                        if (this.guardtower.size() < 1) {
                           hud.setGua(false); 
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < this.guardtower.size(); i++) {
            for (int j = 0; j < this.skeleton.size(); j++) {
                Skeleton ske = (Skeleton)this.skeleton.get(j);
                GuardTower gua = (GuardTower)this.guardtower.get(i);
                if (gua.getDetect().intersects(ske.getDetect())) {
                    gua.battleGuard(ske);
                    if (ske.hp <= 0) {
                        this.skeleton.remove(j);
                        j--;

                        ArrayList<int[]> ali = ske.getChanges();
                        for (int[] k : ali) {
                            this.tileMap.setTile(k[0], k[1], 0);
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < this.skeleton.size(); i++) {
            for (int j = 0; j < this.guild.size(); j++) {
                Skeleton ske = (Skeleton)this.skeleton.get(i);
                Guild gui = (Guild)this.guild.get(j);
                if (ske.getDetect().intersects(gui.getDetect())) {
                    ske.battleBuilding(gui);
                    if (gui.hp <= 0) {
                        this.guild.remove(j);
                        j--;

                        ArrayList<int[]> ali = gui.getChanges();
                        for (int[] k : ali) {
                            this.tileMap.setTile(k[0], k[1], 0);
                        }
                        if (this.guild.size() < 1) {
                           hud.setGui(false); 
                        }
                    }
                }
            }
        }
        for (int i = 0; i < this.skeleton.size(); i++) {
            for (int j = 0; j < this.market.size(); j++) {
                Skeleton ske = (Skeleton)this.skeleton.get(i);
                Market mar = (Market)this.market.get(j);
                if (ske.getDetect().intersects(mar.getDetect())) {
                    ske.battleBuilding(mar);
                    if (mar.hp <= 0) {
                        this.market.remove(j);
                        j--;

                        ArrayList<int[]> ali = mar.getChanges();
                        for (int[] k : ali) {
                            this.tileMap.setTile(k[0], k[1], 0);
                        }
                        if (this.market.size() < 1) {
                           hud.setMar(false); 
                        }
                    }
                }
            }
        }
        for (int i = 0; i < this.skeleton.size(); i++) {
            for (int j = 0; j < this.blacksmith.size(); j++) {
                Skeleton ske = (Skeleton)this.skeleton.get(i);
                Blacksmith bla = (Blacksmith)this.blacksmith.get(j);
                if (ske.getDetect().intersects(bla.getDetect())) {
                    ske.battleBuilding(bla);
                    if (bla.hp <= 0) {
                        this.blacksmith.remove(j);
                        j--;

                        ArrayList<int[]> ali = bla.getChanges();
                        for (int[] k : ali) {
                            this.tileMap.setTile(k[0], k[1], 0);
                        }
                        if (this.blacksmith.size() < 1) {
                           hud.setBla(false); 
                        }
                    }
                }
            }
        }
        for (int i = 0; i < this.skeleton.size(); i++) {
            for (int j = 0; j < this.hall.size(); j++) {
                Skeleton ske = (Skeleton)this.skeleton.get(i);
                Hall h = (Hall)this.hall.get(j);
                if (ske.getDetect().intersects(h.getDetect())) {
                    ske.battleBuilding(h);
                    if (h.hp <= 0) {
                        this.hall.remove(j);
                        j--;

                        ArrayList<int[]> ali = h.getChanges();
                        for (int[] k : ali) {
                            this.tileMap.setTile(k[0], k[1], 0);
                        }
                        if (this.hall.size() < 1) {
                           hud.setTow(false); 
                        }
                    }
                }
            }
        }
        for (int i = 0; i < this.adventurer.size(); i++) {
            for (int j = 0; j < this.mine.size(); j++) {
                Adventurer adv = (Adventurer)this.adventurer.get(i);
                Mine min = (Mine)this.mine.get(j);
                if (adv.getDetect().intersects(min.getDetect())) {
                    adv.battleBuilding(min);
                    if (min.hp <= 0) {
                        this.mine.remove(j);
                        j--;

                        ArrayList<int[]> ali = min.getChanges();
                        for (int[] k : ali) {
                            this.tileMap.setTile(k[0], k[1], 0);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < this.adventurer.size(); i++) {
            for (int j = 0; j < this.quest.size(); j++) {
                Adventurer adv = (Adventurer)this.adventurer.get(i);
                Quest que = (Quest)this.quest.get(j);
                if (adv.getDetect().intersects(que.getDetect())) {
                    this.quest.remove(j);
                    j--;
                }
            }
        }
        //Win and Lose Condition
        if (this.hall.isEmpty() && this.guild.isEmpty() && this.market.isEmpty() && 
            this.blacksmith.isEmpty() && this.guardtower.isEmpty() && this.adventurer.isEmpty()) {
            Sound.stop("bgPlay");
            this.gsm.setState(3);
            System.out.println("Defeat");
        } else if (this.skeleton.isEmpty() && this.mine.isEmpty()) {
            Sound.stop("bgPlay");
            this.gsm.setState(3);
            System.out.println("Victory");
        }
    }

    @Override
    public void draw(Graphics2D g) {
        //Draw Map
        this.tileMap.draw(g);
        
        //Draw Building
        this.hall.forEach((h) -> {
            h.draw(g);
        });
        this.guild.forEach((gui) -> {
            gui.draw(g);
        });
        this.market.forEach((mar) -> {
            mar.draw(g);
        });
        this.blacksmith.forEach((b) -> {
            b.draw(g);
        });
        this.guardtower.forEach((gua) -> {
            gua.draw(g);
        });
        this.mine.forEach((min) -> {
            min.draw(g);
        }); 
        this.quest.forEach((que) -> {
            que.draw(g);
        });

        //Debut Show TileType
        //Content.drawTileTypeNum(g, tileMap.getTileType(), tileMap.getx(), tileMap.gety());
        
        this.adventurer.forEach((adv) -> {
            adv.draw(g);
        });
        this.skeleton.forEach((ske) -> {
            ske.draw(g);
        });
        
        this.gold.update();
        
        //Draw Hud
        this.hud.draw(g);
        
        //Draw Gold
        Content.drawString2(g, this.gold.getGoldString(), 1600-this.gold.getGoldString().length()*16-4, 900-18);
    }

    @Override
    public void handleInput() {
        // Camera Movement
        if (Keys.isDown(Keys.UP)){
            tileMap.moveUp();
        }
        if (Keys.isDown(Keys.DOWN)){
            tileMap.moveDown();
        }
        if (Keys.isDown(Keys.LEFT)){
            tileMap.moveLeft();
        }
        if (Keys.isDown(Keys.RIGHT)){
            tileMap.moveRight();
        }
        if (Keys.isPressed(Keys.ESCAPE)) {
            Sound.stop("bgPlay");
            this.gsm.setPaused(true);
        }
        //Click to build
        if (m.mouseX() > 1600-46 && (m.mouseX() < 1600) && this.mouseState == 0) {
            if ((m.mouseY() > 900-288) && (m.mouseY() < 900-288+38)) {
                if (m.isPressed()) {
                    System.out.println("Selected Town Hall");
                    this.mouseState = 1;
                }
            }
            if (hud.isTow()) {
                if ((m.mouseY() > 900-288+38) && (m.mouseY() < 900-288+38*2) && !hud.isGui()) {
                    if (m.isPressed()) {
                        System.out.println("Selected Guild");
                        this.mouseState = 2;
                    }
                }
                if ((m.mouseY() > 900-288+38*2) && (m.mouseY() < 900-288+38*3) && !hud.isMar()) {
                    if (m.isPressed()) {
                        System.out.println("Selected Market");
                        this.mouseState = 3;
                    }
                }
                if ((m.mouseY() > 900-288+38*3) && (m.mouseY() < 900-288+38*4) && !hud.isBla()) {
                    if (m.isPressed()) {
                        System.out.println("Selected Blacksmith");
                        this.mouseState = 4;
                    }
                }
                if ((m.mouseY() > 900-288+38*4) && (m.mouseY() < 900-288+38*5)) {
                    if (m.isPressed()) {
                        System.out.println("Selected Guard Tower");
                        this.mouseState = 5;
                    }
                }
            }
        }
        
        //BuyUnit
        if (hud.isGui()) {
            if (m.mouseX() > 1600-448+16 && (m.mouseX() < 1600-448+48) && this.mouseState == 0) {
                if ((m.mouseY() > 900-288) && (m.mouseY() < 900-288+32)) {
                    if (m.isReleased() && gold.getGold() >= 100) {
                        System.out.println("brought swordman");
                        Adventurer adv = new Adventurer(this.tileMap);
                        adv.setTilePosition(this.spownX, this.spownY);
                        this.adventurer.add(adv);
                        Sound.play("click");
                        gold.spenGold(100);
                        m.mouseReleased = false;
                    }
                }
                if ((m.mouseY() > 900-288+48) && (m.mouseY() < 900-288+32+48)) {
                    if (m.isReleased() && gold.getGold() >= 500) {
                        System.out.println("brought swordmaster");
                        Adventurer adv = new Adventurer(this.tileMap);
                        adv.setTilePosition(this.spownX, this.spownY);
                        this.adventurer.add(adv);
                        adv.level = 5;
                        adv.expUp += 100*(adv.level-1);
                        adv.maxhp = (int) (adv.maxhp * ((adv.level*adv.modifier+1)-adv.level) + hud.getArmor()*10);
                        adv.attack = (int) (adv.attack * ((adv.level*adv.modifier+1)-adv.level) + hud.getSword());
                        Sound.play("click");
                        gold.spenGold(500);
                        m.mouseReleased = false;
                    }
                }
                if ((m.mouseY() > 900-288+48*2) && (m.mouseY() < 900-288+32+48*2)) {
                    if (m.isReleased() && gold.getGold() >= 1000) {
                        System.out.println("brought swordmaster");
                        Adventurer adv = new Adventurer(this.tileMap);
                        adv.setTilePosition(this.spownX, this.spownY);
                        this.adventurer.add(adv);
                        adv.level = 9;
                        adv.expUp += 100*(adv.level-1);
                        adv.maxhp = (int) (adv.maxhp * ((adv.level*adv.modifier+1)-adv.level) + hud.getArmor()*10);
                        adv.attack = (int) (adv.attack * ((adv.level*adv.modifier+1)-adv.level) + hud.getSword());
                        Sound.play("click");
                        gold.spenGold(1000);
                        m.mouseReleased = false;
                    }
                }
            }
        }
        
        //BuyUpgrade
        if (hud.isBla()) {
            if (m.mouseX() > 1600-448+195 && (m.mouseX() < 1600-448+195+34) && this.mouseState == 0) {
                if ((m.mouseY() > 900-288+5) && (m.mouseY() < 900-288+5+34)) {
                    if (m.isReleased()) {
                        if (hud.getArmor() == 1) {
                            hud.setArmor(2);
                            Sound.play("click");
                            gold.spenGold(300);
                        } else if (hud.getArmor() == 2) {
                            hud.setArmor(3);
                            Sound.play("click");
                            gold.spenGold(800);
                        } else {
                            System.out.println("No More Upgrade");
                        }
                        m.mouseReleased = false;
                    }
                }
                if ((m.mouseY() > 900-288+75) && (m.mouseY() < 900-288+75+34)) {
                    if (m.isReleased()) {
                        if (hud.getSword() == 1) {
                            hud.setSword(2);
                            Sound.play("click");
                            gold.spenGold(500);
                        } else if (hud.getSword() == 2) {
                            hud.setSword(3);
                            Sound.play("click");
                            gold.spenGold(1000);
                        } else {
                            System.out.println("No More Upgrade");
                        }
                        m.mouseReleased = false;
                    }
                }
            }
        }
        if (hud.isMar()) {
            if (m.mouseX() > 1600-448+195 && (m.mouseX() < 1600-448+195+34) && this.mouseState == 0) {
                if ((m.mouseY() > 900-288+145) && (m.mouseY() < 900-288+145+34)) {
                    if (m.isReleased()) {
                        if (hud.getPotion() == 1) {
                            hud.setPotion(2);
                            Sound.play("click");
                            gold.spenGold(400);
                        } else if (hud.getPotion() == 2) {
                            hud.setPotion(3);
                            Sound.play("click");
                            gold.spenGold(900);
                        } else {
                            System.out.println("No More Upgrade");
                        }
                        m.mouseReleased = false;
                    }
                }
            }
        }
        //Make Quest
        if (this.adventurer.size() > 0 && this.quest.size() < 1) {
            if (m.mouseX() > 1600-448+16 && (m.mouseX() < 1600-448+16+34) && this.mouseState == 0) {
                if ((m.mouseY() > 900-34) && (m.mouseY() < 900)) {
                    if (m.isPressed()) {
                        System.out.println("Make Quest");
                        this.mouseState = 6;
                        m.mouseReleased = false;
                    }
                }
            }
        }
        if (this.quest.size() > 0) {
            if (m.mouseX() > 1600-448+16+34 && (m.mouseX() < 1600-448+16+34*2) && this.mouseState == 0) {
                if ((m.mouseY() > 900-34) && (m.mouseY() < 900)) {
                    if (m.isReleased()) {
                        System.out.println("Remove Quest");
                        Sound.play("click");
                        this.quest.remove(0);
                        m.mouseReleased = false;
                    }
                }
            }
        }
        
        switch (this.mouseState) {
            case 1: if (m.isReleased() ) {
                        Hall h = new Hall(this.tileMap);
                        if (h.scanSite() && gold.getGold() >= h.cost) {
                            h.setTilePosition(h.getBuildCol(), h.getBuildRow());
                            h.build();
                            this.hall.add(h);
                            Sound.play("rumble");
                            gold.spenGold(h.cost);
                            hud.setTow(true);
                        }
                        m.mouseReleased = false;
                        this.mouseState = 0;
                    };
                    break;
            case 2: if (m.isReleased() ) {
                        Guild gui = new Guild(this.tileMap);
                        if (gui.scanSite() && gold.getGold() >= gui.cost) {
                            gui.setTilePosition(gui.getBuildCol(), gui.getBuildRow());
                            gui.build();
                            this.guild.add(gui);
                            Sound.play("rumble");
                            gold.spenGold(gui.cost);
                            hud.setGui(true);
                            
                            //spown unit locat
                            this.spownX = gui.getBuildCol();
                            this.spownY = gui.getBuildRow() + 2;
                        }
                        m.mouseReleased = false;
                        this.mouseState = 0;
                    };
                    break;
            case 3: if (m.isReleased() ) {
                        Market mar = new Market(this.tileMap);
                        if (mar.scanSite() && gold.getGold() >= mar.cost) {
                            mar.setTilePosition(mar.getBuildCol(), mar.getBuildRow());
                            mar.build();
                            this.market.add(mar);
                            Sound.play("rumble");
                            gold.spenGold(mar.cost);
                            hud.setMar(true);
                            hud.setPotion(1);
                        }
                        m.mouseReleased = false;
                        this.mouseState = 0;
                    };
                    break;
            case 4: if (m.isReleased() ) {
                        Blacksmith bla = new Blacksmith(this.tileMap);
                        if (bla.scanSite() && gold.getGold() >= bla.cost) {
                            bla.setTilePosition(bla.getBuildCol(), bla.getBuildRow());
                            bla.build();
                            this.blacksmith.add(bla);
                            Sound.play("rumble");
                            gold.spenGold(bla.cost);
                            hud.setBla(true);
                            hud.setArmor(1);
                            hud.setSword(1);
                        }
                        m.mouseReleased = false;
                        this.mouseState = 0;
                    };
                    break;
            case 5: if (m.isReleased() ) {
                        GuardTower gua = new GuardTower(this.tileMap);
                        if (gua.scanSite() && gold.getGold() >= gua.cost) {
                            gua.setTilePosition(gua.getBuildCol(), gua.getBuildRow());
                            gua.build();
                            this.guardtower.add(gua);
                            Sound.play("rumble");
                            gold.spenGold(gua.cost);
                            hud.setGua(true);
                        }
                        m.mouseReleased = false;
                        this.mouseState = 0;
                    };
                    break;
            case 6: if (m.isReleased()) {
                        Quest q = new Quest(this.tileMap);
                        if (gold.getGold() >= q.cost) {
                            q.setTilePosition(q.getCol(), q.getRow());
                            this.quest.add(q);
                            Sound.play("click");
                            gold.spenGold(q.cost);
                        }
                        m.mouseReleased = false;
                        this.mouseState = 0;
            }
        }
    }
    
    public void spownMine() {
        Mine min = new Mine(this.tileMap);
        min.setSpownX(51);
        min.setSpownY(81);
        min.buildMine(min);
        this.mine.add(min);
        
        min = new Mine(this.tileMap);
        min.setSpownX(80);
        min.setSpownY(60);
        min.buildMine(min);
        this.mine.add(min);
        
        min = new Mine(this.tileMap);
        min.setSpownX(86);
        min.setSpownY(75);
        min.buildMine(min);
        this.mine.add(min);
        
        min = new Mine(this.tileMap);
        min.setSpownX(20);
        min.setSpownY(50);
        min.buildMine(min);
        this.mine.add(min);
        
        min = new Mine(this.tileMap);
        min.setSpownX(50);
        min.setSpownY(40);
        min.buildMine(min);
        this.mine.add(min);
    }
}
