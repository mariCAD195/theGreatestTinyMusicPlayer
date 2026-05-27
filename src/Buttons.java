import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * manages the music player control buttons
 * @author mari
 */
public class Buttons extends JPanel{

    private JButton previousButton;
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private DataLoading dataLoading;
    private AudioPlayer audioPlayer;
    private Vinyl vinyl;

    public Buttons(Vinyl vinyl, AudioPlayer audioPlayer){
        super();
        dataLoading = new DataLoading();
        this.vinyl = vinyl;
        this.audioPlayer = audioPlayer;
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
        playButton.setVisible(false);
        playButton.setOpaque(false);
        playButton.setBackground(null);
        playButton.setFocusPainted(false);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        add(playButton);

        pauseButton = new JButton(dataLoading.loadAssets("res/assets/pauseButton.png", 50, 50));
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
                audioPlayer.pauseSong();
                vinyl.stopSpinning();
                PLAYonPauseOff();
            }
        });

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    audioPlayer.resumeSong();
                    vinyl.startSpinning();
                    PAUSEonPlayOff();
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
