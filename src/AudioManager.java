package src;
import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {
    Clip music;


    public void play() throws Exception {
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("src/assets/musikk.wav"));
        music = AudioSystem.getClip();
        music.open(audio);
        music.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        music.stop();
    }

    public void start(){
        music.start();
    }

}


/*
Lurer på om vi kan bruke JavaFX til bagrunnsmusikk og Clip for korte effekt lyder.
JavaFX må kjøre på egen string hvertfall. Litt usikker på hvordan vi løser de korte effekt lydene.
*/