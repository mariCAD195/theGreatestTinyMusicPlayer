import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private PlayerGUI playerGUI;
    private Background background;
    private CardLayout cardLayout;
    private Library library;
    private Queue queue;
    private Customization customization;
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

        library = new Library(cardLayout,cards);
        cards.add(library,"library");

        queue = new Queue(cardLayout,cards);
        cards.add(queue,"queue");

        customization = new Customization(cardLayout,cards);
        cards.add(customization,"customization");

        ImageIcon icon = DataLoading.loadAssets("res/assets/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        cardLayout.show(cards,"library");

        addMusicPlayer();

        add(cards);
    }

    public void addMusicPlayer(){
        try {
            playerGUI = new PlayerGUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        playerGUI.temporaryGUI(cardLayout, cards);
        cards.add(playerGUI,"player");
    }
}
