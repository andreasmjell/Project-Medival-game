import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapController {
    Player player = new Player(2000, 1500, 69);
    Npc npc = new Npc(2100, 1400, 10, player, this); //TEMP!!!
    Ui ui = new Ui(this);
    UiHandler uiHandler = new UiHandler(ui);

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    GamePanel gamePanel;
    Camera camera = new Camera(500, 500, (int)size.getWidth(), (int)size.getHeight(), player);
    InputManager inputManager = new InputManager(uiHandler, this, camera, player);
    Pathfinder pathfinder = new Pathfinder();
    CollisionManager collisionManager= new CollisionManager();

    ArrayList<GameObject> gameObjects = new ArrayList<>();
    ArrayList<Settlement> settlements = new ArrayList<>();
    ArrayList<Npc> npcs = new ArrayList<>();

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
        createSettlements();

        Timer timer = new Timer(16, e -> {
            update();
        });
        timer.start();
    }

    public void update(){
        inputManager.update();
        player.updatePos();
        npc.update();
        gamePanel.update();
        gamePanel.repaint();
        collisionManager.checkCollision(player, gameObjects);

    }


    private void createSettlements(){
        Settlement by1 = new Settlement("By 1", 1230, 1162, 10, 10);
        settlements.add(by1);
        gameObjects.add(by1);
        gameObjects.add(npc);
        npcs.add(npc); //TEMP!
    }


    public ArrayList<Settlement> getSettlements(){
        return settlements;
    }

    public ArrayList<Npc> getNpcs(){
        return npcs;
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

    public void newNpcPath(int x, int y){
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
}
