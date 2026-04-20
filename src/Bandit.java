package src;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bandit extends Npc{
    public static ArrayList<Npc> enemyList = new ArrayList<>();
    public boolean playerEnemy = false;

    public Bandit(GameContext gameContext, String faction, String name, double x, double y, int troops){
        super(gameContext, faction, name, x, y, troops);
    }

    public void addEnemy(ArrayList<Npc> npcList, String faction){
        for (Npc x : npcList){
            if (x.getFaction().equals(faction)){
                enemyList.add(x);
            }
        }
    }
    public void removeEnemy(String faction){
        for (Npc x : npcList){
            if (x.getFaction().equals(faction)){
                enemyList.remove(x);
            }
        }
    }
    public void addEnemyPlayer(){
        playerEnemy = true;
    }
    public void removeEnemyPlayer(){
        playerEnemy = false;

    }
    public ArrayList<Npc> getEnemyList(){
        return enemyList;
    }
}
