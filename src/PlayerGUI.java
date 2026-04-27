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

    /**
     * JFrame setup
     */
    public PlayerGUI() {
        super();
        dataLoading = new DataLoading();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);

        musicPlayer = new MusicPlayer();
        buttons = new Buttons(musicPlayer, vinyl);
        buttons.addButtons();
    }


    /**
     * updates GUI to assets specific to a song that is currently playing
     *
     * @param playlist playlist with our songs
     * @param name     keyword of the song specific assets
     */
    public void tempoaryGUI() {

        vinyl = new Vinyl("/assets/deathBedVinyl.png");
        add(vinyl);

        songTitle.setText("Death Bed");
        songTitle.setFont(dataLoading.loadFont("/fonts/Daydream DEMO.otf", 30));
        songTitle.setForeground(new Color(0, 0, 0));
        add(songTitle);

        songArtist.setText("POWFU");
        songArtist.setFont(dataLoading.loadFont("/fonts/Daydream DEMO.otf", 20));
        songArtist.setForeground(new Color(0, 0, 0));
        add(songArtist);


        playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setUI(new SliderUI(playbackSlider, new Color(110, 81, 200)));
        playbackSlider.setOpaque(false);
        playbackSlider.setBackground(null);
        playbackSlider.setSnapToTicks(false);
        playbackSlider.setFocusable(false);
        playbackSlider.setForeground(Color.BLACK);
        add(playbackSlider);

        add(buttons);
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }
}

