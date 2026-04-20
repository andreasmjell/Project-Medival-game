package src;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel {
        GameContext gameContext;
        Player player;
        Camera camera;
        MapController mapController;

    Double cameraX, cameraY, playerX, playerY;

    //Map and Player icon
     Image mapImage = new ImageIcon(getClass().getResource("assets/map.png")).getImage();
     Dimension size = Toolkit.getDefaultToolkit().getScreenSize();


    public GamePanel(GameContext gameContext, Player player, Camera camera, MapController mapController){

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
        this.gameContext = gameContext;

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

    public boolean isVisible(Drawable obj, Camera camera){
    return obj.getX() >= camera.getX() &&
           obj.getX() <= camera.getX() + camera.width &&
           obj.getY() >= camera.getY() &&
           obj.getY() <= camera.getY() + camera.height;
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

        ArrayList<Drawable> drawable = mapController.getDrawable();

        drawable.sort(Comparator.comparingDouble(Drawable::getY));

        for (Drawable d : drawable) {
            if (isVisible(d, camera)){
                d.draw(g, cameraX, cameraY);
            }
        }
    }
}