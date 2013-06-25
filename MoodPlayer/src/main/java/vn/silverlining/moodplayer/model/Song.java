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

    public Song(int id, String albumName, String artist, String albumImageUrl, String name) {
        this.id = id;
        this.albumName = albumName;
        this.artist = artist;
        this.albumImageUrl = albumImageUrl;
        this.name = name;
    }

    public static Song fromJson(JSONObject songJson) {
        int id;
        String albumName, artist, albumImageUrl, name;
        Song result = null;

        try {
            albumName = songJson.getString("album");
            artist = songJson.getString("artist");
            albumImageUrl = songJson.getString("album_image");
            name = songJson.getString("name");
            id = songJson.getInt("id");

            result = new Song(id, albumName, artist, albumImageUrl, name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
