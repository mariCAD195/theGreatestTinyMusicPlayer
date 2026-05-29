import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreatePlaylist extends JDialog {

    private JTextField playlistName;

    public CreatePlaylist(Playlist playlist,CardLayout cardLayout,JPanel cards) {
        setSize(300, 200);
        setLayout(new BorderLayout());
        setTitle("Create Playlist");
        setLocationRelativeTo(null);
        setResizable(false);
        playlistName = new JTextField("playlist title");
        add(playlistName,BorderLayout.CENTER);

        enter(playlist,cardLayout,cards);
        setVisible(true);
    }
    public void enter(Playlist playlist,CardLayout cardLayout,JPanel cards) {
        playlistName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = playlistName.getText();
                    if (!text.isEmpty()){
                        playlist.setName(text);
                        playlistName.setText("");
                        cardLayout.show(cards,"queue");
                        dispose();
                    }
                }
            }
        });
    }
}
