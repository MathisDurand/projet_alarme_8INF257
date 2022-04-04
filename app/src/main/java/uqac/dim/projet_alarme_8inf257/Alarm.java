package uqac.dim.projet_alarme_8inf257;

import android.content.Context;
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

    public LinearLayout display(Context ctx){
        LinearLayout res = new LinearLayout(ctx);
        res.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        res.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv = new TextView(ctx);
        tv.setText(this.heure);
        Switch s = new Switch(ctx);
        s.setChecked(this.enable != 0);
        res.addView(tv);
        res.addView(s);
        return res;
    }
}
