package src;
import java.util.HashSet;

import javax.swing.ImageIcon;

import java.awt.*;

public class Player implements Drawable{
    //stats
    int troops;
    double speed;

    //Position
    double x;
    double y;
    Rectangle bounds;
    HashSet<Integer> insideObjects = new HashSet<>();
    

    // Movement
    Path path;
    boolean addToPath = false;

    // Image
    Image playerImage = new ImageIcon(getClass().getResource("assets/playericon.png")).getImage();
    
    
    public Player(int x, int y, int troops){
        this.x = x;
        this.y = y;
        this.troops = troops;
        this.speed = 3;
        bounds = new Rectangle((int)x, (int)y, 80, 80);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public Drawable getThis(){
        return this;
    }
    
    public void draw(Graphics g, double cameraX, double cameraY){
        int px = (int)(x - cameraX - 40); // Player X
        int py = (int)(y - cameraY -40);

        g.drawImage(playerImage, px, py, 50, 80, null);
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void updateTroops(int troops){
        this.troops += troops;
    }
    public int getTroops(){
        return troops;
    }

    private void updateBounds(){
        bounds.setLocation((int)x, (int)y);
    }

    public void updatePos(){
        if (path == null || path.isDone()) return;

        Point target = path.getCurrent();

        double dx = target.x - x;
        double dy = target.y - y;
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist <= 5) {
            x = target.x;
            y = target.y;
                
            path.advance();
                
            if (path.isDone()) {
                path = null;
            }
        
        } else {
            double dirX = dx / dist;
            double dirY = dy / dist;
        
            x += dirX * speed;
            y += dirY * speed;
        }

        updateBounds();
    }

    public void setPath(Path newPath) {
        this.path = newPath;
        System.out.println(path);
    }

    public Path getPath(){
        return path;
    }

    public void setAddToPath(){
        if (!addToPath){
            addToPath = true;
        }
        else{
            addToPath = false;
        }
        System.out.println(addToPath);
    }

    public boolean addToPath(){
        return addToPath;
    }
}
