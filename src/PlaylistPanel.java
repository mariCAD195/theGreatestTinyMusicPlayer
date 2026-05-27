import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * manages users playlists
 * @author mari
 */
public class PlaylistPanel extends JPanel {

    private HashMap<String,Playlist> playlists;
    private Playlist selected;

    public PlaylistPanel(){
        setBackground(null);
        setOpaque(false);

        playlists = new HashMap<>();
        playlists.put("Showcase playlist",showcasePlaylist());

        loadPanel();
        setLayout(new GridLayout(playlists.size(),0));
    }

    public void loadPanel(){
        for(Playlist playlist : playlists.values()){
            JButton option = new JButton();
            option.addActionListener(e -> {
                selected = playlists.get(option.getText());
            });
            add(option);
        }
    }


    public void addPlaylist(Playlist playlist){
        playlists.put(playlist.getName(),playlist);
    }

    public Playlist showcasePlaylist(){
        Playlist showcase = new Playlist();
        return showcase;
    }

}
