package vn.silverlining.moodplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import vn.silverlining.moodplayer.model.Song;

/**
 * Created by unreal on 6/26/13.
 */
public class MediaPlayerActivity extends Activity implements MediaController.OnSongChangedListener, View.OnClickListener {
    SongRequestTask requestTask;
    MediaController mediaController;

    ImageButton btnPrev;
    ImageButton btnNext;
    ImageButton btnLike;
    ImageButton btnBan;

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

        btnPrev = (ImageButton) findViewById(R.id.media_player_btn_prev);
        btnNext = (ImageButton) findViewById(R.id.media_player_btn_next);
        btnLike = (ImageButton) findViewById(R.id.media_player_btn_like);
        btnBan = (ImageButton) findViewById(R.id.media_player_btn_ban);

        btnNext.setOnClickListener(this);
    }

    @Override
    public void onSongChanged(Song changedTo) {
        TextView lblName = (TextView) findViewById(R.id.media_player_lbl_title);
        TextView lblArtist = (TextView) findViewById(R.id.media_player_lbl_artist);
        //TextView lblAlbum = (TextView) findViewById(R.id.media_player_lbl_album);

        lblName.setText(changedTo.getName());
        lblArtist.setText(changedTo.getArtist());
    }

    @Override
    public void onClick(View view) {
        if (view == btnNext) {
            mediaController.next();
        } else if (view == btnPrev) {
            mediaController.prev();
        }
    }
}
