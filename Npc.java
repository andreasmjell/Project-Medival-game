import java.awt.Rectangle;

public class Npc extends GameObject{
    String name = "Enemy Army";
    double x;
    double y;
    int speed = 2;
    int troops;
    Player player;
    MapController mapController;

    Path path;

    public Npc(double x, double y, int troops, Player player, MapController mapController){
        super((int)x, (int)y, 80, 80);
        this.x = x;
        this.y = y;
        this.troops = troops;
        this.player = player;
        this.mapController = mapController;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    private void updateBounds(){
        Rectangle bounds = super.getBounds();
        bounds.setLocation((int)x, (int)y);
    }
    public boolean chase(Player player){
        double diffX = this.x - player.getX();
        double diffY = this.y - player.getY();
        if(diffX * 2 < 40 && diffY * 2 < 40){
            return true;
        }
        return false;
    }
    public void newPath(Player player){
        if (chase(player)){
            this.newRoute(player.getX(), player.getY());
        }
        else {
            double newX = Math.random() * 100 - 50;
            double newY = Math.random() * 100 - 50;
            this.newRoute((int)(x + newX), (int)(y + newY));
        }
        
    }
    public void setPath(Path newPath) {
        this.path = newPath;
        System.out.println(path);
    }
    public void updatePos(){
        if (path == null || path.isDone()){
            newPath(player);
        }
        
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

    public void onCollision(Player player){
        System.out.println("Spiller traff" + name);
    }


    public void newRoute(double x, double y){
        mapController.newNpcPath((int)x, (int)y);
    }
}
