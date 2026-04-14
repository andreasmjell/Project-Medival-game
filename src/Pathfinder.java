package src;
import java.util.ArrayList;

public class Pathfinder {
    
    public ArrayList<Point> findPath(double startX, double startY, double targetX, double targetY){
        ArrayList<Point> path = new ArrayList<>();

        Point point = new Point(targetX, targetY);


        path.add(point);

        return path;

    }
}
