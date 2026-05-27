import java.util.ArrayList;
import java.util.HashMap;

/**
 * stores users songs
 * @author mari
 */
public class Playlist {

    private HashMap<String,Song> playlist;
    private String name;

    public Playlist() {
        this.playlist = new HashMap<>();
    }

    public HashMap<String, Song> getPlaylist() {
        return playlist;
    }

    public void addSong(String name, Song song) {
        this.playlist.put(name,song);
    }

    public String getName() {
        return name;
    }
}
