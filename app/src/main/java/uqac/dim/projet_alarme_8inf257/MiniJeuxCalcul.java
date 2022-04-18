package uqac.dim.projet_alarme_8inf257;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.TextViewCompat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MiniJeuxCalcul extends Activity {
    private int nombre1 = 0;
    private int nombre2 = 0;
    private  int operateur = 0;
    private int resultat = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minijeuxcalcul);
        commencer();
        Button commencer = (Button) findViewById(R.id.commencer);
        commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valider();
                score();

            }
        });
    }
    public void commencer(){
        TextView calcul = (TextView) findViewById(R.id.CalculTextView);
        Random random = new Random();
        operateur = random.nextInt(3);
        if(operateur == 0){
            Log.v("DIM", "Addition");
            nombre1 = random.nextInt(100);
            nombre2 = random.nextInt(100);
            resultat = nombre1+nombre2;
            calcul.setText(nombre1+" + "+nombre2);
        }if (operateur == 1){
            Log.v("DIM", "Soustraction");
            nombre1 = random.nextInt(100);
            nombre2 = random.nextInt(100);
            if(nombre2>nombre1){
                int changenombre = nombre2;
                nombre2 = nombre1;
                nombre1 = changenombre;
            }
            resultat = nombre1-nombre2;
            calcul.setText(nombre1+" - "+nombre2);
        }if(operateur == 2){
            Log.v("DIM", "Multiplication");
            nombre1 = random.nextInt(10);
            nombre2 = random.nextInt(10);
            resultat = nombre1*nombre2;
            calcul.setText(nombre1+" * "+nombre2);
        }
    }

    public void score(){
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setText(score+"/5");
        if(score>4){CommonMyMediaPlayer.player.stopMusic();}
    }

    public void valider(){
        EditText reponse = (EditText) findViewById(R.id.reponse);
        String numero = reponse.getText().toString();
        String result = Integer.toString(resultat);
        Log.v("DIM", result);
        Log.v("DIM", numero);
        TextView calcul = (TextView) findViewById(R.id.CalculTextView);
        Timer t = new Timer();
        if(result.equals(numero)){
            Log.v("DIM", "Bonne réponse");
            calcul.setBackgroundColor(-16711936);
            TimerTask task = new TimerTask() {
                public void run()
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            calcul.setBackgroundColor(-1);
                            reponse.setText("");
                            commencer();
                            score+=1;
                            score();
                        }
                    });

                }
            };
            t.schedule(task, 1000);
        }else{
            Log.v("DIM", "Mauvaise réponse");
            calcul.setBackgroundColor(-65536);
            TimerTask task = new TimerTask() {
                public void run()
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            calcul.setBackgroundColor(-1);
                            reponse.setText("");
                            commencer();
                        }
                    });

                }
            };
            t.schedule(task, 1000);
        }

    }

}

