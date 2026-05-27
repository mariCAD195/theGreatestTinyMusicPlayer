import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * manages the audio
 * @author (youtube guys username)
 * edited by mari
 */
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
            System.out.println(advancedPlayer.toString());

            startMusicThread();
            System.out.println("new music thread started: " + advancedPlayer.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }

    public void pauseSong() {
        System.out.println("pause: "+advancedPlayer.toString());
        if(advancedPlayer!=null){
            System.out.println("here");
            paused = true;
            stopSong();
        }else {
            System.out.println("pause: advancedPlayer is null");
        }
    }

    public void resumeSong() {
        paused = false;
        startMusicThread();
    }

    public void stopSong() {
        System.out.println("stop: "+advancedPlayer.toString());
        if(advancedPlayer!=null){
            System.out.println("here");
            advancedPlayer.stop();
            advancedPlayer.close();
            advancedPlayer = null;
        }else{
            System.out.println("stop: advancedPlayer is null");
        }
    }

    private void startMusicThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runningMusicThread: "+advancedPlayer.toString());
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
