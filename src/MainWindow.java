import javax.swing.*;
import java.awt.*;

/**
 * main JFrame storing all panels
 * @author mari
 */
public class MainWindow extends JFrame {

    private PlayerGUI playerGUI;
    private Background background;
    private CardLayout cardLayout;
    private Library library;
    private Queue queue;
    private Customization customization;
    private JPanel cards;

    /**
     * loads the app and all panels
     */
    public MainWindow(){
        super("well hello there");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,700);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setOpaque(false);

        library = new Library(cardLayout,cards);
        cards.add(library,"library");

        try {
            playerGUI = new PlayerGUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        cards.add(playerGUI,"player");

        queue = new Queue(cardLayout,cards,playerGUI);
        cards.add(queue,"queue");

        customization = new Customization(cardLayout,cards,queue.getAudioPlayer());
        cards.add(customization,"customization");

        playerGUI.defaultGUI(cardLayout,cards);
        DataLoading.loadTheme(customization.getThemeManager());

        ImageIcon icon = DataLoading.loadAssets("res/assets/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        cardLayout.show(cards,"library");

        add(cards);
    }

}
