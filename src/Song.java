import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
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
    private Mp3File mp3File;
    private double frameRate;

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
    public void mathPleaseKillMe(String filePath){
        try {
            mp3File = new Mp3File(filePath);
            frameRate = (double) mp3File.getFrameCount() / mp3File.getLengthInMilliseconds();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedTagException e) {
            throw new RuntimeException(e);
        }
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

    public Mp3File getMp3File() {
        return mp3File;
    }

    public double getFrameRate() {
        return frameRate;
    }
}
