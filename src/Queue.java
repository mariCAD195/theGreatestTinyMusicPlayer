import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Queue extends JPanel {

    private JButton addSong;
    private JFileChooser fileChooser;
    private Song song;
    private AudioPlayer audioPlayer;

    public Queue(CardLayout cardLayout, JPanel cards) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        setBackground(new Color(239, 27, 197));

        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        load(cardLayout, cards);
    }

    public void load(CardLayout cardLayout, JPanel cards){
        addSong = new JButton("Add Song");
        add(addSong);

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            cardLayout.show(cards,"player");
        });

        add(back);
        makeItDoSomething(cardLayout, cards);
    }

    public void makeItDoSomething(CardLayout cardLayout, JPanel cards){
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(""));
        audioPlayer = new AudioPlayer();

        addSong.addActionListener(e -> {
            fileChooser.showOpenDialog(this);
            File selected = fileChooser.getSelectedFile();

            if (selected != null) {
                song = new Song(selected.getPath(), new Color(0,0,0));
                audioPlayer.loadSong(song);
                cardLayout.show(cards,"player");
            }
        });

    }
}
