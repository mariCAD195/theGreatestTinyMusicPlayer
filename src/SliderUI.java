import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

/**
 * manages ui look of the progress bar
 * @author chatGPT, Mari
 */
public class SliderUI extends BasicSliderUI {

    private int pixelSize;
    private int trackHeight;
    private ImageIcon sliderThumb;
    private DataLoading dataLoading;

    private final Color trackColor;
    private final Color progressColor = new Color(0,0,0);

    public SliderUI(JSlider b, Color color) {
        super(b);
        this.dataLoading = new DataLoading();
        this.pixelSize = 4;
        this.trackHeight = pixelSize * 3;
        this.trackColor = color;
        this.sliderThumb = dataLoading.loadAssets("res/assets/sliderThumb.png",20,20);
    }

    /**
     * makes the progress bar appear as pixel art with "rounded" corners
     * <p>
     * changes progress bar color based on progres
     */
    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        g2.setColor(trackColor);
        int x = trackRect.x;
        int width = trackRect.width;
        int y = trackRect.y + trackRect.height / 2 - trackHeight/ 2;

        g2.fillRect(x + pixelSize, y, width - pixelSize * 2, trackHeight);
        g2.fillRect(x, y + pixelSize, width, trackHeight - pixelSize * 2);

        int thumbCenter = thumbRect.x + 20 / 2;
        int progressWidth = Math.min(thumbCenter - x, width);

        if (progressWidth > 0) {
            g2.setColor(progressColor);

            if (progressWidth > pixelSize) {
                g2.fillRect(x + pixelSize, y, progressWidth - pixelSize, trackHeight);
                g2.fillRect(x, y + pixelSize, progressWidth, trackHeight - pixelSize * 2);
            }
        }

        g2.setColor(new Color(255, 255, 255, 40));
        g2.fillRect(x + pixelSize, y, width - pixelSize * 2, pixelSize);
    }

    /**
     * sets the progress bar thumb as an image
     */
    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(sliderThumb.getImage(), thumbRect.x, thumbRect.y, null);
    }
}
