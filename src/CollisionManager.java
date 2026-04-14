package src;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;

public class CollisionManager{


    public void checkCollision(Player player, ArrayList<GameObject> gameObjects){

        HashSet<Integer> insideGameObjects = new HashSet<>();

        for (GameObject obj: gameObjects){
            if (player.getBounds().intersects(obj.getBounds())){
                insideGameObjects.add(obj.getId());

                if(!player.insideObjects.contains(obj.getId())){
                    obj.onCollision(player);
                    System.out.println("player går inn for første gang");
                }
            }
        }
        player.insideObjects = insideGameObjects;
    }
}