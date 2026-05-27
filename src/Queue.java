import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * displays users selected playlist
 * @author mari
 */
public class Queue extends JPanel {

    private JButton addSong;
    private JFileChooser fileChooser;
    private Song song;
    private AudioPlayer audioPlayer;
    private JButton playButton;
    private ArrayList<Song> songList;
    private Dialog dialog;
    private boolean readyToPlay;
    private PlayerGUI playerGUI;

    public Queue(CardLayout cardLayout, JPanel cards, PlayerGUI playerGUI) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        setBackground(new Color(239, 211, 27));

        this.playerGUI = playerGUI;
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        load(cardLayout, cards);
        songList = new ArrayList<>();

    }

    public void load(CardLayout cardLayout, JPanel cards){
        addSong = new JButton("Add Song");
        add(addSong);

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            cardLayout.show(cards,"player");
        });

        add(back);
        makeItDoSomething();
        play(cardLayout, cards);
    }

    public void makeItDoSomething(){
        fileChooser = new JFileChooser();
        String downloadsPath =
                System.getProperty("user.home") + File.separator + "Downloads";
        fileChooser.setCurrentDirectory(new File(downloadsPath));
        fileChooser.setFileFilter(
                new FileNameExtensionFilter("Music Files", "mp3", "wav")
        );

        audioPlayer = new AudioPlayer();

        addSong.addActionListener(e -> {
            fileChooser.showOpenDialog(this);
            File selected = fileChooser.getSelectedFile();

            if (selected != null) {
                song = new Song(selected.getPath(), new Color(0,0,0));
                songList.add(song);
            }
        });
    }

    public Dialog newDialog(String text, int width, int height) {
        dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialog.add(label, BorderLayout.CENTER);
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        return dialog;
    }

    public void play(CardLayout cardLayout, JPanel cards) {
        playButton = new JButton("Play");
        add(playButton);
        playButton.addActionListener(e -> {
            if (!songList.isEmpty()) {
                audioPlayer.loadSong(songList.getFirst());
                readyToPlay = true;
                playerGUI.temporaryGUI(cardLayout, cards,audioPlayer);
                cardLayout.show(cards,"player");
            }else{
                newDialog("no songs",300,150);
            }
        });

    }

    public boolean isReadyToPlay() {
        return readyToPlay;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
