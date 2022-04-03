package uqac.dim.projet_alarme_8inf257;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreerAlarmActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creeralarme);
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
        startActivity(intent);
    }
    public void desactivation(View desactivation) {
        Intent intent = new Intent(CreerAlarmActivity.this, DesactivationActivity.class);
        startActivity(intent);
    }

}