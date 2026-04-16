package src;
import java.awt.Image;

public class TreeObject{
    Image sprite;
    double x;
    double y;

    public TreeObject(double x, double y, Image sprite){
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
}
