import javax.swing.*;
import java.awt.*;

/**
 * the main music player you see when you play music
 * @author mari
 */
public class PlayerGUI extends Background{

    private AudioPlayer audioPlayer;
    private Vinyl vinyl;
    private Buttons buttons;
    private JLabel songTitle = new JLabel();
    private JLabel songArtist = new JLabel();
    private JSlider playbackSlider;
    private MenuPanel menuPanel;
    private Theme currentTheme;

    public PlayerGUI() throws Exception {
        super("/assets/deathBedBackground.png");
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);
    }

    public void updateGUI(AudioPlayer audioPlayer) {

        currentTheme = audioPlayer.getCurrentSong().getSongsTheme();

        vinyl = new Vinyl(currentTheme.getVinyl());
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
        songTitle.setFont(DataLoading.loadFont(currentTheme.getFont(), 30));
        songTitle.setForeground(new Color(0, 0, 0));
        songTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(songTitle);

        songArtist.setFont(DataLoading.loadFont(currentTheme.getFont(), 20));
        songArtist.setForeground(new Color(0, 0, 0));
        songArtist.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(songArtist);


        playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setSize(300,40);
        Dimension sliderDimension = new Dimension(300,40);
        playbackSlider.setMaximumSize(sliderDimension);
        playbackSlider.setMaximumSize(sliderDimension);
        playbackSlider.setPreferredSize(sliderDimension);
        playbackSlider.setUI(new SliderUI(playbackSlider, new Color(currentTheme.getProgressBarColorR(), currentTheme.getProgressBarColorG(), currentTheme.getProgressBarColorB())));
        playbackSlider.setOpaque(false);
        playbackSlider.setBackground(null);
        playbackSlider.setSnapToTicks(false);
        playbackSlider.setFocusable(false);
        playbackSlider.setForeground(Color.BLACK);
        playbackSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(playbackSlider);

        buttons = new Buttons(vinyl, audioPlayer);
        buttons.addButtons();
        add(buttons);

        add(Box.createRigidArea(new Dimension(0, 15)));

        menuPanel = new MenuPanel();
        menuPanel.addButtons();
        Dimension menuDimension = new Dimension(270,60);
        menuPanel.setAlignmentX(CENTER_ALIGNMENT);
        menuPanel.setSize(menuDimension);
        menuPanel.setMaximumSize(menuDimension);
        menuPanel.setMinimumSize(menuDimension);
        menuPanel.setPreferredSize(menuDimension);
        add(menuPanel);

    }

    /**
     * updates UI with every song
     * @param song currently playing song
     */
    public void updateSong(Song song) {
        songTitle.setText(song.getSongTitle());
        songArtist.setText(song.getSongArtist());
    }

    public void defaultGUI() {
        vinyl = new Vinyl("/assets/defaultVinyl.png");
        Dimension vinylDimension = new Dimension(400,400);
        vinyl.setSize(vinylDimension);
        vinyl.setMaximumSize(vinylDimension);
        vinyl.setMinimumSize(vinylDimension);
        vinyl.setPreferredSize(vinylDimension);
        vinyl.setAlignmentX(Component.CENTER_ALIGNMENT);
        vinyl.startSpinning();
        add(vinyl);

        Dimension songTitleDimension = new Dimension(280,35);
        songTitle.setSize(songTitleDimension);
        songTitle.setMaximumSize(songTitleDimension);
        songTitle.setMinimumSize(songTitleDimension);
        songTitle.setPreferredSize(songTitleDimension);
        songTitle.setFont(DataLoading.loadFont("/fonts/Minecraftia-Regular.ttf",30));
        songTitle.setForeground(new Color(0, 0, 0));
        songTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(songTitle);

        songArtist.setFont(DataLoading.loadFont("/fonts/Minecraftia-Regular.ttf",20));
        songArtist.setForeground(new Color(0, 0, 0));
        songArtist.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(songArtist);

        playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setSize(300,40);
        Dimension sliderDimension = new Dimension(300,40);
        playbackSlider.setMaximumSize(sliderDimension);
        playbackSlider.setMaximumSize(sliderDimension);
        playbackSlider.setPreferredSize(sliderDimension);
        playbackSlider.setUI(new SliderUI(playbackSlider, new Color(138,72,72)));
        playbackSlider.setOpaque(false);
        playbackSlider.setBackground(null);
        playbackSlider.setSnapToTicks(false);
        playbackSlider.setFocusable(false);
        playbackSlider.setForeground(Color.BLACK);
        playbackSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(playbackSlider);

        buttons = new Buttons(vinyl, audioPlayer);
        buttons.addButtons();
        add(buttons);

        add(Box.createRigidArea(new Dimension(0, 15)));

        menuPanel = new MenuPanel();
        menuPanel.addButtons();
        Dimension menuDimension = new Dimension(270,60);
        menuPanel.setAlignmentX(CENTER_ALIGNMENT);
        menuPanel.setSize(menuDimension);
        menuPanel.setMaximumSize(menuDimension);
        menuPanel.setMinimumSize(menuDimension);
        menuPanel.setPreferredSize(menuDimension);
        add(menuPanel);
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
}

