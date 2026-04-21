import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private DataLoading dataLoading;
    private PlayerGUI playerGUI;

    public MainWindow(){
        super("well hello there");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,700);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        dataLoading = new DataLoading();
        playerGUI = new PlayerGUI();

        ImageIcon icon = dataLoading.loadAssets("res/assets/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        addMusicPlayer();
    }

    public void addMusicPlayer(){
        playerGUI.tempoaryGUI();
        add(playerGUI);
    }
}
