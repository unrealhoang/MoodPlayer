package vn.silverlining.moodplayer.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by unreal on 6/25/13.
 */
public class Song {
    private int id;
    private String albumName;
    private String artist;
    private String albumImageUrl;
    private String name;
    private String url;

    public Song(int id, String albumName, String artist, String albumImageUrl, String name, String url) {
        this.id = id;
        this.albumName = albumName;
        this.artist = artist;
        this.albumImageUrl = albumImageUrl;
        this.name = name;
        this.url = url;
    }

    public static Song fromJson(JSONObject songJson) {
        int id;
        String albumName, artist, albumImageUrl, name, url;
        Song result = null;

        try {
            albumName = songJson.getString("album");
            artist = songJson.getString("artist");
            albumImageUrl = songJson.getString("album_image");
            name = songJson.getString("name");
            id = songJson.getInt("id");
            url = songJson.getString("url");

            result = new Song(id, albumName, artist, albumImageUrl, name, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumImageUrl() {
        return albumImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
