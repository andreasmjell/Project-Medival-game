public class Npc extends GameObject{
    String name = "Enemy Army";
    double x;
    double y;
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
    public boolean chase(Player player, Point chase){
        double diffX = this.x - player.getX();
        double diffY = this.y - player.getY();
        if(diffX * 2 < 40 && diffY * 2 < 40){
            this.newRoute(player.getX(), player.getY());
            return true;
        }
        return false;
    }
    public void updatePos(){
        if (!chase(player)){
            int newX;
            int newY;
        }
    }

    public void onCollision(Player player){
        System.out.println("Spiller traff" + name);
    }


    public void newRoute(double x, double y){
        mapController.newNpcPath(x, y);
    }
}
