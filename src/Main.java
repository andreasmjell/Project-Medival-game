package src;
/* for å starte spillet med JSON:

WINDOWS
javac -cp ".;lib/json-20251224.jar" *.java
java -cp ".;lib/json-20251224.jar" .\Main.java
NY:
javac -cp ".;lib/json-20251224.jar" src/*.java src/menu/*.java
java -cp ".;lib/json-20251224.jar" src.Main

MAC
javac -cp ".:lib/json-20251224.jar" *.java
java -cp ".:lib/json-20251224.jar" Main
NY:
java -cp ".:lib/json-20251224.jar" src.Main
javac -cp ".:lib/json-20251224.jar" src/*.java src/menu/*.java


*/

public class Main {
    public static void main(String[] args) {
        MapController mc = new MapController();
        mc.start();
        
    }
}
