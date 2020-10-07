package fr.isep.ii3510.lolkanyewest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Debug message", "OnCreate");
        if (mediaPlayer == null) {
            int trackId = getResources().getIdentifier(
                    "audio", "raw", getPackageName()
            );
            mediaPlayer = MediaPlayer.create(this, trackId);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug message", "OnResume");
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            mediaPlayer.seekTo(currentPosition);
            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug message", "OnPause");
        if (mediaPlayer.isPlaying()) {
            currentPosition = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Debug message", "OnSaveInstanceState");
        outState.putInt("POSITION", currentPosition);
    }

    @Override
    protected void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("Debug message", "OnRestoreInstanceState");
        currentPosition = savedInstanceState.getInt("POSITION", 0);
    }
}