import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class AudioManager {

    ArrayList<String> soundList = new ArrayList<>();
    ArrayList<String> hitSoundList = new ArrayList<>();

    private final String SONG_FILENAME = "resources/sound/song.wav";
    long songStartTime = System.currentTimeMillis();
    int songLength = 45191;

    final int SOUND_LIST = 0;
    final int HIT_SOUND_LIST = 1;

    AudioInputStream audioIn;

    public AudioManager() {
        soundList.add("resources/sound/guns/mp5navy/mp5-1.wav");

        hitSoundList.add("resources/sound/hitsounds/bhit_flesh-2.wav");
        hitSoundList.add("resources/sound/hitsounds/headshot3.wav");
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

            // set the volume to 10%
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            double gain = 0.1;
            double dB = (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue((float) dB);

            clip.start();   // play sound

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
        }
    }

    public void playSong() {

        try {
            audioIn = AudioSystem.getAudioInputStream(getClass().getResource(SONG_FILENAME));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // set the volume to 20%
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            double gain = 0.2;
            double dB = (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue((float) dB);

            clip.start();   // play sound

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Song couldn't be loaded! :(");
        }
    }

}