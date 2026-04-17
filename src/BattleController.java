package src;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;


public class BattleController {
    private MapController mapController;
    private Npc npc;
    private Player player;
    private BattlePlayer battlePlayer;
    private UiHandler uiHandler;
    private BattlePanel battlePanel;
    private Camera battleCamera;
    private Timer timer;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    


    public BattleController(MapController mapController, Npc npc, Player player, UiHandler uiHandler){
        this.mapController = mapController;
        this.npc = npc;
        this.player = player;
        this.uiHandler = uiHandler;

        battlePlayer = new BattlePlayer((int)player.getX(), (int)player.getY(), player.getTroops());
        battleCamera = new Camera((int)battlePlayer.getX(), (int)battlePlayer.getY(), (int)size.getWidth(), (int)size.getHeight(), battlePlayer);
    }

    public void start(){
        BattlePanel battlePanel = new BattlePanel(npc, battlePlayer, mapController ,this);
        uiHandler.openBattlePanel(battlePanel);


        timer = new Timer(12, e -> {

            update();
        });
    }

    public void update(){
        System.out.println("UPDATE!");
    }

    public void stopBattle(){
        mapController.closeBattle(battlePlayer);
    }
}
