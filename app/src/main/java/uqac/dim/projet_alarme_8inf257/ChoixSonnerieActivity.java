package uqac.dim.projet_alarme_8inf257;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class ChoixSonnerieActivity extends Activity {
    private int selectedRingtone;

    static final String DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choixsonnerie);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RingtoneChoice);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int i;
                switch(checkedId){
                    case R.id.RT2:
                        i = 2;
                        Log.v("DIM", "change ringtoneid to : "+i);
                        break;
                    case R.id.RT1:
                        i = 1;
                        Log.v("DIM", "change ringtoneid to : "+i);
                        break;
                    case R.id.RT0:
                    default:
                        i=0;
                        Log.v("DIM", "change ringtoneid to : "+i);
                }
                setSelectedRingtone(i);
            }
        });

        Button btn = (Button) findViewById(R.id.OkRingtone);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInfos();
            }
        });
    }

    public void setSelectedRingtone(int selectedRingtone) {
        this.selectedRingtone = selectedRingtone;
    }

    public void choixsonnerie(View creeralarme) {
        Intent intent = new Intent(ChoixSonnerieActivity.this, ChoixSonnerieActivity.class);
        startActivity(intent);
    }

    public void alarme(View activity_main) {
        Intent intent = new Intent(ChoixSonnerieActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void parametre(View parametre) {
        Intent intent = new Intent(ChoixSonnerieActivity.this, Parametre.class);
        startActivity(intent);
    }

    public void sendInfos(){
        // gives the information back
        Intent res = new Intent();
        res.putExtra(DATA, selectedRingtone);
        setResult(RESULT_OK, res);
        finish();
    }

}