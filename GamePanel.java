import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Player player;
    Camera camera;

    Double cameraX, cameraY, playerX, playerY;

     Image mapImage = new ImageIcon("assets/map.png").getImage();


    public GamePanel(Player player, Camera camera){
        setFocusable(true);

        this.player = player;
        this.camera = camera;
        playerX = player.getX();
        playerY = player.getY();
        cameraX = playerX;
        cameraY = playerY;
        
        Timer timer = new Timer(16, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    private void update(){
        playerX = player.getX();
        playerY = player.getY();
        camera.focusPlayer();
        cameraX = camera.getX();
        cameraY = camera.getY();
    }

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // tegn kart
        g.drawImage(mapImage,
                (int)(-cameraX),
                (int)(-cameraY),
                (int)(mapImage.getWidth(null)),
                (int)(mapImage.getHeight(null)),
                null
        );

        // tegn player
        int px = (int)(playerX - cameraX);
        int py = (int)(playerY - cameraY);

        g.setColor(Color.RED);
        g.fillOval(px, py, 20, 20);
    }
}
