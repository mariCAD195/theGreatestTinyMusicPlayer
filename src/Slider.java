import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class Slider extends BasicSliderUI {

    private int pixelSize;
    private int trackHeight;
    private int thummbSize;
    private ImageIcon sliderThumb;
    private DataLoading dataLoading = new DataLoading();

    private final Color trackColor;
    private final Color progressColor = new Color(0,0,0);

    public Slider(JSlider b, Color color) {
        super(b);
        this.pixelSize = 4;
        this.thummbSize = pixelSize * 6;
        this.trackHeight = pixelSize * 3;
        this.trackColor = color;
        this.sliderThumb = dataLoading.loadAssets("res/assets/sliderThumb.png",20,20);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        int x = trackRect.x;
        int width = trackRect.width;
        int y = trackRect.y + trackRect.height / 2 - trackHeight / 2;

        // --- Base Track ---
        g2.setColor(trackColor);

        g2.fillRect(x + pixelSize, y, width - pixelSize * 2, trackHeight);
        g2.fillRect(x, y + pixelSize, width, trackHeight - pixelSize * 2);

        // --- Progress ---
        int thumbCenter = thumbRect.x + thummbSize / 2;
        int progressWidth = Math.min(thumbCenter - x, width);

        if (progressWidth > 0) {
            g2.setColor(progressColor);

            if (progressWidth > pixelSize) {
                g2.fillRect(x + pixelSize, y, progressWidth - pixelSize, trackHeight);
                g2.fillRect(x, y + pixelSize, progressWidth, trackHeight - pixelSize * 2);
            }
        }

        // --- Highlight (top stripe) ---
        g2.setColor(new Color(255, 255, 255, 40));
        g2.fillRect(x + pixelSize, y, width - pixelSize * 2, pixelSize);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        g2.drawImage(
                sliderThumb.getImage(),
                thumbRect.x,
                thumbRect.y,
                null
        );
    }
}
