package src;
import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {
    Clip music;
    FloatControl gain;



    public void play() throws Exception {
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("src/assets/musikk.wav"));
        music = AudioSystem.getClip();
        music.open(audio);
        gain = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);

        music.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        music.stop();
    }

    public void start(){
        music.start();
    }

    public void setVolume(int sliderValue) {
        if (music == null || !music.isOpen()) return;

        float min = gain.getMinimum();   // typically around -80dB
        float max = gain.getMaximum();   // typically around +6dB

        // Map 0-100 linearly across the control's actual dB range
        float dB = min + (sliderValue / 100f) * (max - min);
        gain.setValue(dB);
    }

}


/*
Lurer på om vi kan bruke JavaFX til bagrunnsmusikk og Clip for korte effekt lyder.
JavaFX må kjøre på egen string hvertfall. Litt usikker på hvordan vi løser de korte effekt lydene.
*/