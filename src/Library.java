import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class Library extends JPanel {

    private JButton newPlaylist;
    private JLabel title;
    private JLabel title2;
    private PlaylistPanel playlistPanel;

    public Library(CardLayout cardLayout, JPanel cards) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        setBackground(new Color(27, 94, 239));

        load(cardLayout, cards);
    }

    public void load(CardLayout cardLayout, JPanel cards){

        add(Box.createRigidArea(new Dimension(0, 15)));

        title = new JLabel("Welcome to your library");
        title.setFont(DataLoading.loadFont("/fonts/alagard.ttf",30));
        title.setForeground(Color.BLACK);
        add(title,CENTER_ALIGNMENT);

        title2 = new JLabel("what's the vibe today?");
        title2.setFont(DataLoading.loadFont("/fonts/alagard.ttf",30));
        title2.setForeground(Color.BLACK);
        add(title2, CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 15)));

        newPlaylist = new JButton("New Playlist");
        add(newPlaylist,CENTER_ALIGNMENT);

        playlistPanel = new PlaylistPanel();
        add(playlistPanel,CENTER_ALIGNMENT);

        makeItDoSomething(cardLayout, cards);
    }

    public void makeItDoSomething(CardLayout cardLayout, JPanel cards){
        newPlaylist.addActionListener(e -> {
            playlistPanel.addPlaylist(new Playlist());
            playlistPanel.loadPanel();
            cardLayout.show(cards,"queue");
        });
    }
}
