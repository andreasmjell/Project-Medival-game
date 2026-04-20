package src;
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


public class Main {
    public static void main(String[] args) {

    GameContext gameContext = new GameContext();
    MapController mapController = new MapController(gameContext);
    Player player = new Player(gameContext, 2000, 1500, 80000);
    Save save = new Save(gameContext);
    Ui ui = new Ui(gameContext);
    UiHandler uiHandler = new UiHandler(gameContext);
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    HudPanel hud = uiHandler.getHud(); // TEMP
    Camera camera = new Camera(gameContext, 500, 500, (int)size.getWidth(), (int)size.getHeight());
    GamePanel gamePanel = new GamePanel(gameContext, player, camera, mapController);
    InputManager inputManager = new InputManager(gameContext);
    Pathfinder pathfinder = new Pathfinder();
    CollisionManager collisionManager= new CollisionManager();
    MapPixelReader mapPixelReader = new MapPixelReader(mapController);
    AudioManager audioManager = new AudioManager();
    NpcManager npcManager = new NpcManager(gameContext);


    //Alle klasser som opprettes må opprettes her og sendes til setReferences!!
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
        npcManager
    );

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
