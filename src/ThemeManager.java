import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ThemeManager extends JFrame{

    private HashMap<String,Theme> themes;
    private ArrayList<JButton> options;
    private JButton option;
    private JLabel title;

    public ThemeManager() {
        super("well hello there");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new GridLayout());

        themes = new HashMap<>();
        Theme testTheme = new Theme("test");
        themes.put("test",testTheme);

        for(Theme theme : themes.values()){
            option = new JButton();
            title = new JLabel(theme.getName());
            option.add(title);

            add(option);
        }

    }
}
