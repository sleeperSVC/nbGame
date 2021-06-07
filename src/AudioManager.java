import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.io.IOException;
import java.util.ArrayList;

//TODO: fix hitsounds

public class AudioManager {

    ArrayList<String> soundList = new ArrayList<>();
    ArrayList<String> hitSoundList = new ArrayList<>();

    public final int SOUND_LIST = 0;
    public final int HIT_SOUND_LIST = 1;

    AudioInputStream audioIn;

    public AudioManager() {
        soundList.add("resources/sound/guns/mp5navy/mp5-1.wav");

//        hitSoundList.add("resources/sound/hitsounds/kevlar1.wav");
//        hitSoundList.add("resources/sound/hitsounds/kevlar2.wav");
//        hitSoundList.add("resources/sound/hitsounds/kevlar3.wav");
//        hitSoundList.add("resources/sound/hitsounds/kevlar4.wav");
//        hitSoundList.add("resources/sound/hitsounds/kevlar5.wav");
        hitSoundList.add("resources/sound/hitsounds/headshot3.wav");
//        hitSoundList.add("resources/sound/hitsounds/headshot4.wav");
//        hitSoundList.add("resources/sound/hitsounds/headshot5.wav");




    }

    public void playSound(int i, int whichList) {

        try {
            if (whichList == HIT_SOUND_LIST) {
                audioIn = AudioSystem.getAudioInputStream(getClass().getResource(hitSoundList.get(i)));
            } else {
                audioIn = AudioSystem.getAudioInputStream(getClass().getResource(soundList.get(i)));
            }
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // volume stuff
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            double gain = 0.1;
            double dB = (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue((float) dB);

            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

}
