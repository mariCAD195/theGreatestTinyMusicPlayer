import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel{

    private JButton previousButton;
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private DataLoading dataLoading;
    private MusicPlayer musicPlayer;
    private Vinyl vinyl;

    public Buttons(MusicPlayer musicPlayerImported, Vinyl vinylImported) {
        super();
        dataLoading = new DataLoading();
        musicPlayer = musicPlayerImported;
        vinyl = vinylImported;
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        setOpaque(false);
    }

    public void addButtons() {
        previousButton = new JButton(dataLoading.loadAssets("res/assets/previousButton.png", 50, 50));
        previousButton.setOpaque(true);
        previousButton.setBackground(null);
        previousButton.setFocusPainted(false);
        previousButton.setBorderPainted(false);
        previousButton.setContentAreaFilled(false);
        add(previousButton);

        playButton = new JButton(dataLoading.loadAssets("res/assets/PlayButton.png", 50, 50));
        playButton.setOpaque(false);
        playButton.setBackground(null);
        playButton.setFocusPainted(false);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        add(playButton);

        pauseButton = new JButton(dataLoading.loadAssets("res/assets/pauseButton.png", 50, 50));
        pauseButton.setVisible(false);
        pauseButton.setOpaque(false);
        pauseButton.setBackground(null);
        pauseButton.setFocusPainted(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        add(pauseButton);

        nextButton = new JButton(dataLoading.loadAssets("res/assets/nextButton.png", 50, 50));
        nextButton.setVisible(true);
        nextButton.setOpaque(false);
        nextButton.setBackground(null);
        nextButton.setFocusPainted(false);
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        add(nextButton);

        makeThemDoStuff();
    }

    public void makeThemDoStuff() {
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                musicPlayer.pause();
                vinyl.stopSpinning();
                PLAYonPauseOff();
            }
        });
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!musicPlayer.isPlaying()) {
                    musicPlayer.loadPlaylist();
                    musicPlayer.loadSong(musicPlayer.getPlaylist(), musicPlayer.getPlaylist().getSongByNumber(musicPlayer.getCurrentSongIndex()));
                    musicPlayer.playSong();
                    PAUSEonPlayOff();
                    vinyl.startSpinning();
                } else {
                    musicPlayer.resume();
                    PAUSEonPlayOff();
                    vinyl.startSpinning();
                }
            }
        });

    }

    public void PLAYonPauseOff(){
        pauseButton.setVisible(false);
        pauseButton.setEnabled(false);

        playButton.setVisible(true);
        playButton.setEnabled(true);
    }

    public void PAUSEonPlayOff(){
        playButton.setVisible(false);
        playButton.setEnabled(false);

        pauseButton.setVisible(true);
        pauseButton.setEnabled(true);
    }


}
