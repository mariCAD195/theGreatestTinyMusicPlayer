import javax.swing.*;
import java.awt.*;

public class Customization extends JPanel {

    private ThemeManager themeManager;
    private AudioPlayer audioPlayer;
    private JButton chooseTheme;

    public Customization(CardLayout cardLayout, JPanel cards,AudioPlayer audioPlayer) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        this.audioPlayer = audioPlayer;

        setBackground(new Color(154, 239, 27));

        load(cardLayout, cards);
    }

    public void load(CardLayout cardLayout, JPanel cards){
        chooseTheme = new JButton("choose theme");
        chooseTheme.addActionListener(e -> {
            themeManager = new ThemeManager(audioPlayer);
            themeManager.setVisible(true);
        });

        add(chooseTheme);

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            cardLayout.show(cards,"player");
        });

        add(back);
    }

    public void changeTheme(){
        audioPlayer.getCurrentSong().setSongsTheme(themeManager.getSelected());
    }
}
