package src;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapController {
    Player player = new Player(2000, 1500, 69);
    Save save = new Save();
    Ui ui = new Ui(this);
    UiHandler uiHandler = new UiHandler(ui);

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    GamePanel gamePanel;
    Camera camera = new Camera(500, 500, (int)size.getWidth(), (int)size.getHeight(), player);
    InputManager inputManager = new InputManager(uiHandler, this, camera, player);
    Pathfinder pathfinder = new Pathfinder();
    CollisionManager collisionManager= new CollisionManager();
    ArrayList<Settlement> settlement = save.getSettlement("NewGameFile.json", this);
    ArrayList<Npc> npc = save.getNpc("NewGameFile.json", this, player);

    ArrayList<GameObject> gameObjects = new ArrayList<>();


    public MapController(){
        ui.setUiHandler(uiHandler);
    }


    //Første oppstart, åpner main menu.
    public void start(){

        uiHandler.start();
    }

    //Starter det faktiske spillet
    public void startGame(){
        System.out.println("Spillet Starter!!!");
        gamePanel = new GamePanel(player, camera, this);
        uiHandler.setGamePanel(gamePanel);
        createGameObject();

        Timer timer = new Timer(16, e -> {
            update();
        });
        timer.start();
    }

    public void update(){
        inputManager.update();
        player.updatePos();
        for (Npc x : npc){
            x.update();
        }
        gamePanel.update();
        gamePanel.repaint();
        collisionManager.checkCollision(player, gameObjects);

    }


    private void createGameObject(){
        gameObjects.addAll(settlement);
        gameObjects.addAll(npc);
    }


    public ArrayList<Settlement> getSettlement(){
        return settlement;
    }


    public ArrayList<Npc> getNpcs(){
        return npc;
    }

    public void newPlayerPath(int x, int y){
        if(player.addToPath()){
            new Thread (() -> {
            ArrayList<Point> points = pathfinder.findPath(player.x, player.y, x, y);

            player.getPath().addPoints(points);
            }).start();
        }
        else{
            new Thread (() -> {
            ArrayList<Point> points = pathfinder.findPath(player.x, player.y, x, y);

            player.setPath(new Path(points));
            }).start();
        }
    }

    public void newNpcPath(int x, int y, Npc npc){
        new Thread (() -> {
            ArrayList<Point> points = pathfinder.findPath(npc.x, npc.y, x, y);    
            npc.setPath(new Path(points));
        }).start();
    }

    public void keyPressed(int keycode, boolean pressed){
        inputManager.keyPressed(keycode, pressed);
    }

    public UiHandler getUiHandler(){
        return uiHandler;
    }

    public Player getPlayer(){
        return player;
    }
}
