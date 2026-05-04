import javax.swing.*;
import java.awt.*;

public class PlayerGUI extends JPanel{

    private MusicPlayer musicPlayer;
    private JLayeredPane layeredPane;
    private JLabel backgroundImage;
    private Vinyl vinyl;
    private Buttons buttons;
    private JLabel songTitle = new JLabel();
    private JLabel songArtist = new JLabel();
    private JSlider playbackSlider;
    private DataLoading dataLoading;
    private MenuPanel menuPanel;

    /**
     * JFrame setup
     */
    public PlayerGUI() {
        super();
        dataLoading = new DataLoading();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);

        musicPlayer = new MusicPlayer();
    }


    /**
     * updates GUI to assets specific to a song that is currently playing
     *
     * @param playlist playlist with our songs
     * @param name     keyword of the song specific assets
     */
    public void temporaryGUI() {

        vinyl = new Vinyl("/assets/deathBedVinyl.png");
        Dimension vinylDimension = new Dimension(400,400);
        vinyl.setSize(vinylDimension);
        vinyl.setMaximumSize(vinylDimension);
        vinyl.setMinimumSize(vinylDimension);
        vinyl.setPreferredSize(vinylDimension);
        vinyl.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(vinyl);

        songTitle.setText("Death Bed");
        songTitle.setFont(dataLoading.loadFont("/fonts/Daydream DEMO.otf", 30));
        songTitle.setForeground(new Color(0, 0, 0));
        songTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(songTitle);

        songArtist.setText("POWFU");
        songArtist.setFont(dataLoading.loadFont("/fonts/Daydream DEMO.otf", 20));
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

        buttons = new Buttons(musicPlayer, vinyl);
        buttons.addButtons();
        add(buttons);

        menuPanel = new MenuPanel();
        menuPanel.addButtons();
        menuPanel.setAlignmentX(CENTER_ALIGNMENT);
        add(menuPanel);

    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }
}

