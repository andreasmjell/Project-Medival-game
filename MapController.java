import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.w3c.dom.events.MouseEvent;

public class MapController {
    Player player = new Player(2000, 1500, 1);
    Ui ui = new Ui(this);
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    GamePanel gamePanel;

    Camera camera = new Camera(500, 500, (int)size.getWidth(), (int)size.getHeight(), player);

    //Første oppstart, åpner main menu.
    public void start(){

        ui.start();
    }

    //Starter det faktiske spillet
    public void startGame(){

        gamePanel = ui.drawMap(player, camera, this);
        Timer timer = new Timer(16, e -> {
            gamePanel.update();
            gamePanel.repaint();
        });
        timer.start();
    }

    public void update(){
    }

    public void newPlayerPath(int x, int y){
        player.x = x;
        player.y = y;
    }




    //Skrot under her
   public class SmoothMoveCharacter extends JPanel {
    private int charX = 50; // startposisjon
    private int charY = 50;
    private final int CHAR_SIZE = 20;

    private int targetX = charX;
    private int targetY = charY;
    private final int SPEED = 5; // hvor mange piksler karakteren beveger seg per steg

    private Image mapImage = new ImageIcon("map.png").getImage();
    private Image charImage = new ImageIcon("ridderhjelm.png").getImage();

    /*
    public SmoothMoveCharacter() {
        // Lytter til museklikk
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                targetX = e.getX() - CHAR_SIZE / 2;
                targetY = e.getY() - CHAR_SIZE / 2;
            }
        });
        */

        // Timer som oppdaterer posisjon hvert 20 ms (~50 FPS)
        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //moveCharacter();
                repaint();
            }
        });{
        
        
            timer.start();
        //this.setPreferredSize(new Dimension(1920, 1080));
        this.setPreferredSize(new Dimension(mapImage.getWidth(null), mapImage.getHeight(null)));
    }

}
}
