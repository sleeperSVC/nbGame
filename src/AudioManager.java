import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AudioManager {

    String gunShot = "resources/sound/guns/mp5navy/mp5-1.wav";
    String hit = "resources/sound/hitsounds/bhit_flesh-1.wav";
    String miss = "resources/sound/hitsounds/kevlar1.wav";
    String walk = "";

    public AudioManager(){
    }

    private void playSound(String soundFile) {
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioIn);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
    }


}
