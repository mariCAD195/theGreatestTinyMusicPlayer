import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DataLoading {

    /**
     * loads images into image icons
     * @param path absolute path to the loaded image
     * @param width scale image to this width
     * @param height scale image to this height
     * @return new image icon scaled properly
     */
    public ImageIcon loadAssets(String path, int width, int height){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("couldn't load this: "+path);
        }
        return new ImageIcon(image.getScaledInstance(width,height, BufferedImage.SCALE_SMOOTH));
    }

    /**
     * loads custom fonts
     * @param path path to the loaded font
     * @param size font size we want to use
     * @return new custom font
     */
    public Font loadFont(String path, float size){
        Font font = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            font = Font.createFont(Font.TRUETYPE_FONT,bis).deriveFont(size);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("couldn't load this: "+path);
        }
        return font;
    }

    public AudioInputStream loadSong(String path){
        InputStream inputStream = getClass().getResourceAsStream(path);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return audioInputStream;
    }

}
