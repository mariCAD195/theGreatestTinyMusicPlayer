import javax.swing.*;
import java.awt.*;

public class Library extends JPanel {

    private JButton newPlaylist;

    public Library(CardLayout cardLayout, JPanel cards) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        setBackground(new Color(27, 94, 239));

        load(cardLayout, cards);
    }

    public void load(CardLayout cardLayout, JPanel cards){
        newPlaylist = new JButton("New Playlist");
        add(newPlaylist);

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            cardLayout.show(cards,"player");
        });

        add(back);
        makeItDoSomething(cardLayout, cards);
    }

    public void makeItDoSomething(CardLayout cardLayout, JPanel cards){
        newPlaylist.addActionListener(e -> {
            cardLayout.show(cards,"queue");
        });
    }
}
