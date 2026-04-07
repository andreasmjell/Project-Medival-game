import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    Player player;
    Camera camera;
    MapController mapController;

    Double cameraX, cameraY, playerX, playerY;

    //Map and Player icon
     Image mapImage = new ImageIcon("assets/map.png").getImage();
     Image playerImage = new ImageIcon("assets/ridderhjelm.png").getImage();
     Dimension size = Toolkit.getDefaultToolkit().getScreenSize();


    public GamePanel(Player player, Camera camera, MapController mapController){
        setFocusable(true);
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if (SwingUtilities.isRightMouseButton(e)){
                    camera.switchFocusPlayer();
                }
                else{
                System.out.println("MusBleTrykketPå X: " + e.getX() + "Y: " + e.getY());
                mapController.newPlayerPath(e.getX() - (int)size.getWidth() / 2, e.getY() - (int)size.getHeight() / 2);
                }
            }
        });

        this.player = player;
        this.camera = camera;
        this.mapController = mapController;

        playerX = player.getX();
        playerY = player.getY();
        cameraX = playerX;
        cameraY = playerY;
        
    }

    //oppdaterer kamera og player posisjon
    protected void update(){
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