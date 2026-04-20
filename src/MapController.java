package src;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class MapController {
    GameContext gameContext;

    ArrayList<GameObject> gameObjects = new ArrayList<>();
    ArrayList<Drawable> drawable = new ArrayList<>();

    ArrayList<Settlement> settlementList = new ArrayList<>();
    ArrayList<Npc> npcList = new ArrayList<>();

    HashSet<Npc> deleteNpc = new HashSet<>();
    HashSet<Npc> respawnNpc = new HashSet<>();
    private Timer timer;


    public MapController(GameContext gameContext){
        this.gameContext = gameContext;
        
        gameContext.ui.setUiHandler(gameContext.uiHandler);
    }


    //Første oppstart, åpner main menu.
    public void start(){
        startMusic();

        gameContext.uiHandler.start();
    }

    //Starter det faktiske spillet
    public void startGame(){

        settlementList = gameContext.save.readSettlement("NewGameFile.json", this);
        npcList = gameContext.save.readNpc("NewGameFile.json", this, gameContext.player);
        gameContext.mapPixelReader.loadBlockedMap();


        System.out.println("Spillet Starter!!!");
        gameContext.gamePanel = new GamePanel(gameContext.player, gameContext.camera, this); // FJERNES! OPPRETTES I MAIN
        gameContext.uiHandler.setGamePanel(gameContext.gamePanel);
        gameContext.uiHandler.showHud();
        gameContext.hud = gameContext.uiHandler.getHud();
        createGameObject();

        timer = new Timer(16, e -> {
            update();
        });
        timer.start();
    }

    public void update(){
        gameContext.inputManager.update();
        gameContext.player.updatePos();
        gameContext.gamePanel.update();
        gameContext.gamePanel.repaint();
        gameContext.collisionManager.checkCollision(gameContext.player, gameObjects);
        gameContext.npcManager.npcUpdate();
        gameContext.hud.updatePlayerTroops(gameContext.player.getTroops());
    }

    public void timerStop(){
        timer.stop();
    }

    public void timerStart(){
        timer.start();
    }

    public void startMusic(){
        try{
        gameContext.audioManager.play();
        }catch(Exception e){
            System.out.println("MUSIKK STARTER IKKE!!!");
            e.printStackTrace();
        }
    }

    private void createGameObject(){
        gameObjects.addAll(settlementList);
        gameObjects.addAll(npcList);
        for (GameObject object : gameObjects){
            Drawable d = object.getThis();
            drawable.add(d);
        }
        drawable.add(gameContext.player);
    }

    public ArrayList<Drawable> getDrawable(){
        return drawable;
    }

    public void addDrawable(Drawable object){
        drawable.add(object);
    }

    public void newPlayerPath(int x, int y){
        if(gameContext.player.addToPath()){
            new Thread (() -> {
            ArrayList<Point> points = gameContext.pathfinder.findPath(gameContext.player.x, gameContext.player.y, x, y, gameContext.mapPixelReader);

            gameContext.player.getPath().addPoints(points);
            }).start();
        }
        else{
            new Thread (() -> {
            ArrayList<Point> points = gameContext.pathfinder.findPath(gameContext.player.x, gameContext.player.y, x, y, gameContext.mapPixelReader);

            gameContext.player.setPath(new Path(points));
            }).start();
        }
    }

    public void keyPressed(int keycode, boolean pressed){
        gameContext.inputManager.keyPressed(keycode, pressed);
    }

    public UiHandler getUiHandler(){
        return gameContext.uiHandler;
    }
    
    public void openSettlementMenu(Settlement settlement){
        gameContext.uiHandler.openSettlementMenu(settlement);
    }
    public void openPauseMenu(){
        gameContext.uiHandler.openPauseMenu();
    }
    public void npcDefeated(Npc npc){
        String faction = npc.getFaction();
        String name = npc.getName();
        double defeatedX = npc.getX();
        double defeatedY = npc.getY();
        int defeatedTroops = npc.getTroops();
        double respawnX = defeatedX + (Math.random() * 600 -300);
        double respawnY = defeatedY + (Math.random() * 600 -300);
        while (gameContext.mapPixelReader.isBlocked((int)respawnX, (int)respawnY)){
            respawnX = defeatedX + (Math.random() * 600 -300);
            respawnY = defeatedY + (Math.random() * 600 -300);
        }
        Npc respawn = new Bandit(name, respawnX, respawnY, defeatedTroops, gameContext.player, this, faction);
        this.deleteNpc.add(npc);
        this.respawnNpc.add(respawn);
    }
    public void npcFight(int troops, Npc npc){
        try{
        gameContext.audioManager.startBattleSound();
        }catch(Exception e){System.out.println("MUSIKK STARTER IKKE!");}
        if (gameContext.player.getTroops() > troops){
            gameContext.player.updateTroops(troops*-1);
            npcDefeated(npc);
            try {
            gameContext.audioManager.enemyDefeated();
            } catch(Exception e){}
        }
        System.out.println(gameContext.player.getTroops());
    }

    public void openBattle(Npc npc) {
    BattleController battleController = new BattleController(this, npc, gameContext.player, gameContext.uiHandler);
    timer.stop();
    battleController.start();
    }

    public void closeBattle(BattlePlayer battlePlayer) {
        gameContext.uiHandler.closeBattlePanel();
        gameContext.player.setTroops(battlePlayer.getTroops());
        timer.start();
    }

    public Player getPlayer(){
        return gameContext.player;
    }

    public AudioManager getAudioManager(){
        return gameContext.audioManager;
    }
}
