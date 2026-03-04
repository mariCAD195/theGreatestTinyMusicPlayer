import javax.sound.sampled.*;
import java.awt.*;
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
    private DataLoading dataLoading;

    public MusicPlayer() {
        playlist = new Playlist();
        isPlaying = false;
        currentSongIndex = 0;
        dataLoading = new DataLoading();
    }

    /**
     * for creating a playlist and adding songs
     * @return created playlist
     */
    public Playlist loadPlaylist() {
        playlist.addSong("Death Bed",new Song("Death Bed","POWFU","2:50", "/musicFiles/Powfu - death bed (coffee for your head).wav",new Color(110, 81, 200)));
        playlist.getTonikuvPlaylist().get("Death Bed").setAssetPaths("res/assets/deathBedBackground.png","res/assets/deathBedVinyl.png", "/fonts/Daydream DEMO.otf");
        playlist.addSong("Californication", new Song("Californication","Red Hot Chili Peppers", "5:29", "res/musicFiles/Red Hot Chili Peppers - Californication.wav",new Color(0,0,0)));

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
                clip = AudioSystem.getClip();
                clip.open(dataLoading.loadSong(currentSong.getFilePath()));
                clip.start();
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP&&!isPaused) {
                            double position = (double) clip.getMicrosecondPosition();
                            double length = (double) clip.getMicrosecondLength();
                            if (position >= length - 1000) {
                                songFinished();
                            }
                        }
                    }
                });
            } catch (IOException e) {
                System.out.println("IO exception");
            } catch (LineUnavailableException e) {
                System.out.println("Line unavailable");
            }

        }).start();
    }

    public void songFinished() {
        isPlaying = false;
        if (currentSongIndex < playlist.getTonikuvPlaylist().size()) {
            if (clip != null) {
                clip.stop();
                clip.close();
                clip = null;
            }
            String next = playlist.getSongByNumber(currentSongIndex+1);
            loadSong(playlist, next);
            currentSongIndex++;

            playSong();
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            pausePosition = clip.getMicrosecondPosition();
            clip.stop();
            isPaused = true;
        }
    }

    public void resume() {
        if (clip != null && isPaused) {
            clip.setMicrosecondPosition(pausePosition);
            clip.start();
            isPaused = false;
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
