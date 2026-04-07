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

    public void moveTo(){ //Lager en ny path til player til en desitnasjon. Denne destinasjonen må havne hos MapController

    }
}
