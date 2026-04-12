import java.awt.Rectangle;

public abstract class GameObject{

    private Rectangle bounds;

    public GameObject(int x, int y, int width, int height){
        bounds = new Rectangle (x, y, width, height);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public abstract void onCollision(Player player);


}