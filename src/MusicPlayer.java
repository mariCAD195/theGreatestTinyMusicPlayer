import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {

    private Song currentSong;
    private Clip clip;
    private boolean isPaused;
    private long pausePosition;
    Playlist playlist;

    public MusicPlayer() {
        playlist = new Playlist();
    }

    /**
     * for creating a playlist and adding songs
     * @return created playlist
     */
    public Playlist loadPlaylist() {
        playlist.addSong("Death Bed",new Song("Death Bed","POWFU","2:50","/musicFiles/Powfu - death bed (coffee for your head).wav"));
        playlist.getTonikuvPlaylist().get("Death Bed").setAssetPaths("src/assets/deathBedBackground.png","src/assets/deathBed_Vinyl.png","/fonts/Daydream DEMO.otf");

        return playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void loadSong(Playlist playlist, String name) {
        currentSong = playlist.getTonikuvPlaylist().get(name);
    }

    public void playSong() {
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

}
