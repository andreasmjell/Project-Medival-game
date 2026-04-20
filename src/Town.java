package src;

public class Town extends Settlement {
    private GameContext gameContext;

    public Town(GameContext gameContext, String name, int x, int y, int troops, int timer) {
        super(name, x, y, troops, timer, "assets/by.png");
        this.gameContext = gameContext;
    }

    @Override
    public void onCollision(Player player) {
        System.out.println("Player gikk inn i byen: " + name);
        gameContext.player.updateTroops(troops);
        gameContext.mapController.openSettlementMenu(this);
    }
}