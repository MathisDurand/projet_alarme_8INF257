package uqac.dim.projet_alarme_8inf257;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.sql.SQLException;

public class CreerAlarmActivity extends Activity {
    private int idRingtone = 0;
    private int idMiniGame = 0;
    private String hour = "00:00";
    private DBAlarmHandler db;

    static final String DATA = "data";
    static final int PICK_RINGTONE_REQUEST = 0;
    static final int PICK_MINIGAME_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creeralarme);

        db = new DBAlarmHandler(this);
        try {
            db.createDatabase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");
        }

        try {
            db.openDatabase();

        }catch(SQLException sqle){
            Log.v("DIM", "Error" + sqle.getMessage());
        }

        Button btnSauvegarder = (Button) findViewById(R.id.saveAlarm);
        btnSauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sauvegarder();
            }
        });
    }
    public void creeralarme(View activity_main) {
        Intent intent = new Intent(CreerAlarmActivity.this, CreerAlarmActivity.class);
        startActivity(intent);
    }
    public void alarme(View activity_main) {
        Intent intent = new Intent(CreerAlarmActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void parametre(View parametre) {
        Intent intent = new Intent(CreerAlarmActivity.this, Parametre.class);
        startActivity(intent);
    }
    public void choixsonnerie(View choixsonnerie) {
        Intent intent = new Intent(CreerAlarmActivity.this, ChoixSonnerieActivity.class);
        intent.putExtra(DATA, this.idRingtone);
        startActivityForResult(intent, PICK_RINGTONE_REQUEST);
    }
    public void desactivation(View desactivation) {
        Intent intent = new Intent(CreerAlarmActivity.this, DesactivationActivity.class);
        intent.putExtra(DATA, this.idMiniGame);
        startActivityForResult(intent, PICK_MINIGAME_REQUEST);
    }

    public void sauvegarder(){
        db.addNewAlarm(this.hour, this.idMiniGame, this.idRingtone, 1);
        this.getParent().recreate();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // call-back function
        Log.v("DIM", "INFOS received");
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("DIM", "oui");
        if (requestCode == PICK_RINGTONE_REQUEST) {
            if (resultCode == RESULT_OK) {
                this.idRingtone = data.getIntExtra(DATA, this.idRingtone);
                Log.v("DIM", "change Ringtone : "+data.getStringExtra(DATA));
            }
            else{Log.v("DIM", "failed");}
        }
        else if (requestCode == PICK_MINIGAME_REQUEST) {
            if (resultCode == RESULT_OK) {
                this.idMiniGame = data.getIntExtra(DATA, this.idMiniGame);
                Log.v("DIM", "change Minigame : "+data.getStringExtra(DATA));
            }
            else{Log.v("DIM", "failed");}
        }
        else{
            Log.v("DIM", "wrong pickup");
        }
        Log.v("DIM", "infos : " + hour + idRingtone + idMiniGame);
    }

    @Override
    protected void onPause() {
        db.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        try {
            db.openDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        super.onResume();
    }
}