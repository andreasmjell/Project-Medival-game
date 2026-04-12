import java.awt.Rectangle;
import java.util.ArrayList;

public class CollisionManager{


    public void checkCollision(Player player, ArrayList<GameObject> gameObjects){
        for (GameObject obj: gameObjects){
            if (player.getBounds().intersects(obj.getBounds())){
                obj.onCollision(player);
            }
        }
    }
}