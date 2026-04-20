package src;
import java.awt.*;


public class PathTile implements Drawable{
    Image sprite;
    double x;
    double y;
    Image img;

    public PathTile(double x, double y, Image img){
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
        int px = (int)(x - cameraX - 2);
        int py = (int)(y - cameraY -2);

        g.drawImage(img, px, py, 5, 5, null);
    }
}
