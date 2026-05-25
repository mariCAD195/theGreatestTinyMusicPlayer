import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioPlayer extends PlaybackListener {

    private Song currentSong;
    private AdvancedPlayer advancedPlayer;
    private boolean paused;
    private int currentSongPosition;

    public AudioPlayer() {}

    public void loadSong(Song song) {
        currentSong = song;

        if(currentSong!=null){
            playCurrentSong();
        }
    }

    public void playCurrentSong() {
        try {
            FileInputStream fileInputStream = new FileInputStream(currentSong.getFilePath());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            advancedPlayer = new AdvancedPlayer(bufferedInputStream);
            advancedPlayer.setPlayBackListener(this);

            startMusicThread();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }

    public void pauseSong() {
        if(advancedPlayer!=null){
            System.out.println("here");
            paused = true;
            stopSong();
        }
    }

    public void resumeSong() {
        paused = false;
        startMusicThread();
    }

    public void stopSong() {
        if(advancedPlayer!=null){
            System.out.println("here");
            advancedPlayer.stop();
            advancedPlayer.close();
            advancedPlayer = null;
        }
    }

    private void startMusicThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(paused){
                        advancedPlayer.play(currentSongPosition);
                    }else{
                        advancedPlayer.play();
                    }
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void playbackStarted(PlaybackEvent evt) {
        System.out.println("playbackStarted");
    }

    @Override
    public void playbackFinished(PlaybackEvent evt) {
        System.out.println("playbackFinished");
        if(paused){
            currentSongPosition = evt.getFrame();
            //currentSongPosition += (int)((double)evt.getFrame()*currentSong.getFrameRate());
        }
    }

    public Song getCurrentSong() {
        return currentSong;
    }
}
