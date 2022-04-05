package uqac.dim.projet_alarme_8inf257;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import java.sql.SQLException;

    public class ModifierAlarmActivity extends Activity {
        private int idRingtone = 0;
        private int idMiniGame = 0;
        private String hour = "00";
        private String minute = "00";
        private int idAlarm=1;
        private DBAlarmHandler db;

        static final String DATA = "data";
        static final String IDR = "idRingstone";
        static final String IDM = "idMiniGame";
        static final String HOUR = "hour";
        static final String MINUTE = "minute";
        static final int PICK_RINGTONE_REQUEST = 0;
        static final int PICK_MINIGAME_REQUEST = 1;

        private static final String TABLE_NAME_SAVED_ALARMS = "savedAlarms";
        private static final String ID_SAVED_ALARMS = "id";
        private static final String HOUR_SAVED_ALARMS = "hourSaved";
        private static final String ID_MINIGAME_SAVED_ALARMS = "idMiniGame";
        private static final String ID_RINGTONE_SAVED_ALARMS = "idRingtone";
        private static final String ENABLE_SAVED_ALARMS = "enable";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.creeralarme);

            idAlarm = this.getIntent().getIntExtra(DATA, idAlarm);
            minute = this.getIntent().getStringExtra(MINUTE);
            hour = this.getIntent().getStringExtra(HOUR);
            idRingtone = this.getIntent().getIntExtra(IDR, idRingtone);
            idMiniGame = this.getIntent().getIntExtra(IDM, idMiniGame);

            Log.v("DIM", idAlarm + "");

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

            EditText txtH = (EditText) findViewById(R.id.creerAlarmHeure);
            txtH.setText(hour);
            txtH.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() != 0){
                        if(Integer.parseInt(s.toString()) >= 24){
                            txtH.setText("00");
                        }
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    if((s.length() != 0) && (Integer.parseInt(s.toString()) < 24))
                        hour = s.toString();
                    if(s.length() == 1){
                        hour = "0" + hour;
                    }
                    Log.v("DIM",hour);
                }
            });

            EditText txtM = (EditText) findViewById(R.id.creerAlarmMinute);
            txtM.setText(minute);
            txtM.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() != 0){
                        if(Integer.parseInt(s.toString()) >= 60){
                            txtM.setText("00");
                        }
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    if((s.length() != 0) && (Integer.parseInt(s.toString()) < 60))
                        minute = s.toString();
                    if(s.length() == 1){
                        minute = "0" + minute;
                    }
                    Log.v("DIM",minute);
                }
            });
        }
        public void creeralarme(View activity_main) {
            Intent intent = new Intent(ModifierAlarmActivity.this, uqac.dim.projet_alarme_8inf257.CreerAlarmActivity.class);
            startActivity(intent);
        }
        public void alarme(View activity_main) {
            Intent intent = new Intent(ModifierAlarmActivity.this, MainActivity.class);
            startActivity(intent);
        }
        public void parametre(View parametre) {
            Intent intent = new Intent(ModifierAlarmActivity.this, Parametre.class);
            startActivity(intent);
        }
        public void choixsonnerie(View choixsonnerie) {
            Intent intent = new Intent(ModifierAlarmActivity.this, ChoixSonnerieActivity.class);
            intent.putExtra(DATA, this.idRingtone);
            startActivityForResult(intent, PICK_RINGTONE_REQUEST);
        }
        public void desactivation(View desactivation) {
            Intent intent = new Intent(ModifierAlarmActivity.this, DesactivationActivity.class);
            intent.putExtra(DATA, this.idMiniGame);
            startActivityForResult(intent, PICK_MINIGAME_REQUEST);
        }

        public void sauvegarder(){
            Log.v("DIM", "Hour chaged : " + this.hour + ":" + this.minute);

            ContentValues cv = new ContentValues();
            cv.put(HOUR_SAVED_ALARMS, this.hour + ":" + this.minute);
            cv.put(ID_RINGTONE_SAVED_ALARMS, idRingtone);
            cv.put(ID_MINIGAME_SAVED_ALARMS, idMiniGame);
            db.getMyDataBase().update(TABLE_NAME_SAVED_ALARMS, cv, ID_SAVED_ALARMS+" = ?", new String[]{String.valueOf(idAlarm)});
            Log.v("DIM", db.getDataDeLaBD());

            Log.v("DIM", "modification effecuée");
            Intent intent = new Intent(ModifierAlarmActivity.this, MainActivity.class);
            startActivity(intent);
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