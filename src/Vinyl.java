import javax.swing.*;

public class Vinyl extends JLabel{

    private String path;
    private JLabel vinyl;

    public Vinyl(String path) {
        this.path = path;
        this.vinyl = new JLabel(DataLoading.loadAssets(path,300,300));
        this.setBounds(0, 0, 400, 400);
    }
}
