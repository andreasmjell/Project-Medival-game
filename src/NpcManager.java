package src;

import java.util.ArrayList;

public class NpcManager {
    GameContext gameContext;

    public NpcManager(GameContext gameContext){
        this.gameContext = gameContext;
    }
    
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
    public void newPath(Player player){
        if (chase(player)){
            this.newRoute(player.getX(), player.getY());
            return;
        }
        Npc target = chaseOther();
        if (target != null){
            this.newRoute(target.getX(), target.getY());
            return;
        }
        else {
            if (path == null || path.isDone()){
                double newX = Math.random() * 1000 - 500;
                double newY = Math.random() * 1000 - 500;
                this.newRoute((int)(x + newX), (int)(y + newY));
            }
        }
    }

    public boolean chase(Player player){
        double diffX = this.x - player.getX();
        double diffY = this.y - player.getY();
        double distance = Math.sqrt(diffX * diffX + diffY * diffY);
        if(distance < 200){
            return true;
        }
        return false;
    }
    public Npc chaseOther(){
        for (Npc chase : enemyList){
            double diffX = this.x - chase.getX();
            double diffY = this.y - chase.getY();
            double distance = Math.sqrt(diffX * diffX + diffY * diffY);
            if(distance < 500){
                return chase;
            }
        }
        return null;
    }
    public void npcDefeated(Npc npc){
        String faction = npc.getFaction();
        String name = npc.getName();
        double defeatedX = npc.getX();
        double defeatedY = npc.getY();
        int defeatedTroops = npc.getTroops();
        double respawnX = defeatedX + (Math.random() * 600 -300);
        double respawnY = defeatedY + (Math.random() * 600 -300);
        while (gameContext.mapPixelReader.isBlocked((int)respawnX, (int)respawnY)){
            respawnX = defeatedX + (Math.random() * 600 -300);
            respawnY = defeatedY + (Math.random() * 600 -300);
        }
        Npc respawn = new Bandit(name, respawnX, respawnY, defeatedTroops, gameContext.player, this, faction);
        this.deleteNpc.add(npc);
        this.respawnNpc.add(respawn);
    }
    public void npcFight(int troops, Npc npc){
        try{
        gameContext.audioManager.startBattleSound();
        }catch(Exception e){System.out.println("MUSIKK STARTER IKKE!");}
        if (gameContext.player.getTroops() > troops){
            gameContext.player.updateTroops(troops*-1);
            npcDefeated(npc);
            try {
            gameContext.audioManager.enemyDefeated();
            } catch(Exception e){}
        }
        System.out.println(gameContext.player.getTroops());
    }
}
