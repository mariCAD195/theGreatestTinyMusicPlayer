import java.awt.*;

/**
 * stores individual asset file paths
 * @author mari
 */
public class Theme {

    private String name;
    private String background;
    private String vinyl;
    private String font;
    private int[] progressBarColor;

    public Theme(String name) {
        this.name = name;
        this.progressBarColor = new int[3];
    }

    public String getName() {
        return name;
    }

    public String getBackground() {
        return background;
    }

    public String getVinyl() {
        return vinyl;
    }

    public String getFont() {
        return font;
    }

    public int getProgressBarColorR() {
        return progressBarColor[0];
    }

    public int getProgressBarColorG() {
        return progressBarColor[1];
    }

    public int getProgressBarColorB() {
        return progressBarColor[2];
    }

    @Override
    public String toString() {
        return "Theme{" +
                "name='" + name + '\'' +
                '}';
    }
}
