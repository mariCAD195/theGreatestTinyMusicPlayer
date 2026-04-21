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

        JButton button = new JButton("hjbashbvjsc");
        button.setBounds(20,20,20,20);
        add(button);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, getWidth(), getHeight());
        setBackground(new Color(0,0,0));

        backgroundImage = new JLabel(dataLoading.loadAssets("res/assets/deathBedBackground.png", 400, 700));
        backgroundImage.setBounds(0, -37, getWidth(), getHeight());
        layeredPane.add(backgroundImage, Integer.valueOf(0));

        vinyl = new Vinyl("/assets/deathBedVinyl.png");
        vinyl.setBounds(0, 0, 400, 400);
        layeredPane.add(vinyl, Integer.valueOf(1));

        songTitle.setText("Death Bed");
        songTitle.setFont(dataLoading.loadFont("/fonts/Daydream DEMO.otf", 30));
        songTitle.setForeground(new Color(0, 0, 0));
        layeredPane.add(songTitle, Integer.valueOf(2));

        songArtist.setText("POWFU");
        songArtist.setFont(dataLoading.loadFont("/fonts/Daydream DEMO.otf", 20));
        songArtist.setForeground(new Color(0, 0, 0));
        layeredPane.add(songArtist, Integer.valueOf(3));


        playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setBounds(getWidth() / 2 - 300 / 2, 470, 300, 40);
        playbackSlider.setUI(new SliderUI(playbackSlider, new Color(110, 81, 200)));
        playbackSlider.setOpaque(false);
        playbackSlider.setBackground(null);
        playbackSlider.setSnapToTicks(false);
        playbackSlider.setFocusable(false);
        playbackSlider.setForeground(Color.BLACK);
        layeredPane.add(playbackSlider, Integer.valueOf(4));

        add(layeredPane);
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }
}

