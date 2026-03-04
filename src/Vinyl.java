import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Vinyl extends JPanel{

    private String path;
    private BufferedImage vinylRecord;
    private Timer timer;
    private double spinSpeed;

    public Vinyl(String path) {
        this.path = path;
        this.vinylRecord = DataLoading.loadBufferedImage(path);
        setOpaque(false);
        setPreferredSize(new Dimension(400,400));

        timer = new Timer(16, e -> {
            spinSpeed += 0.005;
            repaint();
        });
    }

    public void startSpinning() {
        timer.start();
    }

    public void stopSpinning() {
        timer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (vinylRecord == null) return;

        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        g2d.translate(width / 2, height / 2);
        g2d.rotate(spinSpeed);
        g2d.drawImage(vinylRecord, -width / 2, -height / 2, width, height, null);

        g2d.dispose();
    }

}
