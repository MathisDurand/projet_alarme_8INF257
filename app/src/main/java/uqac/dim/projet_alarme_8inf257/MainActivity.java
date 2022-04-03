package uqac.dim.projet_alarme_8inf257;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void parametre(View parametre) {
        Intent intent = new Intent(MainActivity.this, Parametre.class);
        startActivity(intent);
    }
    public void creeralarme(View creeralarme) {
        Intent intent = new Intent(MainActivity.this, CreerAlarmActivity.class);
        startActivity(intent);
    }
}