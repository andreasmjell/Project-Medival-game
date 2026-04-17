package src;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JPanel;

public class BattlePanel extends JPanel{
    Npc npc;
    Player player;
    MapControll mapController;

    public BattlePanel(Npc npc, Player player, this){
        this.npc = npc;
        this.player = player;
        this.mapController = mapController;
    }

    public update(){
        
    }







            @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        //Bilde av kartet som vises
        g.drawImage(BattleMapImage,
                (int)(-cameraX),
                (int)(-cameraY),
                (int)(mapImage.getWidth(null)),
                (int)(mapImage.getHeight(null)),
                null
        );
    }
}
}
