package uqac.dim.projet_alarme_8inf257;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class Alarm {
    private int id = 0;
    private String heure;
    private int idMiniGame;
    private int idRingtone;
    private int enable;

    public Alarm(int id, String h, int i_mg, int i_r, int e){
        this.id = id;
        this.heure = h;
        this.idMiniGame = i_mg;
        this.idRingtone = i_r;
        this.enable = e;
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
        res.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        res.setOrientation(LinearLayout.HORIZONTAL);

        //TextView tv = new TextView(ctx);
        //tv.setText(this.heure);

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
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 7*2);
        s.setLayoutParams(lp);
        s.setText(heure);
        s.setTextSize(50);
        s.setBackground(ctx.getDrawable(R.xml.contenu));
        s.setPadding(10*2, 10*2, 10*2, 10*2);
        s.setElevation(5*2);

        //res.addView(tv);
        res.addView(s);
        return res;
    }
}
