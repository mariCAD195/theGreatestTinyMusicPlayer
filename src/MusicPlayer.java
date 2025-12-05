import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {

    private Song currentSong;
    private Clip clip;
    private boolean isPaused;
    private long pausePosition;
    private boolean isPlaying;
    Playlist playlist;
    private int currentSongIndex;

    public MusicPlayer() {
        playlist = new Playlist();
        isPlaying = false;
        currentSongIndex = 0;
    }

    /**
     * for creating a playlist and adding songs
     * @return created playlist
     */
    public Playlist loadPlaylist() {
        playlist.addSong("Death Bed",new Song("Death Bed","POWFU","2:50","/musicFiles/Powfu - death bed (coffee for your head).wav"));
        playlist.getTonikuvPlaylist().get("Death Bed").setAssetPaths("assets/art/deathBedBackground.png","assets/art/deathBed_Vinyl.png","/fonts/Daydream DEMO.otf");
        playlist.addSong("Californication", new Song("Californication","Red Hot Chili Peppers", "5:29","/musicFiles/Red Hot Chili Peppers - Californication.wav"));

        return playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void loadSong(Playlist playlist, String name) {
        currentSong = playlist.getTonikuvPlaylist().get(name);
    }

    public void playSong() {
        isPlaying = true;
        new Thread(() -> {
            try {
                InputStream inputStream = getClass().getResourceAsStream(currentSong.getFilePath());

                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);

                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException e) {
                System.out.println("Unsupported audio file");
            } catch (IOException e) {
                System.out.println("IO exception");
            } catch (LineUnavailableException e) {
                System.out.println("Line unavailable");
            }

        }).start();
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            pausePosition = clip.getMicrosecondPosition();
            clip.stop();
            isPaused = true;
            System.out.println("Paused at: " + pausePosition);
        }
    }

    public void resume() {
        if (clip != null && isPaused) {
            clip.setMicrosecondPosition(pausePosition);
            clip.start();
            isPaused = false;
            System.out.println("Resumed from: " + pausePosition);
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public int getCurrentSongIndex() {
        return currentSongIndex;
    }

    public void setCurrentSongIndex(int currentSongIndex) {
        this.currentSongIndex = currentSongIndex;
    }
}
