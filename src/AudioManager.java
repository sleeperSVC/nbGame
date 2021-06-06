import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AudioManager {

    private ArrayList<String> soundList = new ArrayList<>();
    private ArrayList<String> hitSoundList = new ArrayList<>();

    public AudioManager() {
        soundList.add("resources/sound/guns/smokegrenade/grenade_hit1.wav");
        soundList.add("");
        hitSoundList.add("resources/sound/hitsounds/kevlar1.wav");
        hitSoundList.add("resources/sound/hitsounds/kevlar2.wav");
        hitSoundList.add("resources/sound/hitsounds/kevlar3.wav");
        hitSoundList.add("resources/sound/hitsounds/kevlar4.wav");
        hitSoundList.add("resources/sound/hitsounds/kevlar5.wav");
        hitSoundList.add("resources/sound/hitsounds/headshot3.wav");
        hitSoundList.add("resources/sound/hitsounds/headshot4.wav");
        hitSoundList.add("resources/sound/hitsounds/headshot5.wav");
    }

    public void playSound(int i) {
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
