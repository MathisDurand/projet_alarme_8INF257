package uqac.dim.projet_alarme_8inf257;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class ResultActivity extends Activity {
    MediaPlayer mediaPlayer;
    private int minigameId;
    private int ringtoneId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desactiver);

        minigameId = getIntent().getIntExtra("minigameID",0);
        ringtoneId = getIntent().getIntExtra("ringtoneID", 0);


        Button dAlarme = (Button) findViewById(R.id.desactiverAlarme);
        dAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desactiver();
            }
        });
    }
    public void desactiver(){
        Log.v("DIM", "Playing minigame : " + minigameId);
        Intent i;
        switch(minigameId){
            case 1:
                i = new Intent(this, MiniJeuxCalcul.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(this, MiniJeuxCliqueActivity.class);
                startActivity(i);
                break;
            default:
                CommonMyMediaPlayer.player.stopMusic();
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
        }
    }

    public void stopMusic(){
        //((MainActivity) this.getParent()).getMmp().stopMusic();
    }
}
