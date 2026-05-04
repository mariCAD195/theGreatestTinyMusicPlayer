import javax.swing.*;

public class MenuPanel extends JPanel {

    private JButton allPlaylists;
    private JButton queue;
    private JButton customization;
    private DataLoading dataLoading;

    public MenuPanel() {
        super();
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        setOpaque(false);
        dataLoading = new DataLoading();
    }

    public void addButtons(){
        allPlaylists = new JButton(dataLoading.loadAssets("res/assets/playlists.png", 40, 40));
        allPlaylists.setOpaque(true);
        allPlaylists.setBackground(null);
        allPlaylists.setFocusPainted(false);
        allPlaylists.setBorderPainted(false);
        allPlaylists.setContentAreaFilled(false);
        add(allPlaylists);

        queue = new JButton(dataLoading.loadAssets("res/assets/queue.png", 40, 40));
        queue.setOpaque(true);
        queue.setBackground(null);
        queue.setFocusPainted(false);
        queue.setBorderPainted(false);
        queue.setContentAreaFilled(false);
        add(queue);

        customization = new JButton(dataLoading.loadAssets("res/assets/customization.png", 40, 40));
        customization.setOpaque(true);
        customization.setBackground(null);
        customization.setFocusPainted(false);
        customization.setBorderPainted(false);
        customization.setContentAreaFilled(false);
        add(customization);

        makeThemDoStuff();
    }

    public void makeThemDoStuff(){
        allPlaylists.addActionListener(e -> {

        });
        queue.addActionListener(e -> {

        });
        customization.addActionListener(e -> {

        });
    }
}
