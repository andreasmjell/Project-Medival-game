public class Player {
    int troops;
    int x;
    int y;
    
    public Player(int x, int y, int troops){
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
}
