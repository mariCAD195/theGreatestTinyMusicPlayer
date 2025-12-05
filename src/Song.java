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

    public Song(String songTitle, String songArtist, String songLength, String filePath) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songLength = songLength;
        this.filePath = filePath;
        this.songsAssets = new HashMap<>();
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
}
