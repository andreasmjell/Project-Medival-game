package src;

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Settlement extends GameObject implements Drawable {
    protected String name;
    protected int x;
    protected int y;
    protected int troops;
    protected int timer;
    protected MapController mapController;
    protected Image settlementImage;

    public Settlement(String name, int x, int y, int troops, int timer, MapController mapController, String imagePath) {
        super(x, y, 50, 50);
        this.name = name;
        this.x = x;
        this.y = y;
        this.troops = troops;
        this.timer = timer;
        this.mapController = mapController;
        this.settlementImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public String getName() { return name; }
    public Settlement getSettlement() { return this; }
    public Drawable getThis() { return this; }

    @Override
    public void draw(Graphics g, double cameraX, double cameraY) {
        int sx = (int)(x - cameraX - 50);
        int sy = (int)(y - cameraY - 50);
        g.drawImage(settlementImage, sx, sy, 100, 100, null);
    }

    // Hver subklasse bestemmer selv hva som skjer ved kollisjon
    @Override
    public abstract void onCollision(Player player);
}