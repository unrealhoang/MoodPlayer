package vn.silverlining.moodplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import vn.silverlining.moodplayer.model.Song;

/**
 * Created by unreal on 6/26/13.
 */
public class MediaPlayerActivity extends Activity implements MediaController.OnSongChangedListener {
    SongRequestTask requestTask;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player);
        ApplicationState state = ApplicationState.getInstance();
        mediaController = new MediaController();
        requestTask = new SongRequestTask(mediaController);

        mediaController.addOnSongChangedListener(this);

        if (state.getCurrentMood() != state.getWantToMood()) {
            requestTask.execute(state.getWantToMood() + "");
            state.setCurrentMood(state.getWantToMood());
        }

        ImageButton btnPrev = (ImageButton) findViewById(R.id.media_player_btn_prev);
        ImageButton btnNext = (ImageButton) findViewById(R.id.media_player_btn_next);
        ImageButton btnLike = (ImageButton) findViewById(R.id.media_player_btn_like);
        ImageButton btnBan = (ImageButton) findViewById(R.id.media_player_btn_ban);
    }

    @Override
    public void onSongChanged(Song changedTo) {
        TextView lblName = (TextView) findViewById(R.id.media_player_lbl_title);
        TextView lblArtist = (TextView) findViewById(R.id.media_player_lbl_artist);
        TextView lblAlbum = (TextView) findViewById(R.id.media_player_lbl_album);

        lblName.setText(changedTo.getName());
        lblArtist.setText(changedTo.getArtist());
    }
}
