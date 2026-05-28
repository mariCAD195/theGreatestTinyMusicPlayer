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
    private Color progressBar;

    public Theme(String name) {
        this.name = name;
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

    public Color getProgressBar() {
        return progressBar;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "name='" + name + '\'' +
                '}';
    }
}
