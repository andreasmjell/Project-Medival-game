public class Npc extends GameObject{
    String name = "Enemy Army";
    double x;
    double y;
    int troops;

    public Npc(double x, double y, int troops){
        super((int)x, (int)y, 80, 80);
        this.x = x;
        this.y = y;
        this.troops = troops;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void chase(Player player){
        double diffX = this.x - player.getX();
        double diffY = this.y - player.getY();
        if(diffX * 2 < 40 && diffY * 2 < 40){
            this.newRoute(player.getX(), player.getY());
        }
    }

    public void onCollision(Player player){
        System.out.println("Spiller traff" + name);
    }


    public void newRoute(double x, double y){
        //Map move to x,y
    }
}
