package src;
public class Settlement extends GameObject{
    String name;
    int x;
    int y;
    int troops;
    int timer;
    MapController mapController;
    UiHandler uiHandler;

    public Settlement(String name, int x, int y, int troops, int timer, MapController mapController){
        super((int)x, (int)y, 50, 50);
        uiHandler =  mapController.getUiHandler();
        this.name = name;
        this.x = x;
        this.y = y;
        this.troops = troops;
        this.timer = timer;
        this.mapController = mapController;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getName(){
        return name;
    }

    public void onCollision(Player player){  
        System.out.println("Player gikk i byen: " + name);
        mapController.getPlayer().updateTroops(troops);
        uiHandler.openSettlementMenu(this);
    }
}
