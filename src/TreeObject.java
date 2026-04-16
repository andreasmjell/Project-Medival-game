package src;
import java.awt.*;

import javax.swing.ImageIcon;

public class TreeObject implements Drawable{
    Image sprite;
    double x;
    double y;
    Image treeImage = new ImageIcon(getClass().getResource("assets/tree1.png")).getImage();

    public TreeObject(double x, double y){
        this.x = x;
        this.y = y;
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

        g.drawImage(treeImage, px, py, 800, 800, null);
    }
}
