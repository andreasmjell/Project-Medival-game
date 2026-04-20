package src;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bandit extends Npc{
    public static ArrayList<Npc> enemyList = new ArrayList<>();

    public Bandit(GameContext gameContext, String faction, String name, double x, double y, int troops){
        super(gameContext, faction, name, x, y, troops);
    }
}
