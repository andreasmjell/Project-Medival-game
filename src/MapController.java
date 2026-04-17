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
    MapPixelReader mapPixelReader = new MapPixelReader(this);
    AudioManager audioManager = new AudioManager();

    HashSet<Npc> deleteNpc = new HashSet<>();
    HashSet<Npc> respawnNpc = new HashSet<>();

    ArrayList<GameObject> gameObjects = new ArrayList<>();
    ArrayList<Drawable> drawable = new ArrayList<>();

    private Timer timer;


    public MapController(){

        ui.setUiHandler(uiHandler);
    }


    //Første oppstart, åpner main menu.
    public void start(){
        startMusic();

        uiHandler.start();
    }

    //Starter det faktiske spillet
    public void startGame(){
        mapPixelReader.loadBlockedMap();
        System.out.println("Spillet Starter!!!");
        gamePanel = new GamePanel(player, camera, this);
        uiHandler.setGamePanel(gamePanel);
        uiHandler.showHud();
        hud = uiHandler.getHud();
        createGameObject();

        timer = new Timer(16, e -> {
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

    public void timerStop(){
        timer.stop();
    }

    public void timerStart(){
        timer.start();
    }

    public void startMusic(){
        try{
        audioManager.play();
        }catch(Exception e){
            System.out.println("MUSIKK STARTER IKKE!!!");
            e.printStackTrace();
        }
    }

    public void npcUpdate(){
        npc.removeAll(deleteNpc);
        drawable.removeAll(deleteNpc);
        gameObjects.removeAll(deleteNpc);
        deleteNpc.clear();
        for (Npc x : npc){
            x.update();
        }
        npc.addAll(respawnNpc);
        gameObjects.addAll(respawnNpc);
        drawable.addAll(respawnNpc);
        respawnNpc.clear();
    }


    private void createGameObject(){
        gameObjects.addAll(settlement);
        gameObjects.addAll(npc);
        for (GameObject object : gameObjects){
            Drawable d = object.getThis();
            drawable.add(d);
        }
        drawable.add(player);
    }

    public ArrayList<Drawable> getDrawable(){
        return drawable;
    }

    public void addDrawable(Drawable object){
        drawable.add(object);
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
            ArrayList<Point> points = pathfinder.findPath(player.x, player.y, x, y, mapPixelReader);

            player.getPath().addPoints(points);
            }).start();
        }
        else{
            new Thread (() -> {
            ArrayList<Point> points = pathfinder.findPath(player.x, player.y, x, y, mapPixelReader);

            player.setPath(new Path(points));
            }).start();
        }
    }

    public void newNpcPath(int x, int y, Npc npc){
        new Thread (() -> {
            ArrayList<Point> points = pathfinder.findPath(npc.x, npc.y, x, y, mapPixelReader);    
            npc.setPath(new Path(points));
        }).start();
    }

    public void keyPressed(int keycode, boolean pressed){
        inputManager.keyPressed(keycode, pressed);
    }

    public UiHandler getUiHandler(){
        return uiHandler;
    }
    
    public void openSettlementMenu(Settlement settlement){
        uiHandler.openSettlementMenu(settlement);
    }
    public void openPauseMenu(){
        uiHandler.openPauseMenu();
    }
    public void npcDefeated(Npc npc){
        String name = npc.getName();
        double defeatedX = npc.getX();
        double defeatedY = npc.getY();
        int defeatedTroops = npc.getTroops();
        double respawnX = defeatedX + (Math.random() * 600 -300);
        double respawnY = defeatedY + (Math.random() * 600 -300);
        while (mapPixelReader.isBlocked((int)respawnX, (int)respawnY)){
            respawnX = defeatedX + (Math.random() * 600 -300);
            respawnY = defeatedY + (Math.random() * 600 -300);
        }
        Npc respawn = new Npc(name, respawnX, respawnY, defeatedTroops, player, this);
        this.deleteNpc.add(npc);
        this.respawnNpc.add(respawn);
    }
    public void npcFight(int troops, Npc npc){
        try{
        audioManager.startBattleSound();
        }catch(Exception e){System.out.println("MUSIKK STARTER IKKE!");}
        if (player.getTroops() > troops){
            player.updateTroops(troops*-1);
            npcDefeated(npc);
            try {
            audioManager.enemyDefeated();
            } catch(Exception e){}
        }
        System.out.println(player.getTroops());
    }

    public void openBattle(Npc npc) {
    BattlePanel battlePanel = new BattlePanel(npc, player, this);
    BattleController battleController = new BattleController(this, npc, player, uiHandler);
    timer.stop();
    uiHandler.openBattlePanel(battlePanel);
    }

    public void closeBattle() {
        uiHandler.closeBattlePanel();
        timer.start();
    }

    public Player getPlayer(){
        return player;
    }

    public AudioManager getAudioManager(){
        return audioManager;
    }
}
