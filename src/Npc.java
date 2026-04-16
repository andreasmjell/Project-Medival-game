package src;
import java.awt.*;

import javax.swing.ImageIcon;

public class Npc extends GameObject implements Drawable{
    String name;
    double x;
    double y;
    double speed;
    int troops;
    Player player;
    MapController mapController;
    Image npcImage = new ImageIcon(getClass().getResource("assets/npc.png")).getImage();

    Path path;

    public Npc(String name, double x, double y, int troops, Player player, MapController mapController){
        super((int)x, (int)y, 80, 80);
        this.name = name;
        this.x = x;
        this.y = y;
        this.troops = troops;
        this.player = player;
        this.mapController = mapController;
        this.speed = 1 - ((double)this.troops*0.001);
    }
    public String getName(){
        return name;
    }

    public Drawable getThis(){
        return this;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void draw(Graphics g, double cameraX, double cameraY){
        int sx = (int)(x - cameraX - 50); //Npc X
        int sy = (int)(y - cameraY - 50); //Npc Y
        
        g.drawImage(npcImage, sx, sy, 80, 80, null);
    }
    public int getTroops(){
        return troops;
    }
    public void update(){
        newPath(player);
        updatePos();
    }

    private void updateBounds(){
        Rectangle bounds = super.getBounds();
        bounds.setLocation((int)x, (int)y);
    }
    public boolean chase(Player player){
        double diffX = this.x - player.getX();
        double diffY = this.y - player.getY();
        double distance = Math.sqrt(diffX * diffX + diffY * diffY);
        if(distance < 200){
            return true;
        }
        return false;
    }
    public void newPath(Player player){
        if (chase(player)){
            this.newRoute(player.getX(), player.getY());
        }
        else {
            if (path == null || path.isDone()){
                double newX = Math.random() * 1000 - 500;
                double newY = Math.random() * 1000 - 500;
                this.newRoute((int)(x + newX), (int)(y + newY));
            }
        }
    }
    public void setPath(Path newPath) {
        this.path = newPath;
        //System.out.println(path);
    }
    public void updatePos(){
        if (path == null || path.isDone())return;
        
        Point target = path.getCurrent();

        double dx = target.x - x;
        double dy = target.y - y;
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist <= 5) {
            //x = target.x;
            //y = target.y;
                
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

    public void onCollision(Player player){
        System.out.println("Spiller traff" + name);

        mapController.npcFight(this.troops, this);
    }


    public void newRoute(double x, double y){
        mapController.newNpcPath((int)x, (int)y, this);
    }
}
