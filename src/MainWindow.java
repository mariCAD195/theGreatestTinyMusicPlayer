import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private DataLoading dataLoading;
    private PlayerGUI playerGUI;
    private JPanel mainPanel;
    private Vinyl vinyl;
    private JPanel backgroundPanel;
    private JLabel backgroundImage;

    public MainWindow(){
        super("well hello there");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,700);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setResizable(false);

        dataLoading = new DataLoading();
        playerGUI = new PlayerGUI();

        ImageIcon icon = dataLoading.loadAssets("res/assets/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        //backgroundPanel = new JPanel();
        //add(backgroundPanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        addMusicPlayer();
    }

    public void addMusicPlayer(){
        vinyl = new Vinyl("/assets/deathBedVinyl.png");
        mainPanel.add(vinyl);

        //backgroundImage = new JLabel(dataLoading.loadAssets("res/assets/deathBedBackground.png", 400, 700));
        //backgroundPanel.add(backgroundImage);
        mainPanel.add(playerGUI);

        add(mainPanel);
    }
}
