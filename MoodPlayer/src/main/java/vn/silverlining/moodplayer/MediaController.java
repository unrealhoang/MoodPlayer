package vn.silverlining.moodplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.silverlining.moodplayer.model.Song;

/**
 * Created by unreal on 6/26/13.
 */
public class MediaController implements MediaPlayer.OnCompletionListener {
    public interface OnSongChangedListener {
        public void onSongChanged(Song changedTo);
    }

    private MediaPlayer mediaPlayer;
    private List<Song> playingList;
    private int currentPlayingPosition;
    private List<OnSongChangedListener> listeners;

    public MediaController() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnCompletionListener(this);

        currentPlayingPosition = 0;

        listeners = new ArrayList<OnSongChangedListener>();
    }

    public void addOnSongChangedListener(OnSongChangedListener listener) {
        listeners.add(listener);
    }

    public void removeOnSongChangedListener(OnSongChangedListener listener) {
        listeners.remove(listener);
    }

    private void fireOnSongChangedEvent(Song changedTo) {
        for (OnSongChangedListener listener: listeners) {
            listener.onSongChanged(changedTo);
        }
    }

    public List<Song> getPlayingList() {
        return playingList;
    }

    public void setPlayingList(List<Song> playingList) {
        this.playingList = playingList;
    }

    public void playAt(int pos) {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();

        if (playingList.size() == 0)
            throw new RuntimeException("Empty playing list");
        if (pos >= playingList.size())
            pos = 0;

        try {
            mediaPlayer.setDataSource(playingList.get(pos).getUrl());
            mediaPlayer.prepare();
            mediaPlayer.start();

            fireOnSongChangedEvent(playingList.get(pos));

            currentPlayingPosition = pos;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void next() {
        playAt(currentPlayingPosition + 1);
    }

    public void prev() {
        playAt(currentPlayingPosition - 1);
    }

    public void reload() {
        playAt(0);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        next();
    }
}
