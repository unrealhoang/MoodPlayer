package vn.silverlining.moodplayer;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import vn.silverlining.moodplayer.model.Song;

public class MoodPickerActivity extends Activity implements View.OnClickListener {
    int currentMood;
    int[] moodIds = new int[]{
            R.id.mood_picker_btn_mood_1,
            R.id.mood_picker_btn_mood_2,
            R.id.mood_picker_btn_mood_3,
            R.id.mood_picker_btn_mood_4,
            R.id.mood_picker_btn_mood_5,
            R.id.mood_picker_btn_mood_6
    };

    ImageButton[] moodBtns = new ImageButton[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_picker);

        for (int i = 0; i < 6; i++) {
            moodBtns[i] = (ImageButton)findViewById(moodIds[i]);
            moodBtns[i].setOnClickListener(this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < 6; i++) {
           if (view == moodBtns[i]) {
               currentMood = i;
               ApplicationState.getInstance().setWantToMood(i);

               startActivity(new Intent(this, MediaPlayerActivity.class));
           }
        }
    }
}
