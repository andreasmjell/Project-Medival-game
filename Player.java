public class Player {
    int troops;
    double x;
    double y;
    
    public Player(int x, int y, int troops){
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
}
