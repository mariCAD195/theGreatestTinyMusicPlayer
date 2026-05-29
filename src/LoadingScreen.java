import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class LoadingScreen extends JFrame {

    private JButton start;
    private JSlider loadingBar;
    private JPanel panel;

    public LoadingScreen() {
        super("well hello there");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        ImageIcon icon = DataLoading.loadAssets("res/assets/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        panel = new JPanel();
        panel();
        add(panel);
    }

    public void panel(){
        panel.setBackground(new Color(221, 162, 163));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        add(Box.createRigidArea(new Dimension(0, 15)));

        loadingBar = new JSlider(JSlider.HORIZONTAL,0,100,0);
        Dimension loadingBarDimensions = new Dimension(250,40);
        loadingBar.setMaximumSize(loadingBarDimensions);
        loadingBar.setMinimumSize(loadingBarDimensions);
        loadingBar.setPreferredSize(loadingBarDimensions);
        loadingBar.setPaintTicks(true);
        loadingBar.setPaintLabels(true);
        loadingBar.setUI(new SliderUI(loadingBar, new Color(118, 52, 96)));
        loadingBar.setOpaque(false);
        loadingBar.setBackground(null);
        loadingBar.setSnapToTicks(false);
        loadingBar.setFocusable(false);
        loadingBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(loadingBar);

        start = new JButton("PLAY");
        panel.add(start);
        start.setVisible(false);
    }

    public void loadingBar(){
        Random rd = new Random();

        for (int i = 0; i < loadingBar.getMaximum();) {
            try {
                Thread.sleep(rd.nextInt(499) + 1);
                loadingBar.setValue(loadingBar.getValue()+rd.nextInt(40) + 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i=loadingBar.getValue();
        }

        button();
    }

    public void button(){
        start.setBackground(null);
        start.setOpaque(false);
        start.setFocusPainted(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);

        start.setFont(DataLoading.loadFont("/fonts/Daydream DEMO.otf",40));
        start.setForeground(Color.black);

        start.setVisible(true);
        start.addActionListener(e -> {
            this.dispose();
            new MainWindow().setVisible(true);
        });
    }
}
