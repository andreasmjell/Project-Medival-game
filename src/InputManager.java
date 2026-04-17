package src;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class InputManager{
    HashMap<Integer, Runnable> keyBindings;
    HashSet<Integer> keyPressed, keyToggled;
    ArrayList<Integer> canHold, toggleWhileHold;

    UiHandler ui;
    MapController mapController;
    Camera camera;
    Player player;

    public InputManager(UiHandler ui, MapController mapController, Camera camera, Player player){
        keyBindings = new HashMap<Integer, Runnable>();
        keyPressed = new HashSet<Integer>();
        keyToggled = new HashSet<Integer>();
        canHold = new ArrayList<Integer>();
        toggleWhileHold = new ArrayList<Integer>();

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
        if (toggleWhileHold.contains(keycode)){
            if(!pressed && keyToggled.contains(keycode)){
                keyToggled.remove(keycode);
                Runnable action = keyBindings.get(keycode);
                if (action != null){
                    action.run();
                }
            }
            else{
                keyToggled.add(keycode);
                Runnable action = keyBindings.get(keycode);
                if (action != null){
                    action.run();
                }
            }
        }

        else{
            if(canHold.contains(keycode)){
                if (pressed && !keyPressed.contains(keycode)){
                    keyPressed.add(keycode);
                }
                else if (!pressed){
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
    }

    public void bindKey(int keycode, Runnable action){
        System.out.println("BINDING KEYS!");
        keyBindings.put(keycode, action);
    }

    public void bindDefault(){

        //esc
        this.bindKey(27, () -> mapController.openPauseMenu()); //Åpner main menu. !! Må byttes til pause overlay. Starter flere game loops!

        //Movement----------------------
        //pil opp
        this.bindKey(38, () -> camera.y -= 15);
        //pil ned
        this.bindKey(40, () -> camera.y += 15);
        //pil venste
        this.bindKey(37, () -> camera.x -= 15);
        //pil høyre
        this.bindKey(39, () -> camera.x += 15);
        //venstre control hold for å legge til destinasjon på path
        this.bindKey(17, () -> player.setAddToPath()); //Funkgerer dårlig!!
        canHold.add(37); canHold.add(38); canHold.add(39); canHold.add(40); toggleWhileHold.add(17);
        



        //Camera------------------------ FUNKER IKKE! AVENTER
        //Komma (zoom in)
        this.bindKey(44, () ->  camera.zoomIn());
        //Punktum (zoom ut)
        this.bindKey(46, () -> camera.zoomOut());


    }


}