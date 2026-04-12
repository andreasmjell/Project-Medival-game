public class Settlement extends GameObject{
    String name;
    int x;
    int y;
    int troops;
    int timer;

    public Settlement(String name, int x, int y, int troops, int timer){
        super((int)x, (int)y, 50, 50);
        this.name = name;
        this.x = x;
        this.y = y;
        this.troops = troops;
        this.timer = timer;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void onCollision(Player player){   // Dette vil skje hver gang game loopen går 50/60 ganger i sekundet. Må fikses!!!!
        System.out.println("Player gikk i byen: " + name);
    }
}
