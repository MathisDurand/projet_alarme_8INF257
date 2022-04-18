package uqac.dim.projet_alarme_8inf257;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm {
    private int id = 0;
    private String heure;
    private int idMiniGame;
    private int idRingtone;
    private int enable;
    private Intent intentAlarm;
    private PendingIntent pending;
    BroadcastReceiver alarmReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Alarm worked", Toast.LENGTH_LONG).show();
        }
    };

    private DBAlarmHandler db;
    static final String DATA = "data";
    static final String IDR = "idRingstone";
    static final String IDM = "idMiniGame";
    static final String HOUR = "hour";
    static final String MINUTE = "minute";

    public Alarm(int id, String h, int i_mg, int i_r, int e, DBAlarmHandler db){
        this.id = id;
        this.heure = h;
        this.idMiniGame = i_mg;
        this.idRingtone = i_r;
        this.enable = e;
        this.db = db;

        this.intentAlarm=new Intent(Intent.ACTION_VIEW);
        this.intentAlarm.setData(Uri.parse("http://www.games-workshop.com"));
    }

    public int getId() {
        return id;
    }

    public String getHeure() {
        return heure;
    }

    public int getIdMiniGame() {
        return idMiniGame;
    }

    public int getIdRingtone() {
        return idRingtone;
    }

    public int getEnable() {
        return enable;
    }

    @SuppressLint("ResourceType")
    public LinearLayout display(Context ctx){
        LinearLayout res = new LinearLayout(ctx);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 20);
        res.setLayoutParams(lp);
        res.setOrientation(LinearLayout.VERTICAL);
        res.setPadding(20,20,20,20);

        Switch s = new Switch(ctx);
        s.setChecked(this.enable != 0);
        LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        s.setLayoutParams(lps);
        s.setText(heure);
        s.setTextSize(50);
        s.setPadding(10*2, 10*2, 10*2, 0);
        s.setElevation(5*2);

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeEnable(isChecked, ctx);
            }
        });

        TextView tv = new TextView(ctx);
        LinearLayout.LayoutParams lpt = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lpt);
        tv.setText("Sonnerie : " + this.idRingtone +" | "+ "Mini-jeu : " + this.idMiniGame);
        tv.setTextColor(Color.BLACK);

        Button btnM = new Button(ctx);
        btnM.setText("Modifier");
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToModify(ctx);
            }
        });
        Button btnS = new Button(ctx);
        btnS.setText("Supprimer");
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlarm(ctx);
            }
        });

        res.addView(s);
        res.addView(tv);
        res.addView(btnM);
        res.addView(btnS);
        res.setBackground(ctx.getDrawable(R.xml.contenu));
        return res;
    }

    public void clickToModify(Context ctx){
        Intent intent = new Intent(ctx, ModifierAlarmActivity.class);
        intent.putExtra(DATA, this.id);
        intent.putExtra(IDR, this.idRingtone);
        intent.putExtra(IDM, this.idMiniGame);
        intent.putExtra(HOUR, this.heure.substring(0, 2));
        intent.putExtra(MINUTE, this.heure.substring(3, 5));
        ctx.startActivity(intent);
    }

    public void deleteAlarm(Context ctx){
        db.deleteByID(id);
        Intent intent = new Intent(ctx, MainActivity.class);
        ctx.startActivity(intent);
        this.unsetAlarm(ctx);
    }

    public void changeEnable(boolean enable, Context ctx){
        if (enable){
            this.enable = 1;
            this.db.enableByID(this.id);
            this.setAlarm(ctx);
        }
        else {
            this.enable = 0;
            this.db.disableByID(this.id);
            this.unsetAlarm(ctx);
        }
    }

    public void updateAlarmStatus(Context ctx){
        if(this.enable == 0){
            this.unsetAlarm(ctx);
        }
        else{
            this.setAlarm(ctx);
        }
    }

    public void setAlarm(Context ctx){
        Log.v("DIM", "Alarm set");
        AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(ctx, AlarmReciever.class);
        Log.v("DIM", "IDMG : " + idMiniGame);
        intent.putExtra("minigameID", idMiniGame);
        intent.putExtra("ringtoneID", idRingtone);
        intent.putExtra("data", this.toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        int time = 3600*Integer.parseInt(this.heure.substring(0, 2)) + 60*Integer.parseInt(this.heure.substring(3, 5))
                - 3600*Calendar.getInstance().getTime().getHours() - 60*Calendar.getInstance().getTime().getMinutes() - Calendar.getInstance().getTime().getSeconds();

        if (time <0){
            time += 24*60*60;
        }
        am.setExact( AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000*time , pendingIntent );
    }

    public void unsetAlarm(Context ctx){
        if (pending != null) {
            ctx.unregisterReceiver(alarmReceiver);
            AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pending);
            Log.v("DIM", "Alarm unset");
        }
    }

    public String toString(){
        return "Alarme : " + heure;
    }
}
