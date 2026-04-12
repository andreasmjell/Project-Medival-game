import java.awt.Rectangle;
import java.util.HashSet;

public class Player {
    //stats
    int troops;
    int speed = 3;

    //Position
    double x;
    double y;
    Rectangle bounds;
    HashSet<Integer> insideObjects = new HashSet<>();
    

    // Movement
    Path path;
    boolean addToPath = false;

    
    
    public Player(int x, int y, int troops){
        this.x = x;
        this.y = y;
        this.troops = troops;
        bounds = new Rectangle((int)x, (int)y, 80, 80);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public Rectangle getBounds(){
        return bounds;
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
    }

    public boolean addToPath(){
        return addToPath;
    }
}
