import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioPlayer {

    private Song currentSong;
    private AdvancedPlayer advancedPlayer;

    public AudioPlayer() {}

    public void loadSong(Song song) {
        currentSong = song;

        if(currentSong!=null){
            playCurrentSong();
        }
    }

    private void playCurrentSong() {
        try {
            FileInputStream fileInputStream = new FileInputStream(currentSong.getFilePath());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            advancedPlayer = new AdvancedPlayer(bufferedInputStream);
            
            startMusicThread();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }

    private void startMusicThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    advancedPlayer.play();
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
