package src;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class Save {
    private GameContext gameContext;

    String filename;
    ArrayList<Settlement> settlementList = new ArrayList<>();
    ArrayList<Npc> npcList = new ArrayList<>();

    public Save(GameContext gameContext){
        this.gameContext = gameContext;
    }

    public ArrayList<Settlement> readSettlement(String filename){
        try {
        String innhold = new String(Files.readAllBytes(Paths.get(filename)));
        JSONArray jsonArray = new JSONArray(innhold);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if (obj.getString("type").equals("Settlement")){
                String navn = obj.getString("navn");
                int x = obj.getInt("x");
                int y = obj.getInt("y");
                int troops = obj.getInt("troops");
                int timer = obj.getInt("timer");
                settlementList.add(new Town(navn, x, y, troops, timer));
            }
        }
        }catch (IOException e){
            e.printStackTrace();
        }
        return settlementList;
    }
    public ArrayList<Npc> readNpc(String filename){
        try {
        String innhold = new String(Files.readAllBytes(Paths.get(filename)));
        JSONArray jsonArray = new JSONArray(innhold);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if (obj.getString("type").equals("Npc")){
                String faction = obj.getString("faction");
                String name = obj.getString("navn");
                double x = obj.getDouble("x");
                double y = obj.getDouble("y");
                int troops = obj.getInt("troops");
                npcList.add(new Bandit(gameContext,faction, name, x, y, troops));
            }
        }
    } catch (IOException e){
        e.printStackTrace();
    }
    /*for (Npc target : npc){
        if (!(target.getFaction().equals("Bandit"))){
            for (Npc newEnemy : npc){
                if (newEnemy.getFaction().equals("Bandit")){
                    target.addEnemy(newEnemy);
                }
            }
        }
    }*/
    return npcList;
    }
}
