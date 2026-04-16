package src;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class MapController {
    Player player = new Player(2000, 1500, 80000);
    Save save = new Save();
    Ui ui = new Ui(this);
    UiHandler uiHandler = new UiHandler(ui, this);
    HudPanel hud;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    GamePanel gamePanel;
    Camera camera = new Camera(500, 500, (int)size.getWidth(), (int)size.getHeight(), player);
    InputManager inputManager = new InputManager(uiHandler, this, camera, player);
    Pathfinder pathfinder = new Pathfinder();
    CollisionManager collisionManager= new CollisionManager();
    ArrayList<Settlement> settlement = save.getSettlement("NewGameFile.json", this);
    ArrayList<Npc> npc = save.getNpc("NewGameFile.json", this, player);

    HashSet<Npc> deleteNpc = new HashSet<>();
    HashSet<Npc> respawnNpc = new HashSet<>();


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
        uiHandler.showHud();
        hud = uiHandler.getHud();
        createGameObject();

        Timer timer = new Timer(16, e -> {
            update();
        });
        timer.start();
    }

    public void update(){
        inputManager.update();
        player.updatePos();
        gamePanel.update();
        gamePanel.repaint();
        collisionManager.checkCollision(player, gameObjects);
        npcUpdate();
        hud.updatePlayerTroops(player.getTroops());
    }

    public void npcUpdate(){
        npc.removeAll(deleteNpc);
        gameObjects.removeAll(deleteNpc);
        deleteNpc.clear();
        for (Npc x : npc){
            x.update();
        }
        npc.addAll(respawnNpc);
        gameObjects.addAll(respawnNpc);
        respawnNpc.clear();
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
    public void npcDefeated(Npc npc){
        String name = npc.getName();
        double defeatedX = npc.getX();
        double defeatedY = npc.getY();
        int defeatedTroops = npc.getTroops();
        double respawnX = defeatedX + (Math.random() * 300 -300);
        double respawnY = defeatedY + (Math.random() * 300 -300);
        Npc respawn = new Npc(name, respawnX, respawnY, defeatedTroops, player, this);
        this.deleteNpc.add(npc);
        this.respawnNpc.add(respawn);
    }
    public void npcFight(int troops, Npc npc){
        if (player.getTroops() > troops){
            player.updateTroops(troops*-1);
            npcDefeated(npc);
        }
        System.out.println(player.getTroops());
    }
    public Player getPlayer(){
        return player;
    }
}
