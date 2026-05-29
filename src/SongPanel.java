import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SongPanel extends JPanel {

    private HashMap<String,Song> songs;

    public SongPanel(){

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);

        songs = new HashMap<>();
        loadSongs();

    }

    public void loadSongs(){
        if(!songs.isEmpty()){
            for(Song song : songs.values()){
                JButton option = new JButton(song.getSongTitle());

                option.setOpaque(true);
                option.setContentAreaFilled(true);
                option.setBorderPainted(false);
                option.setFocusPainted(false);
                option.setBackground(Color.BLACK);
                option.setForeground(Color.WHITE);

                add(option);
            }
        }
    }

    public void addSong(Song song){
        songs.put(song.getSongTitle(),song);
    }

    public HashMap<String, Song> getSongs() {
        return songs;
    }
}
