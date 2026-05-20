import javax.swing.*;

public class Library extends JPanel {
    public Library() {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setOpaque(false);

        loadLibrary();
    }

    public void loadLibrary() {
        JButton button = new JButton("Load Library");
    }
}
