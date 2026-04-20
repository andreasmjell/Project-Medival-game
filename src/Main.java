package src;
import javax.swing.*;
import java.awt.*;
/* for å starte spillet med JSON:

WINDOWS
javac -cp ".;lib/json-20251224.jar" *.java
java -cp ".;lib/json-20251224.jar" .\Main.java
NY:
javac -cp ".;lib/json-20251224.jar" src/*.java src/menu/*.java
java -cp ".;lib/json-20251224.jar" src.Main

MAC
javac -cp ".:lib/json-20251224.jar" *.java
java -cp ".:lib/json-20251224.jar" Main
NY:
java -cp ".:lib/json-20251224.jar" src.Main
javac -cp ".:lib/json-20251224.jar" src/*.java src/menu/*.java


*/

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    private GameContext gameContext;

    public static void main(String[] args) {

    GameContext gameContext = new GameContext();
    MapController mapController = new MapController(gameContext);
    Player player = new Player(gameContext, 2000, 1500, 80000);
    Save save = new Save(gameContext);
    Ui ui = new Ui(gameContext);
    UiHandler uiHandler = new UiHandler(gameContext);
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    HudPanel hud = uiHandler.getHud(); // TEMP
    Camera camera = new Camera(500, 500, (int)size.getWidth(), (int)size.getHeight(), player);
    GamePanel gamePanel = new GamePanel(player, camera, mapController);
    InputManager inputManager = new InputManager(uiHandler, mapController, camera, player);
    Pathfinder pathfinder = new Pathfinder();
    CollisionManager collisionManager= new CollisionManager();
    MapPixelReader mapPixelReader = new MapPixelReader(mapController);
    AudioManager audioManager = new AudioManager();
    gameContext.setReferences(
        mapController,
        player,
        save,
        ui,
        uiHandler,
        hud,
        size,
        gamePanel,
        camera,
        inputManager,
        pathfinder,
        collisionManager,
        mapPixelReader,
        audioManager,
        npc,
        settlement
    ); //Alle klasser som opprettes må opprettes her og sendes til setReferences!!

    gameContext.mapController.start();
    }
}
/*
    HashSet<Npc> deleteNpc = new HashSet<>();
    HashSet<Npc> respawnNpc = new HashSet<>();

    ArrayList<GameObject> gameObjects = new ArrayList<>();
    ArrayList<Drawable> drawable = new ArrayList<>();
    ArrayList<Settlement> settlement = save.getSettlement("NewGameFile.json", this);
    ArrayList<Npc> npc = save.getNpc("NewGameFile.json", this, player);
    */
