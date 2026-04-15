package src;
import java.awt.Rectangle;

public abstract class GameObject{

    private Rectangle bounds;
    static int gameObjectId = 0;
    int id;

    public GameObject(int x, int y, int width, int height){
        bounds = new Rectangle (x, y, width, height);
        id = gameObjectId;
        gameObjectId++;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public int getId(){
        return id;
    } 

    public abstract void onCollision(Player player);


}