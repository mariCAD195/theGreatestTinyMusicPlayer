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
    private CreatePlaylist createPlaylist;

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

        library = new Library();
        cards.add(library,"library");

        try {
            playerGUI = new PlayerGUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        cards.add(playerGUI,"player");

        queue = new Queue(cardLayout,cards,playerGUI);
        cards.add(queue,"queue");

        customization = new Customization(queue.getAudioPlayer(),playerGUI);
        cards.add(customization,"customization");

        DataLoading.loadTheme(customization.getThemeManager());
        playerGUI.defaultGUI();

        ImageIcon icon = DataLoading.loadAssets("res/assets/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        cardLayout.show(cards,"library");

        add(cards);
        cardChange();
    }

    public void cardChange(){
        library.getNewPlaylist().addActionListener(e -> {
            Playlist newPlaylist = new Playlist();
            createPlaylist = new CreatePlaylist(newPlaylist,cardLayout,cards);

            library.getPlaylistPanel().addPlaylist(newPlaylist);
            library.getPlaylistPanel().loadPanel();
        });

        customization.getBack().addActionListener(e -> {
            cardLayout.show(cards,"player");
        });

        playerGUI.getMenuPanel().makeThemDoStuff(cardLayout,cards);
    }

}
