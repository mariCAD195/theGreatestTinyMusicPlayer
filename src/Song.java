import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Song {

    private String songTitle;
    private String songArtist;
    private String songLength;
    private String filePath;
    private HashMap<String,String> songsAssets;
    private String backgroundFilePath;
    private String vinylFilePath;
    private String fontFilePath;
    private Color progressBarColor;

    public Song(String filePath,Color color) {
        this.filePath = filePath;
        try {
            AudioFile audioFile = AudioFileIO.read(new File(filePath));
            Tag tag = audioFile.getTag();
            if (tag != null) {
                songTitle = tag.getFirst(FieldKey.TITLE);
                songArtist = tag.getFirst(FieldKey.ARTIST);
            }else{
                songTitle = "i dont know sorry :(";
                songArtist = "anonymous i guess...";
            }
        } catch (CannotReadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TagException e) {
            throw new RuntimeException(e);
        } catch (ReadOnlyFileException e) {
            throw new RuntimeException(e);
        } catch (InvalidAudioFrameException e) {
            throw new RuntimeException(e);
        }

        this.songsAssets = new HashMap<>();
        this.progressBarColor = color;
    }

    public void setAssetPaths(String backgroundFilePath, String vinylFilePath, String fontFilePath) {
        this.backgroundFilePath = backgroundFilePath;
        this.vinylFilePath = vinylFilePath;
        this.fontFilePath = fontFilePath;
    }

    public String songsBackgroundImage() {
        songsAssets.put("backgroundImage",backgroundFilePath);
        return songsAssets.get("backgroundImage");
    }
    public String songsVinyl() {
        songsAssets.put("vinyl",vinylFilePath);
        return songsAssets.get("vinyl");
    }
    public String songsFont() {
        songsAssets.put("font",fontFilePath);
        return songsAssets.get("font");
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public String getSongLength() {
        return songLength;
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<String, String> getSongsAssets() {
        return songsAssets;
    }

    public String getBackgroundFilePath() {
        return backgroundFilePath;
    }

    public String getVinylFilePath() {
        return vinylFilePath;
    }

    public String getFontFilePath() {
        return fontFilePath;
    }

    public Color getProgressBarColor() {
        return progressBarColor;
    }
}
