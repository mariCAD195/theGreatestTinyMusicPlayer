import java.util.ArrayList;
import java.util.HashMap;

public class Playlist {

    private HashMap<String,Song> playlist;

    public Playlist() {
        this.playlist = new HashMap<>();
    }

    public HashMap<String, Song> getPlaylist() {
        return playlist;
    }

    public void addSong(String name, Song song) {
        this.playlist.put(name,song);
    }

    public String getSongByNumber(int number) {
        ArrayList<Song> songs = new ArrayList<>();
        for(Song s : this.playlist.values()) {
            songs.add(s);
        }
        return songs.get(number).getSongTitle();
    }

}
