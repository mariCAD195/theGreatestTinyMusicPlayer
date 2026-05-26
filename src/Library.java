import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class Library extends JPanel {

    private JButton newPlaylist;
    private JList playlistList;
    private JLabel title;
    private JLabel title2;

    public Library(CardLayout cardLayout, JPanel cards) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        setBackground(new Color(27, 94, 239));

        load(cardLayout, cards);
    }

    public void load(CardLayout cardLayout, JPanel cards){
        playlistList = new JList<>();

        add(Box.createRigidArea(new Dimension(0, 15)));

        title = new JLabel("Welcome to your library");
        title.setFont(DataLoading.loadFont("/fonts/alagard.ttf",30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.BLACK);

        add(title);

        title2 = new JLabel("what's the vibe today?");
        title2.setFont(DataLoading.loadFont("/fonts/alagard.ttf",30));
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        title2.setForeground(Color.BLACK);

        add(title2);

        add(Box.createRigidArea(new Dimension(0, 15)));

        newPlaylist = new JButton("New Playlist");
        newPlaylist.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(newPlaylist);

        makeItDoSomething(cardLayout, cards);
    }

    public void makeItDoSomething(CardLayout cardLayout, JPanel cards){
        newPlaylist.addActionListener(e -> {
            cardLayout.show(cards,"queue");
        });
    }
}
