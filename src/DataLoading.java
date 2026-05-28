import com.google.gson.Gson;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * loads app data and assets
 * @author mari
 */
public class DataLoading {

    /**
     * loads images into image icons
     * @param path absolute path to the loaded image
     * @param width scale image to this width
     * @param height scale image to this height
     * @return new image icon scaled properly
     */
    public static ImageIcon loadAssets(String path, int width, int height){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("couldn't load this: "+path);
        }
        return new ImageIcon(image.getScaledInstance(width,height, BufferedImage.SCALE_SMOOTH));
    }

    /**
     * loads an image as a bufferedImage
     * @param path image file path
     * @return loaded image
     */
    public static BufferedImage loadBufferedImage(String path) {
        try {
            return ImageIO.read(DataLoading.class.getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * loads custom fonts
     * @param path path to the loaded font
     * @param size font size we want to use
     * @return new custom font
     */
    public static Font loadFont(String path, float size){
        Font font = null;
        try {
            InputStream inputStream = DataLoading.class.getResourceAsStream(path);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            font = Font.createFont(Font.TRUETYPE_FONT,bis).deriveFont(size);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("couldn't load this: "+path);
        }
        return font;
    }

    /**
     * loads music files
     * @param path music file path
     * @return loaded file
     */
    public static AudioInputStream loadSong(String path){
        InputStream inputStream = DataLoading.class.getResourceAsStream(path);
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

    public static Theme loadTheme() {
        Gson gson = new Gson();

        try (InputStream is = new FileInputStream("/assets/themes.json")) {
            if (is == null) {
                throw new IllegalStateException("not found");
            }
            return gson.fromJson(
                    new InputStreamReader(is, StandardCharsets.UTF_8),
                    Theme.class
            );
        } catch (Exception e) {
            throw new RuntimeException("error" + e.getMessage());
        }

    }

}
