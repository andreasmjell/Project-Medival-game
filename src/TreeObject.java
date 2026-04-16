package src;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TreeObject implements Drawable{
    Image sprite;
    double x;
    double y;
    Image treeImage = new ImageIcon(getClass().getResource("assets/tree1.png")).getImage();

    public TreeObject(double x, double y, Image sprite){
        this.x = x;
        this.y = y;
    }
    public Image randomImage(){
        Image playerImage = new ImageIcon(getClass().getResource("assets/playericon.png")).getImage();
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
        int px = (int)(x - cameraX - 40); // Player X
        int py = (int)(y - cameraY -40);

        g.drawImage(treeImage, px, py, 80, 80, null);
    }
}
