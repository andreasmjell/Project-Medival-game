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
     Image playerImage = new ImageIcon("assets/playericon.png").getImage();
     Image settlementImage = new ImageIcon("assets/by.png").getImage();
     Image npcImage = new ImageIcon("assets/npc.png").getImage();
     Dimension size = Toolkit.getDefaultToolkit().getScreenSize();


    public GamePanel(Player player, Camera camera, MapController mapController){

        setFocusable(true);


        this.addMouseListener(new MouseAdapter(){ //Mouse LISTNER
            @Override
            public void mousePressed(MouseEvent e){
                if (SwingUtilities.isRightMouseButton(e)){ //Midlertidig for å teste. Løsner kameraet fra spiller
                    camera.switchFocusPlayer();
                }
                else{
                System.out.println("MusBleTrykketPå X: " + e.getX() + "Y: " + e.getY());
                //mapController.newPlayerPath(e.getX() - (int)size.getWidth() / 2, e.getY() - (int)size.getHeight() / 2); Funker bare om spiller er i senter
                mapController.newPlayerPath((int)camera.getX() + e.getX() ,(int)camera.getY() + e.getY()); //Funker unasett om kameraet er løst
                }
            }

        });
        this.addKeyListener(new KeyAdapter(){ // Keyboard LISTNER
            @Override
            public void keyPressed(KeyEvent e){
                Integer keycode = e.getKeyCode();
                System.out.println(KeyEvent.getKeyText(keycode) + "KeyCode: " + keycode);
                mapController.keyPressed(keycode, true);
            }
            @Override
            public void keyReleased(KeyEvent e){
                Integer keycode = e.getKeyCode();
                System.out.println(KeyEvent.getKeyText(keycode) + "KeyCode: " + keycode);
                mapController.keyPressed(keycode, false);
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


        int px = (int)(playerX - cameraX - 40); // Player X
        int py = (int)(playerY - cameraY -40); // Player Y
        
        for (Settlement s: mapController.getSettlements()){
            int sx = (int)(s.getX() - cameraX - 50); //Settlement X
            int sy = (int)(s.getY() - cameraY - 50); //Settlement Y

            g.drawImage(settlementImage, sx, sy, 100, 100, null);
        }

        for (Npc s: mapController.getNpcs()){
            int sx = (int)(s.getX() - cameraX - 50); //Npc X
            int sy = (int)(s.getY() - cameraY - 50); //Npc Y

            g.drawImage(npcImage, sx, sy, 100, 100, null);
        }

        //Bilde av player icon
        g.drawImage(playerImage, px, py, 80, 80, null);
    }
}