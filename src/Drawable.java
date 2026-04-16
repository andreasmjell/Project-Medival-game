package src;
import java.awt.*;

public interface Drawable {
    double getY();
    double getX();
    void draw(Graphics g, double cameraX, double cameraY);
}
