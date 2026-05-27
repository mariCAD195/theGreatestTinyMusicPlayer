import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * allows JPanels to have a custom image as a background
 * @author matej pospisil, edited by mari
 */
public class Background extends JPanel {
    private Image img;

    public Background(String imgFile) throws Exception {
        super();
        loadImage(imgFile);
    }

    private void loadImage(String imgFile) throws Exception {
        URL imageURL = getClass().getResource(imgFile);

        if(imageURL == null){
            throw new Exception("The image "+ imgFile +" was not found");
        }

        this.img = new ImageIcon(imageURL).getImage();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

}
