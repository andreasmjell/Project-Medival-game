import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapController {
    Player player = new Player(2000, 1500, 69);
    Save save = new Save();
    Ui ui = new Ui(this);
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    GamePanel gamePanel;
    Camera camera = new Camera(500, 500, (int)size.getWidth(), (int)size.getHeight(), player);
    InputManager inputManager = new InputManager(ui, this, camera, player);
    Pathfinder pathfinder = new Pathfinder();
    CollisionManager collisionManager= new CollisionManager();
    ArrayList<Settlement> settlement;
    ArrayList<Npc> npc;

    ArrayList<GameObject> gameObjects = new ArrayList<>();
    ArrayList<Settlement> settlements = new ArrayList<>();
    ArrayList<Npc> npcs = new ArrayList<>();


    //Første oppstart, åpner main menu.
    public void start(){

        ui.start();
    }

    //Starter det faktiske spillet
    public void startGame(){
        settlement = save.getSettlement("NewGameFile.json");
        npc = save.getNpc("NewGameFile.json", this, player);
        gamePanel = ui.drawMap(player, camera, this);

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
            for (Npc z : npc){
                            ArrayList<Point> points = pathfinder.findPath(z.x, z.y, x, y);    
            z.setPath(new Path(points));
            }
        }).start();
    }

    public void keyPressed(int keycode, boolean pressed){
        inputManager.keyPressed(keycode, pressed);
    }
}
