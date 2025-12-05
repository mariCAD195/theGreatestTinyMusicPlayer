import java.util.ArrayList;
import java.util.HashMap;

public class Playlist {

    private HashMap<String,Song> tonikuvPlaylist;

    public Playlist() {
        this.tonikuvPlaylist = new HashMap<>();
    }

    public HashMap<String, Song> getTonikuvPlaylist() {
        return tonikuvPlaylist;
    }

    public void addSong(String name, Song song) {
        this.tonikuvPlaylist.put(name,song);
    }

    public String getSongByNumber(int number) {
        switch(number){
            case 0: return "Death Bed";
            case 1: return "Californication";
        }
        return null;
    }

}
