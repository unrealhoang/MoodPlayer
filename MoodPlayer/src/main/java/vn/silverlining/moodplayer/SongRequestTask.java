package vn.silverlining.moodplayer;

import android.media.MediaPlayer;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.silverlining.moodplayer.model.Song;


/**
 * Created by unreal on 6/26/13.
 */
class SongRequestTask extends AsyncTask<String, String, List<Song>> {
    MediaController mediaController;

    public SongRequestTask(MediaController mediaController) {
        this.mediaController = mediaController;
    }

    @Override
    protected List<Song> doInBackground(String... category) {
        String endPoint = "http://10.0.2.2:3000/songs/category/";
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        List<Song> result = null;
        try {
            response = httpclient.execute(new HttpGet(endPoint + category[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
                JSONArray songs = new JSONArray(responseString);
                result = new ArrayList<Song>();

                for (int i = 0; i < songs.length(); i++) {
                    result.add(Song.fromJson(songs.getJSONObject(i)));
                }
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
            e.printStackTrace();
        } catch (IOException e) {
            //TODO Handle problems..
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Song> result) {
        super.onPostExecute(result);
        //Do anything with response..
        mediaController.setPlayingList(result);
        mediaController.reload();
    }
}
