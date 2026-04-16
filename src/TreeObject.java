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
        int px = (int)(x - cameraX - 400);
        int py = (int)(y - cameraY -400);

<<<<<<< HEAD
        g.drawImage(img, px, py, 80, 80, null);
=======
        g.drawImage(treeImage, px, py, 800, 800, null);
>>>>>>> 843c635f98c1062fc344cc0fe155ddddf7fc008f
    }
}
