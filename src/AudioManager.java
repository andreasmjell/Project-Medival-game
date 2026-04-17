package src;
import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {
    Clip music;
    Clip battleSound;
    Clip enemyDefeated;
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
    public void startBattleSound() throws Exception{
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File("src/assets/battleStart.wav"));
        battleSound = AudioSystem.getClip();
        battleSound.open(audio);
        battleSound.start();
    }
    public void enemyDefeated()throws Exception {
        AudioInputStream audio1 = AudioSystem.getAudioInputStream(new File("src/assets/enemyDefeated1.wav"));
        enemyDefeated = AudioSystem.getClip();
        enemyDefeated.open(audio1);
        enemyDefeated.start();
    }

    public void volumeDown(){
    float current = gain.getValue();
    float min = gain.getMinimum();
    gain.setValue(Math.max(current - 2.0f, min));
    }

    public void volumeUp(){
        float current = gain.getValue();
        float max = gain.getMaximum();
        gain.setValue(Math.min(current + 2.0f, max));
    }

}


/*
Lurer på om vi kan bruke JavaFX til bagrunnsmusikk og Clip for korte effekt lyder.
JavaFX må kjøre på egen string hvertfall. Litt usikker på hvordan vi løser de korte effekt lydene.
*/