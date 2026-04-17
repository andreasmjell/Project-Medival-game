package src;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
public class BattlePanel extends JPanel{
    Npc npc;
    Player player;
    MapController mapController;

    public BattlePanel(Npc npc, Player player, MapController mapController){
    }

    public void update(){

    }


            @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        //Bilde av kartet som vises
        /*
        g.drawImage(BattleMapImage,
                (int)(-cameraX),
                (int)(-cameraY),
                (int)(mapImage.getWidth(null)),
                (int)(mapImage.getHeight(null)),
                null
        );*/
    }
}

