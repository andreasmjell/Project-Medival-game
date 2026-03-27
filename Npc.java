public class Npc {
    String navn = "Enemy Army";
    int x;
    int y;
    int troops;

    public Npc(int x, int y, int troops){
        this.x = x;
        this.y = y;
        this.troops = troops;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void chase(Player player){
        int diffX = this.x - player.getX();
        int diffY = this.y - player.getY();
        if(diffX * 2 < 40 && diffY * 2 < 40){
            this.newRoute(player.getX(), player.getY());
        }
    }

    public void engadeCombat(Player player){
        int diffX = this.x - player.getX();
        int diffY = this.y - player.getY();
        if(diffX * 2 < 20 && diffY * 2 < 20){
            //Start combat
        }
    }
    public void newRoute(int x, int y){
        //Map move to x,y
    }
}
