import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ThemeManager extends JFrame{

    private HashMap<String,Theme> themes;
    private ArrayList<JButton> options;
    private Theme selected;
    private AudioPlayer audioPlayer;

    public ThemeManager(AudioPlayer audioPlayer) {
        super("well hello there");
        setSize(300,300);
        setLocationRelativeTo(null);
        setResizable(false);

        this.audioPlayer = audioPlayer;

        themes = new HashMap<>();
        Theme testTheme = new Theme("test");
        themes.put("test",testTheme);

        themes.put("test2",new Theme("test2"));

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

    public void changeTheme(){
        audioPlayer.getCurrentSong().setSongsTheme(selected);
    }

    public Theme getSelected() {
        return selected;
    }
}
