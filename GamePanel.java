import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    Player player;
    Camera camera;

    Double cameraX, cameraY, playerX, playerY;

    //Map and Player icon
     Image mapImage = new ImageIcon("assets/map.png").getImage();
     Image playerImage = new ImageIcon("assets/ridderhjelm.png").getImage();


    public GamePanel(Player player, Camera camera){
        setFocusable(true);
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                System.out.println("MusBleTrykketPå X: " + e.getX() + "Y: " + e.getY());
                player.x = e.getX();
                player.y = e.getY();
            }
        });

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

    //oppdaterer kamera og player posisjon
    private void update(){
        playerX = player.getX();
        playerY = player.getY();
        camera.focusPlayer();
        cameraX = camera.getX(); 
        cameraY = camera.getY();
    }


    //Viser oppdatert bilde av map basert på kamera posisjon og player icon
        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        //Bilde av kartet som vises
        g.drawImage(mapImage,
                (int)(-cameraX),
                (int)(-cameraY),
                (int)(mapImage.getWidth(null)),
                (int)(mapImage.getHeight(null)),
                null
        );


        int px = (int)(playerX - cameraX);
        int py = (int)(playerY - cameraY);

        //Bilde av player icon
        g.drawImage(playerImage, px, py, 40, 40, null);
    }
}