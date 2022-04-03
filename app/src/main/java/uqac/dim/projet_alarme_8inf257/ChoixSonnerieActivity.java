package uqac.dim.projet_alarme_8inf257;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChoixSonnerieActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choixsonnerie);
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

}