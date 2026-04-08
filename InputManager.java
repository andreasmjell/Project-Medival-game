import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class InputManager{
    HashMap<Integer, Runnable> keyBindings;
    HashSet<Integer> keyPressed;
    ArrayList<Integer> canHold;

    Ui ui;
    MapController mapController;
    Camera camera;
    Player player;

    public InputManager(Ui ui, MapController mapController, Camera camera, Player player){
        keyBindings = new HashMap<Integer, Runnable>();
        keyPressed = new HashSet<Integer>();
        canHold = new ArrayList<Integer>();

        this.ui = ui;
        this.mapController = mapController;
        this.camera = camera;
        this.player = player;


        this.bindDefault();
    }
    public void update(){
        for (Integer key : keyPressed){
            Runnable action = keyBindings.get(key);
            if (action != null){
                action.run();
            }
        }
    }

    public void keyPressed(int keycode, boolean pressed){
        if(canHold.contains(keycode)){
            if (pressed){
                keyPressed.add(keycode);
            }
            else{
                if (keyPressed.contains(keycode)){
                    keyPressed.remove(keycode);
                }
            }
        }
        else{
            Runnable action = keyBindings.get(keycode);
            if (action != null){
                action.run();
            }
        }
    }

    public void bindKey(int keycode, Runnable action){
        System.out.println("BINDING KEYS!");
        keyBindings.put(keycode, action);
    }

    public void bindDefault(){

        //esc
        this.bindKey(27, () -> ui.mainMenu());

        //pil opp
        this.bindKey(38, () -> camera.y -= 10);
        //pil ned
        this.bindKey(40, () -> camera.y += 10);
        //pil venste
        this.bindKey(37, () -> camera.x -= 10);
        //pil høyre
        this.bindKey(39, () -> camera.x += 10);


    }


}

