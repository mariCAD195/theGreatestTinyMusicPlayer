import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuPanel extends JPanel {

    private JButton allPlaylists;
    private JButton queue;
    private JButton customization;
    private DataLoading dataLoading;
    private Background background;
    private BufferedImage backgroundImage;

    public MenuPanel() {
        super();
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        setOpaque(false);
        dataLoading = new DataLoading();

        backgroundImage = dataLoading.loadBufferedImage("/assets/menuPanel.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void addButtons(){
        add(Box.createRigidArea(new Dimension(15, 0)));

        allPlaylists = new JButton(dataLoading.loadAssets("res/assets/playlists.png", 40, 40));
        allPlaylists.setOpaque(true);
        allPlaylists.setBackground(null);
        allPlaylists.setFocusPainted(false);
        allPlaylists.setBorderPainted(false);
        allPlaylists.setContentAreaFilled(false);
        add(allPlaylists);

        add(Box.createRigidArea(new Dimension(10, 0)));

        queue = new JButton(dataLoading.loadAssets("res/assets/queue.png", 40, 40));
        queue.setOpaque(true);
        queue.setBackground(null);
        queue.setFocusPainted(false);
        queue.setBorderPainted(false);
        queue.setContentAreaFilled(false);
        add(queue);

        add(Box.createRigidArea(new Dimension(10, 0)));

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
