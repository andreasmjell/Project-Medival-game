import java.util.ArrayList;

public class Path {
    ArrayList<Point> points = new ArrayList<>();

    public Path(ArrayList<Point> points){
        this.points = points;

    }

    public Point getCurrent(){
        if (points.isEmpty()){
            return null;
        }
        else{
            return points.get(0);
        }
    }

    public void advance(){
        if (! points.isEmpty()){
            points.remove(0);
        }
    }

    public boolean isDone(){
        return points.isEmpty();
    }

    public String toString(){
        return points.get(0).toString();
    }
}