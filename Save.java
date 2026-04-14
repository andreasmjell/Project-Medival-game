import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class Save {
    ArrayList<Settlement> settlement = new ArrayList<>();
    ArrayList<Npc> npc = new ArrayList<>();
    String filename;

    public ArrayList<Settlement> getSettlement(String filnavn){
        try {
        String innhold = new String(Files.readAllBytes(Paths.get(filnavn)));
        JSONArray jsonArray = new JSONArray(innhold);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if (obj.getString("type").equals("Settlement")){
                String navn = obj.getString("navn");
                int x = obj.getInt("x");
                int y = obj.getInt("y");
                int troops = obj.getInt("troops");
                int timer = obj.getInt("timer");
                settlement.add(new Settlement(navn, x, y, troops, timer));
            }
        }
        }catch (IOException e){
            e.printStackTrace();
        }
        return settlement;
    }
    public ArrayList<Npc> getNpc(String filnavn, MapController mapController, Player player) throws IOException{
        try {
        String innhold = new String(Files.readAllBytes(Paths.get(filnavn)));
        JSONArray jsonArray = new JSONArray(innhold);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if (obj.getString("type").equals("Npc")){
                String navn = obj.getString("navn");
                double x = obj.getDouble("x");
                double y = obj.getDouble("y");
                int troops = obj.getInt("troops");
                npc.add(new Npc(navn, x, y, troops, player, mapController));
            }
        }
    } catch (IOException e){
        e.printStackTrace();
    }
        return npc;
    }
}
