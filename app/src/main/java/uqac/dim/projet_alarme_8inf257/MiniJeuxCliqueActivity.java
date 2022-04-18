package uqac.dim.projet_alarme_8inf257;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MiniJeuxCliqueActivity extends Activity {
    private final int NOMBRE = 100;
    private int cliqueRestant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minijeuxclique);

       Button soustraire = findViewById(R.id.soustraire);
        cliqueRestant = NOMBRE;
        soustraire.setText(String.valueOf(cliqueRestant));

        soustraire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementer();
            }
        });
    }

    private void decrementer() {
        cliqueRestant --;
        if (cliqueRestant > 0) {
            ((Button) findViewById(R.id.soustraire)).setText(String.valueOf(cliqueRestant));
        }
        else {
            CommonMyMediaPlayer.player.stopMusic();
            Intent intent = new Intent(MiniJeuxCliqueActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
