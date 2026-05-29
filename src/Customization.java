import javax.swing.*;
import java.awt.*;

/**
 * panel for managing customization and other settings
 * @author mari
 */
public class Customization extends JPanel {

    private ThemeManager themeManager;
    private AudioPlayer audioPlayer;
    private JButton chooseTheme;
    private JButton back;

    public Customization(AudioPlayer audioPlayer,PlayerGUI playerGUI) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(true);

        this.audioPlayer = audioPlayer;
        themeManager = new ThemeManager(audioPlayer,playerGUI);

        setBackground(new Color(154, 239, 27));

        load();
    }

    /**
     * loads used UI components
     * @param cardLayout global card layout with other panels
     * @param cards all panels stored in global card layout
     */
    public void load(){
        chooseTheme = new JButton("choose theme");
        chooseTheme.addActionListener(e -> {
            themeManager.setVisible(true);
        });

        add(chooseTheme);

        back = new JButton("Back");

        add(back);
    }

    public ThemeManager getThemeManager() {
        return themeManager;
    }

    public JButton getBack() {
        return back;
    }
}
