import javax.swing.*;

public class Vinyl extends JLabel{

    private String path;
    private JLabel vinylRecord;

    public Vinyl(String path) {
        this.path = path;
        this.vinylRecord = new JLabel(DataLoading.loadAssets(path,400,400));
    }

    public String getPath() {
        return path;
    }

    public JLabel getVinylRecord() {
        vinylRecord.setBounds(0, 0, 400, 400);
        return vinylRecord;
    }
}
