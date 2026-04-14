package src;

public class Camera {
    double x,y;  //Kordinater til kameraet (top left)
    double width, height;  //Skjermstørrelse
    double zoom = 1.0;
    Player player;
    boolean focusPlayer = true;



    public Camera(double x, double y, int width, int height, Player player){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.player = player;
    }

    public double getX(){ 
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZoom(){
        return zoom;
    }

    public void zoomIn(){
        zoom += 0.2;
    }
    
    public void zoomOut(){
        zoom -= 0.2;
    }

    public void switchFocusPlayer(){
            if (focusPlayer){
                focusPlayer = false;
            }
            else{
                focusPlayer = true;
            }
        }

    public void focusPlayer(){
        if (focusPlayer){
            x = player.getX() - (width/2) +20 ; // +20 for størrelsen på bildet til karakteren
            y = player.getY() - (height/2) +20;
        }
    }
}
