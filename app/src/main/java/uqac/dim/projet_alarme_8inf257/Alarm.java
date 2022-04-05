package uqac.dim.projet_alarme_8inf257;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class Alarm {
    private int id = 0;
    private String heure;
    private int idMiniGame;
    private int idRingtone;
    private int enable;
    private DBAlarmHandler db;

    public Alarm(int id, String h, int i_mg, int i_r, int e, DBAlarmHandler db){
        this.id = id;
        this.heure = h;
        this.idMiniGame = i_mg;
        this.idRingtone = i_r;
        this.enable = e;
        this.db = db;
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
        /*android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="50"
        android:text="10:35"
        android:textSize="60dip"
        android:background="@xml/contenu"
        android:padding="10dp"
        android:elevation="5dp"
        android:layout_marginBottom="7dp"*/

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
                changeEnable(isChecked);
            }
        });

        TextView tv = new TextView(ctx);
        LinearLayout.LayoutParams lpt = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lpt);
        tv.setText("Sonnerie : " + this.idRingtone +" | "+ "Mini-jeu : " + this.idMiniGame);
        tv.setTextColor(Color.BLACK);

        res.addView(s);
        res.addView(tv);
        res.setBackground(ctx.getDrawable(R.xml.contenu));
        return res;
    }

    public void changeEnable(boolean enable){
        if (enable){
            this.enable = 1;
            this.db.enableByID(this.id);
        }
        else {
            this.enable = 0;
            this.db.disableByID(this.id);
        }
    }

}
