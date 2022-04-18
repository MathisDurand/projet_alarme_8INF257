package uqac.dim.projet_alarme_8inf257;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyMediaPlayer extends AsyncTask<Void, Void, Void> {
    private static final String ACTION_PLAY = "com.example.action.PLAY";
    private MediaPlayer mediaPlayer = null;
    private int ringtoneID = 0;
    private Context ctx = null;

    public MyMediaPlayer(int ringtoneId, Context context){
        super();
        this.ringtoneID = ringtoneId;
        this.ctx = context;
    }



    public void setMusic() {
        switch (ringtoneID) {
            case 1:
                mediaPlayer = MediaPlayer.create(ctx, R.raw.ringtone1);
                mediaPlayer.start();
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(ctx, R.raw.ringtone2);
                mediaPlayer.start();
                break;
            /*case X:
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtoneX);
                break;*/
            default:
                break;
        }

        mediaPlayer.setLooping(true); // Set looping
        //mediaPlayer.setVolume(1.0f, 1.0f);
    }

    public void stopMusic() {
        mediaPlayer.stop();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        setMusic();
        return null;
    }
}

