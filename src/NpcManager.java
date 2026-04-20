package src;

import java.util.ArrayList;

public class NpcManager {
    
        public void newNpcPath(int x, int y, Npc npc){
        new Thread (() -> {
            ArrayList<Point> points = gameContext.pathfinder.findPath(npc.x, npc.y, x, y, gameContext.mapPixelReader);    
            npc.setPath(new Path(points));
        }).start();
    }
    public void npcUpdate(){
        npc.removeAll(deleteNpc);
        drawable.removeAll(deleteNpc);
        gameObjects.removeAll(deleteNpc);
        deleteNpc.clear();
        for (Npc x : npc){
            x.update();
        }
        npc.addAll(respawnNpc);
        gameObjects.addAll(respawnNpc);
        drawable.addAll(respawnNpc);
        respawnNpc.clear();
    }
}
