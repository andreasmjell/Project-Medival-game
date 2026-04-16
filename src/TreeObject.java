package src;
import java.awt.*;

import javax.swing.ImageIcon;

public class TreeObject implements Drawable{
    Image sprite;
    double x;
    double y;
    Image img;

    public TreeObject(double x, double y, Image img){
        this.x = x;
        this.y = y;
        this.img = img;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public Image getSprite(){
        return sprite;
    }

    public void draw(Graphics g, double cameraX, double cameraY){
        int px = (int)(x - cameraX - 300);
        int py = (int)(y - cameraY -440);

        g.drawImage(img, px, py, 600, 600, null);
    }
}
