import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private DataLoading dataLoading;
    private PlayerGUI playerGUI;
    private Background background;
    private CardLayout cardLayout;

    public MainWindow(){
        super("well hello there");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,700);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setResizable(false);

        dataLoading = new DataLoading();
        playerGUI = new PlayerGUI();
        try {
            background = new Background("/assets/deathBedBackground.png");
            this.setContentPane(background);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ImageIcon icon = dataLoading.loadAssets("res/assets/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        addMusicPlayer();
    }

    public void addMusicPlayer(){
        playerGUI.temporaryGUI();
        add(playerGUI);
    }
}
