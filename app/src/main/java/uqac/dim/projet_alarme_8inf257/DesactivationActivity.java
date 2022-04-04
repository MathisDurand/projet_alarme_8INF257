package uqac.dim.projet_alarme_8inf257;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class DesactivationActivity extends Activity {
    private int selectedMiniGame;

    static final String DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desactivation);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.MiniGameChoice);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int i;
                switch(checkedId){
                    case R.id.MG2:
                        i = 2;
                        Log.v("DIM", "change minigameid to : "+i);
                        break;
                    case R.id.MG1:
                        i = 1;
                        Log.v("DIM", "change minigameid to : "+i);
                        break;
                    case R.id.MG0:
                    default:
                        i=0;
                        Log.v("DIM", "change minigameid to : "+i);
                }
                setSelectedMiniGame(i);
            }
        });

        Button btn = (Button) findViewById(R.id.OkMiniGame);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInfos();
            }
        });

    }

    public void setSelectedMiniGame(int selectedMiniGame) {
        this.selectedMiniGame = selectedMiniGame;
    }

    public void desactivation(View creeralarme) {
        Intent intent = new Intent(DesactivationActivity.this, ChoixSonnerieActivity.class);
        startActivity(intent);
    }
    public void alarme(View activity_main) {
        Intent intent = new Intent(DesactivationActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void parametre(View parametre) {
        Intent intent = new Intent(DesactivationActivity.this, Parametre.class);
        startActivity(intent);
    }


    public void sendInfos(){
        // gives the information back
        Intent res = new Intent();
        res.putExtra(DATA, selectedMiniGame);
        setResult(RESULT_OK, res);
        finish();
    }

}