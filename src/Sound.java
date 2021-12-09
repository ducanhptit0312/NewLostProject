import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private Clip wav;

    public Sound(File path){
        try{
            AudioInputStream ais;
            ais = AudioSystem.getAudioInputStream(path);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels()*2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            wav = AudioSystem.getClip();
            wav.open(dais);
        }
        catch(Exception e){

        }
    }
    public void play(){
        if(wav !=null){
            stop();
            wav.setFramePosition(0);
            wav.start();
        }
    }
    public void playloop(){
        if (wav !=null){
            wav.setFramePosition(0);
            wav.start();
        }
    }
    public void stop(){
        if(wav.isRunning()) wav.stop();
    }
}
