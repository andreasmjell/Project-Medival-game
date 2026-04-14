/* for å starte spillet med JSON:
javac -cp ".;lib/json-20251224.jar" *.java
java -cp ".;lib/json-20251224.jar" .\Main.java
*/

public class Main {
    public static void main(String[] args) {
        MapController mc = new MapController();
        mc.start();
        
    }
}
