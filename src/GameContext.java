package src;
import javax.swing.*;
import java.awt.*;

public class GameContext {
    public MapController mapController;
    public Player player;
    public Save save;
    public Ui ui;
    public UiHandler uiHandler;
    public HudPanel hud; 
    public Dimension size;
    public GamePanel gamePanel;
    public Camera camera;
    public InputManager inputManager;
    public Pathfinder pathfinder;
    public CollisionManager collisionManager;
    public MapPixelReader mapPixelReader;
    public AudioManager audioManager;
    public Npc npc;
    public Settlement settlement;

    public void setReferences(
        MapController mapController,
        Player player,
        Save save,
        Ui ui,
        UiHandler uiHandler,
        HudPanel hud,
        Dimension size,
        GamePanel gamePanel,
        Camera camera,
        InputManager inputManager,
        Pathfinder pathfinder,
        CollisionManager collisionManager,
        MapPixelReader mapPixelReader,
        AudioManager audioManager,
        Npc npc,
        Settlement settlement
    ){
            this.mapController = mapController;
            this.player = player;
            this.save = save;
            this.ui = ui;
            this.uiHandler = uiHandler;
            this.hud = hud;
            this.size = size;
            this.gamePanel = gamePanel;
            this.camera = camera;
            this.inputManager = inputManager;
            this.pathfinder = pathfinder;
            this.collisionManager = collisionManager;
            this.mapPixelReader = mapPixelReader;
            this.audioManager = audioManager;
            this.npc =  npc;
            this.settlement = settlement;
    }
}
