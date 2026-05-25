import javax.swing.*;
import java.awt.*;

public class PlayerGUI extends Background{

    private AudioPlayer audioPlayer;
    private Vinyl vinyl;
    private Buttons buttons;
    private JLabel songTitle = new JLabel();
    private JLabel songArtist = new JLabel();
    private JSlider playbackSlider;
    private MenuPanel menuPanel;

    /**
     * JFrame setup
     */
    public PlayerGUI() throws Exception {
        super("/assets/deathBedBackground.png");
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);
    }

    public void temporaryGUI(CardLayout cardLayout, JPanel cards, AudioPlayer audioPlayer) {

        vinyl = new Vinyl("/assets/deathBedVinyl.png");
        Dimension vinylDimension = new Dimension(400,400);
        vinyl.setSize(vinylDimension);
        vinyl.setMaximumSize(vinylDimension);
        vinyl.setMinimumSize(vinylDimension);
        vinyl.setPreferredSize(vinylDimension);
        vinyl.setAlignmentX(Component.CENTER_ALIGNMENT);
        vinyl.startSpinning();
        add(vinyl);

        this.audioPlayer = audioPlayer;
        updateSong(audioPlayer.getCurrentSong());

        Dimension songTitleDimension = new Dimension(280,35);
        songTitle.setSize(songTitleDimension);
        songTitle.setMaximumSize(songTitleDimension);
        songTitle.setMinimumSize(songTitleDimension);
        songTitle.setPreferredSize(songTitleDimension);
        songTitle.setFont(DataLoading.loadFont("/fonts/Daydream DEMO.otf", 30));
        songTitle.setForeground(new Color(0, 0, 0));
        songTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(songTitle);

        songArtist.setFont(DataLoading.loadFont("/fonts/Daydream DEMO.otf", 20));
        songArtist.setForeground(new Color(0, 0, 0));
        songArtist.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(songArtist);


        playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setSize(300,40);
        Dimension sliderDimension = new Dimension(300,40);
        playbackSlider.setMaximumSize(sliderDimension);
        playbackSlider.setMaximumSize(sliderDimension);
        playbackSlider.setPreferredSize(sliderDimension);
        playbackSlider.setUI(new SliderUI(playbackSlider, new Color(110, 81, 200)));
        playbackSlider.setOpaque(false);
        playbackSlider.setBackground(null);
        playbackSlider.setSnapToTicks(false);
        playbackSlider.setFocusable(false);
        playbackSlider.setForeground(Color.BLACK);
        playbackSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(playbackSlider);

        buttons = new Buttons(vinyl);
        buttons.addButtons();
        add(buttons);

        add(Box.createRigidArea(new Dimension(0, 15)));

        menuPanel = new MenuPanel();
        menuPanel.addButtons(cardLayout, cards);
        Dimension menuDimension = new Dimension(270,60);
        menuPanel.setAlignmentX(CENTER_ALIGNMENT);
        menuPanel.setSize(menuDimension);
        menuPanel.setMaximumSize(menuDimension);
        menuPanel.setMinimumSize(menuDimension);
        menuPanel.setPreferredSize(menuDimension);
        add(menuPanel);

    }

    public void updateSong(Song song) {
        songTitle.setText(song.getSongTitle());
        songArtist.setText(song.getSongArtist());
    }

}

