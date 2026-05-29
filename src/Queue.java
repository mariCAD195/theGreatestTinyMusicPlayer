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
    private Dialog dialog;
    private boolean readyToPlay;
    private PlayerGUI playerGUI;
    private SongPanel songPanel;

    public Queue(CardLayout cardLayout, JPanel cards, PlayerGUI playerGUI) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        setBackground(new Color(239, 211, 27));

        this.playerGUI = playerGUI;
        load(cardLayout, cards);

    }

    public void load(CardLayout cardLayout, JPanel cards) {
        play(cardLayout, cards);

        addSong = new JButton("Add Song");
        add(addSong,CENTER_ALIGNMENT);

        songPanel = new SongPanel();

        makeItDoSomething();
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
                songPanel.addSong(song);
                songPanel.loadSongs();
                songPanel.repaint();
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
        playButton = new JButton(DataLoading.loadAssets("res/assets/playButton.png",50,50));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setOpaque(false);
        JPanel playWrapper = new JPanel();
        playWrapper.setOpaque(false);
        playWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));

        playWrapper.add(playButton);
        add(playWrapper);
        playButton.addActionListener(e -> {
            if (!songPanel.getSongs().isEmpty()) {
                audioPlayer.loadSong(songPanel.getSongs().get(0));
                readyToPlay = true;
                if (playerGUI.getCurrentTheme() != null) {
                    playerGUI.updateGUI(audioPlayer);
                }else{
                    playerGUI.defaultGUI();
                }
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
