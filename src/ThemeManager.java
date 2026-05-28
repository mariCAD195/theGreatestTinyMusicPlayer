import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * manages a JFrame that lets the user change the app theme
 * @author mari
 */
public class ThemeManager extends JFrame{

    private HashMap<String,Theme> themes;
    private Theme selected;
    private AudioPlayer audioPlayer;

    public ThemeManager(AudioPlayer audioPlayer) {
        super("well hello there");
        setSize(300,300);
        setLocationRelativeTo(null);
        setResizable(false);

        this.audioPlayer = audioPlayer;

        themes = new HashMap<>();
        DataLoading.loadTheme(this);

        for(Theme theme : themes.values()){
            JButton option = new JButton(theme.getName());
            option.addActionListener(e -> {
                selected = themes.get(option.getText());
                changeTheme();
                System.out.println(audioPlayer.getCurrentSong().getSongsTheme());
            });
            add(option);
        }

        setLayout(new GridLayout(themes.size(),0));
    }

    /**
     * changes the theme for the currently playing song
     */
    public void changeTheme(){
        audioPlayer.getCurrentSong().setSongsTheme(selected);
    }

    public Theme getSelected() {
        return selected;
    }

    public HashMap<String, Theme> getThemes() {
        return themes;
    }
}
