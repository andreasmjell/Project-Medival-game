import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapController {
    Player player = new Player(2000, 1500, 1);
    Ui ui = new Ui(this);
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    GamePanel gamePanel;
    Camera camera = new Camera(500, 500, (int)size.getWidth(), (int)size.getHeight(), player);
    InputManager inputManager = new InputManager(ui, this, camera, player);
    Pathfinder pathfinder = new Pathfinder();

    //Første oppstart, åpner main menu.
    public void start(){

        ui.start();
    }

    //Starter det faktiske spillet
    public void startGame(){

        gamePanel = ui.drawMap(player, camera, this);
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


    public void keyPressed(int keycode, boolean pressed){
        inputManager.keyPressed(keycode, pressed);
    }
}
