package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class NpcManager {
    GameContext gameContext;
    HashSet<Npc> deleteNpc = new HashSet<>();
    HashSet<Npc> respawnNpc = new HashSet<>();
    private Timer npcTimer;

    public NpcManager(GameContext gameContext){
        this.gameContext = gameContext;

    }
    //npcUpdate blir kjørt hver tick av mapController
    public void npcUpdate(){
        gameContext.mapController.npcList.removeAll(deleteNpc);
        gameContext.mapController.drawable.removeAll(deleteNpc);
        gameContext.mapController.gameObjects.removeAll(deleteNpc);
        deleteNpc.clear();
        for (Npc x : gameContext.mapController.npcList){
            x.update();
        }
        gameContext.mapController.npcList.addAll(respawnNpc);
        gameContext.mapController.gameObjects.addAll(respawnNpc);
        gameContext.mapController.drawable.addAll(respawnNpc);
        respawnNpc.clear();
    }
    public void newNpcPath(int x, int y, Npc npc){
        new Thread (() -> {
            ArrayList<Point> points = gameContext.pathfinder.findPath(npc.x, npc.y, x, y, gameContext.mapPixelReader);    //MÅ FIKSES
            npc.setPath(new Path(points));
        }).start();
    }
    public void npcTimerStart(){
        npcTimer.start();
    }
    public void npcTimerStop(){
        npcTimer.stop();
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
        Npc respawn = new Bandit(gameContext, faction, name, respawnX, respawnY, defeatedTroops);
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
    public void npcFightNpc(Npc npc, Npc target){
        try{
        gameContext.audioManager.startBattleSound();
        }catch(Exception e){System.out.println("MUSIKK STARTER IKKE!");}
        if (npc.getTroops() > target.getTroops()){
            npc.updateTroops(target.getTroops()*-1);
            npcDefeated(target);
        }
        else if (target.getTroops() > npc.getTroops()){
            target.updateTroops(npc.getTroops()*-1);
            npcDefeated(npc);
        }
        else{
            npcDefeated(npc);
            npcDefeated(target);
        }
            try {
            gameContext.audioManager.enemyDefeated();
            } catch(Exception e){}
        System.out.println(gameContext.player.getTroops());
    }
    public void npcTimer(){
        npcTimer = new Timer(64, e -> {

            npcManagerUpdate();
        });
    }
    public void npcManagerUpdate(){
        for (Npc x : gameContext.mapController.npcList){
            checkCollisionNpc(x, gameContext.mapController.npcList);
        }
    }
}
