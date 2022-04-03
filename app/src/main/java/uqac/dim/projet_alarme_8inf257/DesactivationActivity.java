package uqac.dim.projet_alarme_8inf257;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DesactivationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desactivation);
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

}