package src;
import java.awt.*;

public interface Drawable {
    double getY();
    void draw(Graphics g, double cameraX, double cameraY);
}
