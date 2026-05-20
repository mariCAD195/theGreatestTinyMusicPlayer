import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private PlayerGUI playerGUI;
    private Background background;
    private CardLayout cardLayout;
    private Library library;
    private JPanel cards;

    public MainWindow(){
        super("well hello there");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,700);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setOpaque(false);

        try {
            playerGUI = new PlayerGUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        library = new Library();
        cards.add(library,"library");

        ImageIcon icon = DataLoading.loadAssets("res/assets/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        addMusicPlayer();

        cardLayout.show(cards,"player");
        cardLayout.show(cards,"library");
        add(cards);
    }

    public void addMusicPlayer(){
        playerGUI.temporaryGUI();
        cards.add(playerGUI,"player");
    }
}
