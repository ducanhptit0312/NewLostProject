import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AmThanh {
    private Clip wav;
    public AmThanh(File path){//Xử lý file wav
        try{
            AudioInputStream ais;
            ais = AudioSystem.getAudioInputStream(path);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels()*2, baseFormat.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            wav = AudioSystem.getClip();
            wav.open(dais);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void playloop(){// fail khi không lặp được :(
        if (wav !=null){
            wav.setFramePosition(0);
            wav.start();
        }
    }
}
