import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class LoadingScreen extends JFrame {

    private JButton start;
    private JSlider loadingBar;

    public LoadingScreen() {
        super("well hello there");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(0,0));

        ImageIcon icon = DataLoading.loadAssets("res/assets/heartIcon.png",60,60);
        setIconImage(icon.getImage());

        loadingBar = new JSlider(JSlider.HORIZONTAL,0,100,0);
        loadingBar.setPaintTicks(true);
        loadingBar.setPaintLabels(true);
        add(loadingBar,BorderLayout.NORTH);

        start = new JButton("Let's Play");
        add(start,BorderLayout.CENTER);
        start.setVisible(false);
    }

    public void loadingBar(){
        Random rd = new Random();

        for (int i = 0; i < loadingBar.getMaximum();) {
            try {
                Thread.sleep(rd.nextInt(1,500));
                loadingBar.setValue(loadingBar.getValue()+rd.nextInt(10,50));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i=loadingBar.getValue();
        }

        button();
    }

    public void button(){

        start.setVisible(true);
        start.addActionListener(e -> {
            this.dispose();
            new MainWindow().setVisible(true);
        });
    }
}
