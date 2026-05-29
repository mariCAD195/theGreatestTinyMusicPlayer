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

    public PlaylistPanel(CardLayout cardLayout, JPanel cards) {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);

        playlists = new HashMap<>();
        playlists.put("Showcase playlist",showcasePlaylist());

        loadPanel(cardLayout, cards);
    }

    public void loadPanel(CardLayout cardLayout, JPanel cards){
        for(Playlist playlist : playlists.values()){
            JButton option = new JButton(playlist.getName());

            option.setOpaque(true);
            option.setContentAreaFilled(true);
            option.setBorderPainted(false);
            option.setFocusPainted(false);
            option.setBackground(Color.BLACK);
            option.setForeground(Color.WHITE);

            option.addActionListener(e -> {
                selected = playlists.get(option.getText());
                cardLayout.show(cards, "queue");
            });
            add(option);
        }
    }


    public void addPlaylist(Playlist playlist){
        playlists.put(playlist.getName(),playlist);
    }

    public Playlist showcasePlaylist(){
        Playlist showcase = new Playlist();
        showcase.setName("Showcase playlist");

        playlists.put(showcase.getName(), showcase);
        return showcase;
    }

}
