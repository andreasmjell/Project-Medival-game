package src;

import java.awt.*;

import javax.swing.ImageIcon;

public class Settlement extends GameObject implements Drawable{
    String name;
    int x;
    int y;
    int troops;
    int timer;
    MapController mapController;
    Image settlementImage = new ImageIcon(getClass().getResource("assets/by.png")).getImage();

    public Settlement(String name, int x, int y, int troops, int timer, MapController mapController){
        super((int)x, (int)y, 50, 50);
        this.name = name;
        this.x = x;
        this.y = y;
        this.troops = troops;
        this.timer = timer;
        this.mapController = mapController;
    }
    

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public Drawable getThis(){
        return this;
    }

    public void draw(Graphics g, double cameraX, double cameraY){
        int sx = (int)(x - cameraX - 50); //Settlement X
        int sy = (int)(y - cameraY - 50); //Settlement Y
        g.drawImage(settlementImage, sx, sy, 100, 100, null);
    }

    public String getName(){
        return name;
    }

    public Settlement getSettlement(){
        return this;
    }

    public void onCollision(Player player){  
        System.out.println("Player gikk i byen: " + name);
        mapController.getPlayer().updateTroops(troops);
        mapController.openSettlementMenu(this);
    }
}
