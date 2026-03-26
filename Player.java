public class Player {
    int troops;
    int x;
    int y;
    
    public Player(int x, int y, int troops){
        this.troops = troops;
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
