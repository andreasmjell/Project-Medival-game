package src;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Npc extends GameObject implements Drawable{
    GameContext gameContext;
    String faction;
    String name;
    double x;
    double y;
    double speed;
    int troops;
    Image npcImage = new ImageIcon(getClass().getResource("assets/npc.png")).getImage();
    Path path;

    public Npc(GameContext gameContext, String faction, String name, double x, double y, int troops){
        super((int)x, (int)y, 80, 80);
        this.faction = faction;
        this.name = name;
        this.x = x;
        this.y = y;
        this.troops = troops;
        this.speed = 1 - ((double)this.troops*0.001);
    }

    public String getName(){return name;}
    public String getFaction(){return faction;}
    public Drawable getThis(){return this;}
    public double getX(){return x;}
    public double getY(){return y;}
    public int getTroops(){return troops;}

    public void draw(Graphics g, double cameraX, double cameraY){
        int sx = (int)(x - cameraX - 50); //Npc X
        int sy = (int)(y - cameraY - 50); //Npc Y
        
        g.drawImage(npcImage, sx, sy, 80, 80, null);
    }

    public void update(){
        newPath(player);
        updatePos();
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
    private void updateBounds(){
        Rectangle bounds = super.getBounds();
        bounds.setLocation((int)x, (int)y);
    }

    public void setPath(Path newPath) {
        this.path = newPath;
        //System.out.println(path);
    }

    public void onCollision(Player player){
        System.out.println("Spiller traff" + name);

        mapController.npcFight(this.troops, this);
    }


    public void newRoute(double x, double y){
        mapController.newNpcPath((int)x, (int)y, this);
    }
}
