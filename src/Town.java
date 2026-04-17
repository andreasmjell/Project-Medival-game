package src;

public class Town extends Settlement {

    public Town(String name, int x, int y, int troops, int timer, MapController mapController) {
        super(name, x, y, troops, timer, mapController, "assets/by.png");
    }

    @Override
    public void onCollision(Player player) {
        System.out.println("Player gikk inn i byen: " + name);
        mapController.getPlayer().updateTroops(troops);
        mapController.openSettlementMenu(this);
    }
}