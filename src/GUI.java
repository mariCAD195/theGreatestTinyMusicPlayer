import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GUI extends JFrame {

    private MusicPlayer musicPlayer;
    private JLayeredPane layeredPane;
    private JLabel backgroundImage;
    private JLabel vinyl;
    private JPanel buttonPanel;
    private JLabel songTitle;
    private JLabel songArtist;
    private JSlider playbackSlider;
    private JButton playButton;
    private JButton pauseButton;

    /**
     * JFrame setup
     */
    public GUI() {

        //JFrame configuration
        super("well hello there");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,600);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        //set windowIcon
        ImageIcon icon = loadImage("assets/art/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        musicPlayer = new MusicPlayer();
        defaultGUI();
        addButtons();

    }

    /**
     *default music player GUI when there's nothing playing
     */
    public void defaultGUI(){
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,getWidth(),getHeight());

        vinyl = new JLabel(loadImage("assets/art/defaultVinyl.png",300,300));
        vinyl.setBounds(0,0,400,350);
        layeredPane.add(vinyl,Integer.valueOf(1));

        songTitle = new JLabel();
        songTitle.setBounds(0,330,getWidth()-10,50);
        songTitle.setText("Song Title");
        songTitle.setFont(loadFont("/fonts/Pixellari.ttf",30));
        songTitle.setForeground(Color.BLACK);
        songTitle.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(songTitle,Integer.valueOf(2));

        songArtist = new JLabel();
        songArtist.setBounds(0,370,getWidth()-10,50);
        songArtist.setText("Song Artist");
        songArtist.setFont(loadFont("/fonts/Pixellari.ttf",20));
        songArtist.setForeground(Color.BLACK);
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(songArtist,Integer.valueOf(3));

        playbackSlider = new JSlider(JSlider.HORIZONTAL,0,100,0);
        playbackSlider.setBounds(getWidth()/2 - 300/2, 420, 300, 40);
        playbackSlider.setBackground(null);
        playbackSlider.setSnapToTicks(false);
        playbackSlider.setFocusable(false);
        playbackSlider.setForeground(Color.BLACK);
        layeredPane.add(playbackSlider,Integer.valueOf(4));

        add(layeredPane);
    }

    /**
     * adds buttons and their functions
     */
    public void addButtons(){

        //button panel where all the buttons live
        buttonPanel = new JPanel();
        buttonPanel.setBackground(null);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0,470,390,80);

        //play button - loads the playlist and plays songs, updates gui to song specific assets
        playButton = new JButton(loadImage("assets/art/defaultPlayButton.png",50,50));
        playButton.setOpaque(false);
        playButton.setBackground(null);
        playButton.setFocusPainted(false);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        buttonPanel.add(playButton);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                musicPlayer.loadPlaylist();
                for (int i = 0; i < musicPlayer.getPlaylist().getTonikuvPlaylist().size(); i++) {
                    musicPlayer.loadSong(musicPlayer.getPlaylist(), musicPlayer.getPlaylist().getSongByNumber(i));
                    musicPlayer.playSong();
                    updateGUI(musicPlayer.getPlaylist(), musicPlayer.getPlaylist().getSongByNumber(i));
                }
            }
        });

        //pause button - stops the music
        pauseButton = new JButton(loadImage("assets/art/defaultPauseButton.png",50,50));
        pauseButton.setVisible(false);
        pauseButton.setOpaque(false);
        pauseButton.setBackground(null);
        pauseButton.setFocusPainted(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        buttonPanel.add(pauseButton);

        add(buttonPanel);
    }

    public void PLAYonPauseOff(){
        pauseButton.setVisible(false);
        pauseButton.setEnabled(false);

        playButton.setVisible(true);
        playButton.setEnabled(true);
    }

    public void PAUSEoffPlayOn(){
        playButton.setVisible(false);
        playButton.setEnabled(false);

        pauseButton.setVisible(true);
        pauseButton.setEnabled(true);
    }

    /**
     * updates GUI to assets specific to a song that is currently playing
     * @param playlist playlist with our songs
     * @param name keyword of the song specific assets
     */
    public void updateGUI(Playlist playlist, String name) {
        if(layeredPane != null){
            layeredPane.removeAll();
            remove(layeredPane);
        }

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,getWidth(),getHeight());

        //adds the music player background for the currently playing song
        if (playlist.getTonikuvPlaylist().get(name).songsBackgroundImage() != null) {
            backgroundImage = new JLabel(loadImage(playlist.getTonikuvPlaylist().get(name).songsBackgroundImage(),400,600));
            backgroundImage.setBounds(0,0,getWidth(),getHeight());
            layeredPane.add(backgroundImage,Integer.valueOf(0));
        }

        //adds the vinyl for the currently playing song
        if (playlist.getTonikuvPlaylist().get(name).songsVinyl() != null) {
            vinyl = new JLabel(loadImage(playlist.getTonikuvPlaylist().get(name).songsVinyl(),300,300));
            vinyl.setBounds(0,0,400,350);
            layeredPane.add(vinyl,Integer.valueOf(1));
        }

        //updates to currently playing song's title, artist and font
        if (playlist.getTonikuvPlaylist().get(name).songsFont() != null) {
            songTitle.setText(playlist.getTonikuvPlaylist().get(name).getSongTitle());
            songTitle.setFont(loadFont(playlist.getTonikuvPlaylist().get(name).songsFont(),30));
            songTitle.setForeground(new Color(0,0,0));
            layeredPane.add(songTitle,Integer.valueOf(2));

            songArtist.setText(playlist.getTonikuvPlaylist().get(name).getSongArtist());
            songArtist.setFont(loadFont(playlist.getTonikuvPlaylist().get(name).songsFont(),20));
            songArtist.setForeground(new Color(0,0,0));
            layeredPane.add(songArtist,Integer.valueOf(3));
        }

        add(layeredPane);
        layeredPane.revalidate();
        layeredPane.repaint();
        repaint();
        revalidate();

    }

    /**
     * loads images into image icons
     * @param path absolute path to the loaded image
     * @param width scale image to this width
     * @param height scale image to this height
     * @return new image icon scaled properly
     */
    public ImageIcon loadImage(String path, int width, int height){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("Error loading image " + path);
        }
        return new ImageIcon(image.getScaledInstance(width,height, BufferedImage.SCALE_SMOOTH));
    }

    /**
     * loads custom fonts
     * @param path path to the loaded font
     * @param size font size we want to use
     * @return new custom font
     */
    public Font loadFont(String path, float size){
        Font font = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            font = Font.createFont(Font.TRUETYPE_FONT,bis).deriveFont(size);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error loading font " + path);
        }
        return font;
    }
}

